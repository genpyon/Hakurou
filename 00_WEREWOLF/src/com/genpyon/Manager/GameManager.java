package com.genpyon.Manager;

import java.util.Collections;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import com.genpyon.Main;
import com.genpyon.Library.lib;

import net.md_5.bungee.api.ChatColor;



public class GameManager implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public GameManager(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}

	public void Reset(){

		plugin.ROLE.clear();
		plugin.PLAYER.clear();
		plugin.NONROLE.clear();
		plugin.WEREWOLF.clear();
		plugin.HAKUROU.clear();
		plugin.TYOUROU.clear();
		plugin.DEATH.clear();

		plugin.GameStatus = 1;

		plugin.reloadConfig();

		plugin.Preparation = plugin.getConfig().getInt("PREPARATION");
		plugin.GameTime = plugin.getConfig().getInt("GAMETIME");


		for(Player a : Bukkit.getOnlinePlayers()){

			if(!a.getGameMode().equals(GameMode.CREATIVE)){
				plugin.pm.DefaultStuff(a);
			}

		}
	}

	public void setTabName(){
		for(Player a : Bukkit.getOnlinePlayers()){
			if(plugin.GameStatus == 3){
				a.setPlayerListName("");
			} else {
				a.setPlayerListName(a.getName());
			}
		}
	}

	@EventHandler
	public void sneakCanceled(PlayerToggleSneakEvent b){

		if(plugin.GameStatus == 3){
			b.setCancelled(true);
		}
	}



	/**
	 * ロールの設定
	 *
	 */





	// /ww ro [人狼の数] [探偵の数] [妖狐の数]
	public void start(Player p){
		int plus = plugin.iMAGO + plugin.iWEREWOLF + plugin.iDETECTIVE + plugin.iJACKAL;



		Location loc = p.getLocation();


		//ロールのリセット

		plugin.ROLE.clear();
		plugin.PLAYER.clear();
		plugin.NONROLE.clear();
		plugin.WEREWOLF.clear();
		plugin.HAKUROU.clear();
		plugin.TYOUROU.clear();
		plugin.DEATH.clear();


		plugin.GameStatus = 2;

		//全員イノセントにし、ゲームプレイヤーに設定する。
		for(Player a : Bukkit.getOnlinePlayers()){
			if(a.getGameMode().equals(GameMode.ADVENTURE)){

				plugin.pm.GamePlayerStuff(a);

				plugin.ROLE.put(a.getName(), "INNOCENT");
				plugin.PLAYER.add(a.getName());
				plugin.NONROLE.add(a.getName());

				loc.setYaw(a.getLocation().getYaw());
				loc.setPitch(a.getLocation().getPitch());
				a.teleport(loc);

				a.setBedSpawnLocation(loc, true);

				lib.SoundPlayer(p, Sound.ENTITY_ENDERMEN_TELEPORT, 0.2F);

			}

			plugin.StartLocation = loc;
		}

		if(plugin.NONROLE.size()-3 < plus){
			int in = plugin.NONROLE.size()-3;
			Bukkit.broadcastMessage("指定人数が超過しています。" + in + "人以下になるように設定してください。" );
			Reset();
			return;
		}

		lib.sendTitle(ChatColor.BOLD + "白狼", plugin.Preparation +" 秒後に役職が割り当てられます。");
	}


	public void roleOpen(int MAGO ,int DETECTIVE ,int WEREWOLF , int JACKAL){
		//白狼,長老の振り分け

		if(plugin.NONROLE.size() >= 1){

			plugin.WEREWOLF.clear();
			plugin.HAKUROU.clear();
			plugin.TYOUROU.clear();

			Collections.shuffle(plugin.NONROLE);

			roleFor("HAKUROU", 1);

			Collections.shuffle(plugin.NONROLE);

			roleFor("TYOUROU", 1);

		}

		if(plugin.NONROLE.size() >= MAGO){

			//孫の振り分け
			Collections.shuffle(plugin.NONROLE);
			roleFor("MAGO", MAGO);

		} else {
			Bukkit.broadcastMessage("人数が足りません。 - 孫");
			return;
		}

		if(plugin.NONROLE.size() >= DETECTIVE){

			//探偵の振り分け
			Collections.shuffle(plugin.NONROLE);
			roleFor("DETECTIVE", DETECTIVE);

		} else {
			Bukkit.broadcastMessage("人数が足りません。 - 探偵");
			return;
		}

		if(plugin.NONROLE.size() >= WEREWOLF){

			//人狼の振り分け
			Collections.shuffle(plugin.NONROLE);
			roleFor("WEREWOLF", WEREWOLF);

		} else {
			Bukkit.broadcastMessage("人数が足りません。 - 人狼");
			return;
		}

		if(plugin.NONROLE.size() >= JACKAL){

			//妖狐の振り分け
			Collections.shuffle(plugin.NONROLE);
			roleFor("JACKAL", JACKAL);

		} else {
			Bukkit.broadcastMessage("人数が足りません。 - 妖狐");
			return;
		}

		lib.sendPlayer(null, plugin.ROLE.toString());

		for(Player a : Bukkit.getOnlinePlayers()){
			if(plugin.ROLE.containsKey(a.getName())){
				roleChat(a);
			}
		}
	}


	public void roleChat(Player p){
		String name = p.getName();
		String role = null;
		String desc = null;
		String zinei = null;

		if(plugin.ROLE.get(name) != null){
			if(plugin.ROLE.get(name).equalsIgnoreCase("INNOCENT")){
				role = ChatColor.GREEN + "村人";
				desc = ChatColor.RESET + "長老を守り、白狼を狩れ。";
				zinei = ChatColor.GREEN + "村人陣営";

			}


			if(plugin.ROLE.get(name) == "TYOUROU"){
				lib.sendTitleTarget(p, ChatColor.GREEN + "長老", "死ぬな、白狼を狩れ。");
				role = ChatColor.DARK_GREEN + "長老";
				desc = ChatColor.RESET + "死ぬな、白狼を狩れ。";
				zinei = ChatColor.GREEN + "村人陣営";

				p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(60);
				p.setHealth(60);
			}


			if(plugin.ROLE.get(name) == "MAGO"){
				role = ChatColor.GREEN + "孫";
				desc = ChatColor.RESET + "お前だけが長老を知っている。";
				zinei = ChatColor.GREEN + "村人陣営";

				p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
				p.setHealth(20);
			}

			if(plugin.ROLE.get(name) == "DETECTIVE"){
				role = ChatColor.BLUE + "探偵";
				desc = ChatColor.RESET + "長老を守り、白狼を見つけろ。";
				zinei = ChatColor.GREEN + "村人陣営";
			}


			if(plugin.ROLE.get(name) == "WEREWOLF"){
				role = ChatColor.RED + "人狼";
				desc = ChatColor.RESET + "白狼を守り、長老を狩れ。";
				zinei = ChatColor.RED + "人狼陣営";
			}


			if(plugin.ROLE.get(name) == "HAKUROU"){
				role = ChatColor.DARK_RED + "白狼";
				desc = ChatColor.RESET + "死ぬな、長老を狩れ。";
				zinei = ChatColor.RED + "人狼陣営";
			}


			if(plugin.ROLE.get(name) == "JACKAL"){
				role = ChatColor.AQUA + "妖狐";
				desc = ChatColor.RESET + "見つからずに、長老と白狼を呪え。";
				zinei = ChatColor.AQUA + "妖狐陣営";
			}

			lib.sendTitleTarget(p, role, desc);
			lib.sendPlayer(p, "");
			lib.sendPlayer(p, "========================");
			lib.sendPlayer(p, "");
			lib.sendPlayer(p, ChatColor.RED + " ゲームが開始されました。");
			lib.sendPlayer(p, "  " + plugin.Preparation + " 秒後に役職が割り振られます。");
			lib.sendPlayer(p, "");
			lib.sendPlayer(p, "========================");
			lib.sendPlayer(p, "");
			lib.sendPlayer(p, " あなたの役職 : " + role);
			lib.sendPlayer(p, " " + desc);
			lib.sendPlayer(p, "");
			lib.sendPlayer(p, " あなたの陣営 : " + zinei);
			lib.sendPlayer(p, "");
			lib.sendPlayer(p, "========================");
			lib.sendPlayer(p, "");

			if(plugin.ROLE.get(name).equals("WEREWOLF") || plugin.ROLE.get(name).equals("HAKUROU")){
				lib.sendPlayer(p, ChatColor.RED + "仲間の人狼 : " + plugin.WEREWOLF.toString());
				lib.sendPlayer(p, ChatColor.DARK_RED + "白狼 : " + plugin.HAKUROU.toString());
			}

			if(plugin.ROLE.get(name).equals("MAGO")){
				lib.sendPlayer(p, ChatColor.DARK_GREEN + "長老 : " + plugin.TYOUROU.toString());
			}

			lib.SoundPlayer(p, Sound.ENTITY_WOLF_HOWL, 2F);
		}

		plugin.GameStatus = 3;

	}

	//ロール振り分けちゃん
	public void roleFor(String ROLE, int math){

		Collections.shuffle(plugin.PLAYER);

		for (int i = 0; i < math; i++){

			if(plugin.ROLE.get(plugin.NONROLE.get(i)) == "INNOCENT"){

				plugin.ROLE.put(plugin.NONROLE.get(i), ROLE);

				lib.sendPlayer(null, ROLE + " --> " + plugin.NONROLE.get(i));

				if(ROLE.equalsIgnoreCase("TYOUROU")){
					plugin.TYOUROU.add(plugin.NONROLE.get(i));
				}

				if(ROLE.equalsIgnoreCase("WEREWOLF")){
					plugin.WEREWOLF.add(plugin.NONROLE.get(i));
				}
				if(ROLE.equalsIgnoreCase("HAKUROU")){
					plugin.HAKUROU.add(plugin.NONROLE.get(i));
				}


				plugin.NONROLE.remove(i);

			} else {

				Collections.shuffle(plugin.PLAYER);
				i--;

			}
		}
	}



	//win 1村人 - 2人狼 3 -妖狐
	public void gameEnd(int win, boolean time){

		String Title = null;
		String SubTitle = null;

		//村人の勝ち
		if(win == 1){

			Title = ChatColor.GREEN + "村人陣営の勝利";

			if(time == true){
				SubTitle = "時間切れ";
			} else {
				SubTitle = ChatColor.DARK_RED + "白狼" + ChatColor.RESET + " が倒された。";
			}
		}

		//人狼の勝ち

		if(win == 2){


			Title = ChatColor.RED + "人狼陣営の勝利";
			SubTitle = ChatColor.DARK_GREEN + "長老" + ChatColor.RESET + " が倒された。";


		}

		if(win == 3){


			Title = ChatColor.AQUA + "妖狐陣営の勝利";
			SubTitle = ChatColor.GREEN + "呪" + ChatColor.RESET + "";

		}


		lib.sendTitle(Title, SubTitle);
		lib.SoundAllPlayer(Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 2F);


		openRole();

		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("========================");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(Title);
		Bukkit.broadcastMessage(SubTitle);
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("========================");
		Bukkit.broadcastMessage("");

		plugin.GameStatus = 4;
	}


	public void openRole(){
		Bukkit.broadcastMessage("========================");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("■ 今回の配役 ■");
		Bukkit.broadcastMessage("");

		String rolename = null;

		for (int i = 0; i < plugin.PLAYER.size(); i++){

			String ROLE = plugin.ROLE.get(plugin.PLAYER.get(i));

			if(ROLE.equalsIgnoreCase("INNOCENT")){
				rolename = ChatColor.GREEN + "村人" + ChatColor.RESET;
			}

			if(ROLE.equalsIgnoreCase("TYOUROU")){
				rolename = ChatColor.DARK_GREEN + "長老" + ChatColor.RESET;
			}

			if(ROLE.equalsIgnoreCase("DETECTIVE")){
				rolename = ChatColor.BLUE + "探偵" + ChatColor.RESET;
			}

			if(ROLE.equalsIgnoreCase("MAGO")){
				rolename = ChatColor.GREEN + "孫" + ChatColor.RESET;
			}

			if(ROLE.equalsIgnoreCase("WEREWOLF")){
				rolename = ChatColor.RED + "人狼" + ChatColor.RESET;
			}
			if(ROLE.equalsIgnoreCase("HAKUROU")){
				rolename = ChatColor.DARK_RED + "白狼" + ChatColor.RESET;
			}

			if(ROLE.equalsIgnoreCase("JACKAL")){
				rolename = ChatColor.AQUA + "妖狐" + ChatColor.RESET;
			}

			Bukkit.broadcastMessage("  [ " + rolename + " ] : " +  plugin.PLAYER.get(i));
		}

	}

	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent b){
		Player p = b.getPlayer();

		if(plugin.GameStatus != 3){

			plugin.pm.DefaultStuff(p);

		} else {

			plugin.pm.RespawnStuff(p);

		}
	}

	@EventHandler
	public void PlayerQuitEvent(PlayerQuitEvent b){
		Player p = b.getPlayer();

		if(plugin.GameStatus != 3){

			plugin.pm.DefaultStuff(p);

		} else {
			if(plugin.PLAYER.contains(p.getName())&& !plugin.DEATH.contains(p.getName())){
			plugin.pm.DeathPlayer(p, p.getLocation());
			}

		}
	}


	@EventHandler
	public void playerKillEvent(PlayerDeathEvent b){

		if(plugin.GameStatus != 3){
			return;
		}

		b.setDeathMessage("");

		Player killer = b.getEntity().getPlayer();
		Player death = b.getEntity().getPlayer();

		Location loc = death.getLocation();
		loc.setY(loc.getY() +1);


		if(killer == null){

		}



		//人狼が死ぬ処理
		if(plugin.ROLE.containsKey(death.getName())){

			if(plugin.ROLE.get(death.getName()).equalsIgnoreCase("HAKUROU")){
				gameEnd(1, false);
				return;
			}

			if(plugin.ROLE.get(death.getName()).equalsIgnoreCase("TYOUROU")){
				gameEnd(2, false);
				return;
			}

			if(plugin.PLAYER.contains(death.getName()) && !plugin.DEATH.contains(death.getName())){

				plugin.pm.DeathPlayer(death ,loc);

			}

		}

	}

	@EventHandler
	public void respawnEvent(PlayerRespawnEvent b){

		if(plugin.GameStatus != 3){
			return;
		} else {

			Player p = b.getPlayer();
			plugin.pm.RespawnStuff(p);


		}


	}



}
