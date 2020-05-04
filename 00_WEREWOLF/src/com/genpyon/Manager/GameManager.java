package com.genpyon.Manager;

import java.util.Collections;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
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


		Location loc = p.getLocation();


		//ロールのリセット
		plugin.ROLE.clear();
		plugin.PLAYER.clear();
		plugin.NONROLE.clear();
		plugin.WEREWOLF.clear();
		plugin.HAKUROU.clear();

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

				lib.SoundPlayer(p, Sound.ENTITY_ENDERMEN_TELEPORT, 0.2F);

			}
		}

		lib.sendTitle(ChatColor.BOLD + "白狼", plugin.Preparation +" 秒後に役職が割り当てられます。");
	}


	public void roleOpen(int WEREWOLF ,int DETECTIVE , int JACKAL){
		//白狼,長老の振り分け
		
		if(plugin.NONROLE.size() >= 1){
			Collections.shuffle(plugin.NONROLE);

			roleFor("HAKUROU", 1);

			Collections.shuffle(plugin.NONROLE);

			roleFor("TYOUROU", 1);
		}

		if(plugin.NONROLE.size() >= WEREWOLF){

			//人狼の振り分け
			Collections.shuffle(plugin.NONROLE);
			roleFor("WEREWOLF", WEREWOLF);

		} else {
			Bukkit.broadcastMessage("人数が足りません。 - 人狼");
		}

		if(plugin.NONROLE.size() >= DETECTIVE){

			//探偵の振り分け
			Collections.shuffle(plugin.NONROLE);
			roleFor("DETECTIVE", DETECTIVE);

		} else {
			Bukkit.broadcastMessage("人数が足りません。 - 探偵");
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

		if(plugin.ROLE.get(name) != null){
			if(plugin.ROLE.get(name).equalsIgnoreCase("INNOCENT")){
				role = ChatColor.GREEN + "村人";
				desc = ChatColor.RESET + "長老を守り、白狼を狩れ。";
			}

			if(plugin.ROLE.get(name) == "TYOUROU"){
				lib.sendTitleTarget(p, ChatColor.GREEN + "長老", "死ぬな、白狼を狩れ。");
				role = ChatColor.GREEN + "長老";
				desc = ChatColor.RESET + "長老を守り、白狼を狩れ。";
			}

			if(plugin.ROLE.get(name) == "DETECTIVE"){
				role = ChatColor.BLUE + "探偵";
				desc = ChatColor.RESET + "長老を守り、白狼を見つけろ。";
			}

			if(plugin.ROLE.get(name) == "WEREWOLF"){
				role = ChatColor.RED + "人狼";
				desc = ChatColor.RESET + "白狼を守り、長老を狩れ。";
			}

			if(plugin.ROLE.get(name) == "HAKUROU"){
				role = ChatColor.RED + "白狼";
				desc = ChatColor.RESET + "死ぬな、長老を狩れ。";
			}

			if(plugin.ROLE.get(name) == "JACKAL"){
				role = ChatColor.AQUA + "妖狐";
				desc = ChatColor.RESET + "見つからずに、長老と白狼を呪え。";
			}

			lib.sendTitleTarget(p, role, desc);

			lib.sendPlayer(p, ChatColor.RED + "ゲームが開始されました。");

			lib.sendPlayer(p, "");
			lib.sendPlayer(p, "========================");
			lib.sendPlayer(p, "");
			lib.sendPlayer(p, " あなたの役職 : " + role);
			lib.sendPlayer(p, " " + desc);
			lib.sendPlayer(p, "");
			lib.sendPlayer(p, "========================");
			lib.sendPlayer(p, "");

			if(plugin.ROLE.get(name).equals("WEREWOLF") || plugin.ROLE.get(name).equals("HAKUROU")){
				lib.sendPlayer(p, ChatColor.RED + "仲間の人狼 : " + plugin.WEREWOLF.toString());
				lib.sendPlayer(p, ChatColor.RED + "白狼 : " + plugin.HAKUROU.toString());
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
				SubTitle = ChatColor.RED + "白狼" + ChatColor.RESET + " が倒された。";
			}


			lib.sendTitle(Title, SubTitle);
			lib.SoundAllPlayer(Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 2F);

		}

		//人狼の勝ち

		if(win == 2){


			Title = ChatColor.RED + "人狼陣営の勝利";
			SubTitle = ChatColor.GREEN + "オサ" + ChatColor.RESET + " が倒された。";


			lib.sendTitle(Title, SubTitle);
			lib.SoundAllPlayer(Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 2F);

		}


		if(win == 3){


			Title = ChatColor.AQUA + "妖狐陣営の勝利";
			SubTitle = ChatColor.GREEN + "呪" + ChatColor.RESET + "";


			lib.sendTitle(Title, SubTitle);
			lib.SoundAllPlayer(Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 2F);

		}

		plugin.GameStatus = 4;
	}






	@EventHandler
	public void PlayerKillEvent(PlayerDeathEvent b){
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

		}

		//Spawn Head
		death.getWorld().dropItemNaturally(loc, plugin.sm.roleHeadSpawn(death.getName()));

	}

	public void Reset(){

		plugin.ROLE.clear();
		plugin.PLAYER.clear();
		plugin.NONROLE.clear();
		plugin.WEREWOLF.clear();
		plugin.HAKUROU.clear();

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


}
