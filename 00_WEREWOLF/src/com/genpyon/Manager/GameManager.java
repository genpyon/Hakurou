package com.genpyon.Manager;

import java.util.Collections;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Library.lib;
import com.genpyon.Role.RoleManager;

import net.md_5.bungee.api.ChatColor;



public class GameManager implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public GameManager(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}


	@EventHandler
	public void onDropedItems(PlayerDropItemEvent b){
		ItemStack is = b.getItemDrop().getItemStack();

		if(b.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			return;
		}

		if(is.getType().equals(Material.SKULL_ITEM)) {

		} else {
			b.setCancelled(true);
		}
	}


	public void Reset(){

		for(Player a : Bukkit.getOnlinePlayers()) {
			for(Player a2 : Bukkit.getOnlinePlayers()) {
				a.hidePlayer(a2);
			}
		}

		Main.ROLE.clear();
		Main.PLAYER.clear();
		Main.DEATH.clear();
		Main.NONROLE.clear();
		Main.INNOCENT.clear();
		Main.TYOUROU.clear();
		Main.WEREWOLF.clear();
		Main.HAKUROU.clear();
		Main.JACKAL.clear();
		Main.FOUND.clear();

		Main.CO.clear();
		Main.KEKKA.clear();
		Main.URANAI.clear();


		Main.iMAGO = plugin.getConfig().getInt("STARTINT.MAGO");
		Main.iDETECTIVE = plugin.getConfig().getInt("STARTINT.DETECTIVE");
		Main.iWEREWOLF = plugin.getConfig().getInt("STARTINT.WEREWOLF");
		Main.iJACKAL = plugin.getConfig().getInt("STARTINT.JACKAL");


		Main.cINNOCENT = plugin.getConfig().getInt("COIN.INNOCENT");
		Main.cWEREWOLF = plugin.getConfig().getInt("COIN.WEREWOLF");
		Main.cHAKUROU = plugin.getConfig().getInt("COIN.HAKUROU");
		Main.cDETECTIVE = plugin.getConfig().getInt("COIN.DETECTIVE");
		Main.cTYOUROU = plugin.getConfig().getInt("COIN.TYOUROU");
		Main.cJACKAL = plugin.getConfig().getInt("COIN.JACKAL");

		Main.GameStatus = 1;

		plugin.PlayTime = 0;

		plugin.reloadConfig();

		Main.Preparation = plugin.getConfig().getInt("PREPARATION");
		Main.GameTime = plugin.getConfig().getInt("GAMETIME");


		for(Player a : Bukkit.getOnlinePlayers()){

			if(!a.getGameMode().equals(GameMode.CREATIVE)){
				plugin.pm.DefaultStuff(a);
			}

			//クールダウンの初期化
			a.setCooldown(Material.BOOK, 0);
		}

		Main.dropedItemsClear();

		for(Player a : Bukkit.getOnlinePlayers()) {
			for(Player a2 : Bukkit.getOnlinePlayers()) {
				a.hidePlayer(a2);
			}
		}
	}

	public void setTabName(){
		for(Player a : Bukkit.getOnlinePlayers()){
			if(Main.GameStatus == 3){
				a.setPlayerListName("");
			} else {
				a.setPlayerListName(a.getName());
			}
		}
	}

	@EventHandler
	public void sneakCanceled(PlayerToggleSneakEvent b){

		if(Main.GameStatus == 3 && !b.getPlayer().getGameMode().equals(GameMode.SPECTATOR)){
			b.setCancelled(true);
		}
	}



	/**
	 * ロールの設定
	 *
	 */


	public void start(Player p, Location loc){
		int plus = Main.iMAGO + Main.iWEREWOLF + Main.iDETECTIVE + Main.iJACKAL;

		//ロールのリセット
		Main.ROLE.clear();

		Main.PLAYER.clear();
		Main.DEATH.clear();

		Main.NONROLE.clear();
		Main.INNOCENT.clear();

		Main.TYOUROU.clear();
		Main.WEREWOLF.clear();

		Main.HAKUROU.clear();
		Main.JACKAL.clear();


		Main.GameStatus = 2;

		//全員イノセントにし、ゲームプレイヤーに設定する。
		for(Player a : Bukkit.getOnlinePlayers()){
			if(a.getGameMode().equals(GameMode.ADVENTURE)){

				plugin.pm.GameReadyPlayerStuff(a, loc);

			}

			Main.StartLocation = loc;
			Main.dropedItemsClear();

		}

		if(Main.NONROLE.size()-1 <= plus){
			int in = Main.NONROLE.size()-1;

			Bukkit.broadcastMessage("指定人数が超過しています。" + in + "人以下になるように設定してください。" );
			Reset();
			return;
		}
		if(Main.TTTMode == false) {
			lib.sendTitle(ChatColor.BOLD + "白狼", Main.Preparation +" 秒後に役職が割り当てられます。");
		} else {
			lib.sendTitle(ChatColor.RED + "TTT", Main.Preparation +" 秒後に役職が割り当てられます。");
		}

		lib.sendPlayer(p, "");
		lib.sendPlayer(p, "========================");
		lib.sendPlayer(p, "");
		lib.sendPlayer(p, ChatColor.RED + " ゲームが開始されました。");
		lib.sendPlayer(p, "  " + Main.Preparation + " 秒後に役職が割り振られます。");
		lib.sendPlayer(p, "");
		lib.sendPlayer(p, "========================");
	}


	public void roleOpen(int MAGO ,int DETECTIVE ,int WEREWOLF , int JACKAL){
		//白狼,長老の振り分け

		if(Main.NONROLE.size() > 1){
			if(Main.TTTMode == false) {

				Collections.shuffle(Main.NONROLE);
				roleFor("HAKUROU", 1);

				Collections.shuffle(Main.NONROLE);
				roleFor("TYOUROU", 1);

			}
		}

		if(Main.NONROLE.size() >= MAGO){

			//孫の振り分け
			Collections.shuffle(Main.NONROLE);
			roleFor("MAGO", MAGO);

		} else {
			Bukkit.broadcastMessage("人数が足りません。 - 孫");
			Bukkit.broadcastMessage(Main.NONROLE.toString());
			Reset();
			return;
		}

		if(Main.NONROLE.size() >= DETECTIVE){

			//探偵の振り分け
			Collections.shuffle(Main.NONROLE);
			roleFor("DETECTIVE", DETECTIVE);

		} else {
			Bukkit.broadcastMessage("人数が足りません。 - 探偵");
			Bukkit.broadcastMessage(Main.NONROLE.toString());
			Reset();
			return;
		}
		if(Main.NONROLE.size() >= WEREWOLF){

			//人狼の振り分け
			Collections.shuffle(Main.NONROLE);
			roleFor("WEREWOLF", WEREWOLF);

		} else {
			Bukkit.broadcastMessage("人数が足りません。 - 人狼");
			Bukkit.broadcastMessage(Main.NONROLE.toString());
			Reset();
			return;
		}

		if(Main.NONROLE.size() >= JACKAL){

			//妖狐の振り分け
			Collections.shuffle(Main.NONROLE);
			roleFor("JACKAL", JACKAL);

		} else {
			Bukkit.broadcastMessage("人数が足りません。 - 妖狐");
			Bukkit.broadcastMessage(Main.NONROLE.toString());
			Reset();
			return;
		}

		lib.sendPlayer(null, Main.ROLE.toString());

		for(Player a : Bukkit.getOnlinePlayers()){
			if(Main.ROLE.containsKey(a.getName())){
				roleChat(a);
			}
		}
	}


	public void roleChat(Player p){
		String name = p.getName();
		String role = null;
		String desc = null;
		String zinei = null;
		int coin = 0;

		plugin.pm.GamePlayerStuff(p);

		if(Main.ROLE.get(name) != null){
			if(Main.ROLE.get(name).equalsIgnoreCase("INNOCENT")){
				role = ChatColor.GREEN + "村人";
				if(Main.TTTMode == false) {
					desc = ChatColor.RESET + "長老を守り、白狼を狩れ。";
				} else {
					desc = ChatColor.RESET + "人狼と妖狐を狩りきれ。";
				}
				zinei = ChatColor.GREEN + "村人陣営";

				coin = Main.cINNOCENT;

			}


			if(Main.ROLE.get(name) == "TYOUROU"){
				lib.sendTitleTarget(p, ChatColor.GREEN + "長老", "死ぬな、白狼を狩れ。");
				role = ChatColor.DARK_GREEN + "長老";
				desc = ChatColor.RESET + "死ぬな、白狼を狩れ。";
				zinei = ChatColor.GREEN + "村人陣営";

				coin = Main.cINNOCENT;


				p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
				p.setHealth(p.getHealthScale());
			}


			if(Main.ROLE.get(name) == "MAGO"){
				role = ChatColor.GREEN + "孫";
				desc = ChatColor.RESET + "お前だけが長老を知っている。";
				zinei = ChatColor.GREEN + "村人陣営";

				p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
				p.setHealth(p.getHealthScale());


				coin = Main.cINNOCENT;

			}

			if(Main.ROLE.get(name) == "DETECTIVE"){
				role = ChatColor.BLUE + "探偵";
				if(Main.TTTMode == false) {
					desc = ChatColor.RESET + "長老を守り、白狼を見つけろ。";
				} else {
					desc = ChatColor.RESET + "人狼と妖狐を見つけ、狩れ。";
				}
				zinei = ChatColor.GREEN + "村人陣営";

				coin = Main.cDETECTIVE;

			}


			if(Main.ROLE.get(name) == "WEREWOLF"){
				role = ChatColor.RED + "人狼";
				if(Main.TTTMode == false) {
					desc = ChatColor.RESET + "白狼を守り、長老を狩れ。";
				} else {
					desc = ChatColor.RESET + "村人と妖狐をすべて狩れ。";
				}
				zinei = ChatColor.RED + "人狼陣営";


				coin = Main.cWEREWOLF;

			}


			if(Main.ROLE.get(name) == "HAKUROU"){
				role = ChatColor.DARK_RED + "白狼";
				desc = ChatColor.RESET + "死ぬな、長老を狩れ。";
				zinei = ChatColor.RED + "人狼陣営";


				coin = Main.cHAKUROU;

			}


			if(Main.ROLE.get(name) == "JACKAL"){
				role = ChatColor.AQUA + "妖狐";
				if(Main.TTTMode == false) {
					desc = ChatColor.RESET + "見つからずに、長老と白狼を呪え。";
				} else {
					desc = ChatColor.RESET + "生き残れ。";
				}
				zinei = ChatColor.AQUA + "妖狐陣営";


				coin = Main.cJACKAL;

			}

			lib.sendTitleTarget(p, role, desc);
			lib.sendPlayer(p, "");
			lib.sendPlayer(p, " あなたの役職 : " + role);
			lib.sendPlayer(p, " " + desc);
			lib.sendPlayer(p, "");
			lib.sendPlayer(p, " あなたの陣営 : " + zinei);

			if(coin != 0) {
				lib.sendPlayer(p, " " + ChatColor.YELLOW + coin + " COIN" + ChatColor.RESET + " 付与されました。");
			}

			lib.sendPlayer(p, "");
			lib.sendPlayer(p, "========================");
			lib.sendPlayer(p, "");

			Main.COIN.put(p.getName(), coin);

			if(Main.ROLE.get(name).equals("WEREWOLF") || Main.ROLE.get(name).equals("HAKUROU")){
				lib.sendPlayer(p, ChatColor.RED + " 仲間の人狼 : " + Main.WEREWOLF.toString());
				lib.sendPlayer(p, ChatColor.DARK_RED + " 白狼 : " + Main.HAKUROU.toString());
				lib.sendPlayer(p, "");
			}

			if(Main.ROLE.get(name).equals("MAGO")){
				lib.sendPlayer(p, ChatColor.DARK_GREEN + " 長老 : " + Main.TYOUROU.toString());
				lib.sendPlayer(p, "");
			}

			lib.sendPlayer(p, " 今回の配役");
			lib.sendPlayer(p, "  " + RoleManager.Haiyaku());

			lib.SoundPlayer(p, Sound.ENTITY_WOLF_HOWL, 2F);
		}

		Main.GameStatus = 3;

	}

	//ロール振り分けちゃん
	public void roleFor(String ROLE, int math){

		Collections.shuffle(Main.PLAYER);

		for (int i = 0; i < math; i++){

			if(Main.ROLE.get(Main.NONROLE.get(i)) == "INNOCENT"){

				Main.ROLE.put(Main.NONROLE.get(i), ROLE);

				lib.sendPlayer(null, ROLE + " --> " + Main.NONROLE.get(i));

				if(ROLE.equalsIgnoreCase("TYOUROU")){
					Main.TYOUROU.add(Main.NONROLE.get(i));
				}

				if(ROLE.equalsIgnoreCase("WEREWOLF")){
					Main.WEREWOLF.add(Main.NONROLE.get(i));
				}

				if(ROLE.equalsIgnoreCase("HAKUROU")){
					Main.HAKUROU.add(Main.NONROLE.get(i));
				}

				if(ROLE.equalsIgnoreCase("JACKAL")){
					Main.JACKAL.add(Main.NONROLE.get(i));
				}

				Main.INNOCENT.remove(Main.NONROLE.get(i));

			} else {

				Collections.shuffle(Main.PLAYER);
				i--;

			}
		}

		for(Player a : Bukkit.getOnlinePlayers()){
			if(Main.ROLE.get(a.getName()) != null && !Main.ROLE.get(a.getName()).equalsIgnoreCase("INNOCENT")){
				Main.NONROLE.remove(a.getName());
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

		//妖狐の勝ち

		if(win == 3){
			Title = ChatColor.AQUA + "妖狐陣営の勝利";
			SubTitle = ChatColor.GREEN + "呪" + ChatColor.RESET + "";
		}

		//村人の勝ち TTTモード
		if(win == 4){
			Title = ChatColor.GREEN + "村人陣営の勝利";
			if(time == true){
				SubTitle = "時間切れ";
			} else {
				SubTitle = ChatColor.RED + "人狼" + ChatColor.RESET + " が全滅した。";
			}
		}

		//人狼の勝ち TTTモード
		if(win == 5){
			Title = ChatColor.RED + "人狼陣営の勝利";
			if(time == true){
				SubTitle = "時間切れ";
			} else {
				SubTitle = ChatColor.RED + "村人" + ChatColor.RESET + " が全滅した。";
			}
		}

		if(win == 6){
			Title = ChatColor.AQUA + "妖狐陣営の勝利";
			SubTitle = ChatColor.RED + "人狼" + ChatColor.RESET + "が全滅した。";
		}

		if(win == 7){
			Title = ChatColor.AQUA + "妖狐陣営の勝利";
			SubTitle = ChatColor.GREEN + "村人" + ChatColor.RESET + "が全滅した。";
		}

		lib.sendTitle(Title, SubTitle);
		lib.SoundAllPlayer(Sound.ENTITY_ENDERDRAGON_FIREBALL_EXPLODE, 2F);


		openRole();

		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("========================");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(" " + Title);
		Bukkit.broadcastMessage("  " + SubTitle);
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("========================");
		Bukkit.broadcastMessage("");

		Main.GameStatus = 4;
	}


	public void openRole(){
		Bukkit.broadcastMessage("========================");
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage("■ 今回の配役 ■");
		Bukkit.broadcastMessage("");

		String rolename = null;

		for (int i = 0; i < Main.PLAYER.size(); i++){

			String ROLE = Main.ROLE.get(Main.PLAYER.get(i));

			rolename = RoleManager.roleNameChanger(ROLE);

			Bukkit.broadcastMessage("  [ " + rolename + " ] : " +  Main.PLAYER.get(i));
		}

	}

	@EventHandler
	public void PlayerJoinEvent(PlayerJoinEvent b){
		Player p = b.getPlayer();

		if(Main.GameStatus != 3){
			if(Main.GameStatus == 2) {
				plugin.pm.GameReadyPlayerStuff(p, Main.StartLocation);
			} else {
				plugin.pm.DefaultStuff(p);
			}

		} else {

			plugin.pm.RespawnStuff(p);

		}
	}

	@EventHandler
	public void PlayerQuitEvent(PlayerQuitEvent b){
		Player p = b.getPlayer();
		Location loc = p.getLocation();
		loc.setY(loc.getY() +1);

		if(Main.GameStatus != 3){

			plugin.pm.DefaultStuff(p);

		} else {

			deathifevent(p,loc);

		}
	}

	@EventHandler
	public void playerKillEvent(PlayerDeathEvent b){

		b.setDeathMessage(null);

		Player p = b.getEntity().getPlayer();
		Player killer = null;

		Location loc = p.getLocation();
		loc.setY(loc.getY() +1);


		if(b.getEntity().getKiller() instanceof Player){
			killer = b.getEntity().getKiller();
		} else {

		}

		if(killer != null) {
			lib.sendPlayer(p, Main.system + ChatColor.RED + killer.getName() + ChatColor.RESET + " に殺されました。");
		} else {
			lib.sendPlayer(p, Main.system + ChatColor.RED + " 自滅しました。");
		}

		deathifevent(p,loc);

	}

	public void deathifevent(Player p, Location loc) {

		//人狼が死んだ時
		if(Main.ROLE.containsKey(p.getName())){

			if(Main.PLAYER.contains(p.getName()) && !Main.DEATH.contains(p.getName())){
				plugin.pm.DeathPlayer(p ,loc);
				for(Player a : Bukkit.getOnlinePlayers()) {
					a.spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 1, 0, 0, 0);
				}
			}

			if(Main.ROLE.get(p.getName()).equalsIgnoreCase("WEREWOLF")){
				Main.WEREWOLF.remove(p.getName());
			}

			if(Main.ROLE.get(p.getName()).equalsIgnoreCase("HAKUROU")){
				gameEnd(1, false);
				return;
			}

			if(Main.ROLE.get(p.getName()).equalsIgnoreCase("TYOUROU")){
				gameEnd(2, false);
				return;
			}

			//村人が死んだ時
			if(Main.ROLE.get(p.getName()).equalsIgnoreCase("INNOCENT")){
				Main.INNOCENT.remove(p.getName());
			}

			if(Main.ROLE.get(p.getName()).equalsIgnoreCase("JACKAL")){
				Main.JACKAL.remove(p.getName());
			}
			endGame();
		}
	}

	public void endGame() {

		if(Main.TTTMode == true) {
			if(Main.INNOCENT.size() == 0) {
				if(Main.JACKAL.size() != 0) {
					//妖狐の勝ち
					gameEnd(7, false);
					return;
				} else {
					//人狼の勝ち
					gameEnd(5, false);
					return;
				}
			}

			if(Main.WEREWOLF.size() == 0) {
				if(Main.JACKAL.size() != 0) {
					//妖狐の勝ち
					gameEnd(6, false);
					return;
				} else {
					//村人の勝ち
					gameEnd(4, false);
					return;
				}
			}
		}

		if(Main.WEREWOLF.size() == 0 && Main.INNOCENT.size() == 0 && Main.JACKAL.size() >= 1){
			gameEnd(3, false);
		}
	}

	@EventHandler
	public void respawnEvent(PlayerRespawnEvent b){

		if(Main.GameStatus != 3){
			return;
		} else {

			Player p = b.getPlayer();
			plugin.pm.RespawnStuff(p);


		}


	}



}
