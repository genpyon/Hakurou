package com.genpyon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

import com.genpyon.Event.ChatListener;
import com.genpyon.Event.EventListener;
import com.genpyon.Event.ShopListener;
import com.genpyon.Item.Wolf.BlinkTimer;
import com.genpyon.Library.lib;
import com.genpyon.Manager.GameManager;
import com.genpyon.Manager.PlayerManager;
import com.genpyon.Manager.ShopManager;
import com.genpyon.Manager.SkullManager;
import com.genpyon.Manager.TeamManager;
import com.genpyon.Role.RoleManager;
import com.genpyon.Shop.ShopType;

import net.md_5.bungee.api.ChatColor;



public class Main extends JavaPlugin implements Listener {

	public static Main main;

	public static Team USER;

	//1待機 2準備 3ゲーム 4終了
	public int GameStatus = 1;

	public int GameTime = getConfig().getInt("GAMETIME");
	public int Preparation = getConfig().getInt("PREPARATION");

	public int PlayTime = 0;
	public int MoneyTime = 45;

	public int MoneyMoneyMoney = 50;

	public static Location StartLocation = null;

	public int iMAGO = 0;
	public int iWEREWOLF = 0;
	public int iDETECTIVE = 0;
	public int iJACKAL = 0;

	//コインをあげる量

	public static int cINNOCENT = 0;
	public static int cWEREWOLF = 0;
	public static int cHAKUROU = 0;
	public static int cDETECTIVE = 0;
	public static int cTYOUROU = 0;
	public static int cJACKAL = 0;


	public static boolean DetectiveMode = true;





	//全員の役職まとめ
	public static HashMap <String, String> ROLE = new HashMap<String, String>();
	public static ArrayList<String> PLAYER = new ArrayList<String>();
	public static ArrayList<String> DEATH = new ArrayList<String>();


	public static ArrayList<String> NONROLE = new ArrayList<String>();
	public static ArrayList<String> INNOCENT = new ArrayList<String>();

	public static ArrayList<String> TYOUROU = new ArrayList<String>();
	public static ArrayList<String> WEREWOLF = new ArrayList<String>();
	public static ArrayList<String> HAKUROU = new ArrayList<String>();
	public static ArrayList<String> JACKAL = new ArrayList<String>();

	public static HashMap <String, String> CO = new HashMap<String, String>();

	//ショップの金
	public static  HashMap <String, Integer> COIN = new HashMap<String, Integer>();

	//最後にロール公開するやつ



	/**
	 * ■ ロール一覧 ■
	 *
	 * 村人 = INNOCENT;
	 * 	- 普通の村人。
	 *
	 * 長老 = TYOUROU;
	 * 	- 	村人の長。死んだら村人陣営の負け。
	 *
	 * 孫 = MAGO;
	 *  -	唯一、長老を知る男。
	 *
	 * 探偵 = DETECTIVE;
	 * 	- 	村人陣営。一定時間に一人、陣営を見ることができる。
	 * 		アイテムを購入できる。
	 *
	 *
	 * 人狼 = WEREWOLF;
	 * 	- 	普通の人狼。
	 *
	 * 白狼 = HAKUROU;
	 *  - 	人狼のボス。死んだら人狼陣営の負け。
	 *
	 *
	 * 妖狐 = JACKAL;
	 *  - 	第三陣営。
	 */



	public EventListener el = null;
	public TeamManager tm = null;
	public PlayerManager pm = null;
	public GameManager gm = null;
	public SkullManager sm = null;
	public RoleManager rm = null;
	public ShopListener sl= null;
	public ChatListener cl = null;


	Random rnd = new Random();

	public void onEnable(){
		main = this;

		el = new EventListener(this);
		tm = new TeamManager(this);
		pm = new PlayerManager(this);
		gm = new GameManager(this);
		sm = new SkullManager(this);
		rm = new RoleManager(this);
		sl = new ShopListener(this);
		cl = new ChatListener(this);

		tm.ScoreboardCreate();
		saveConfig();
		Timer();

		new BlinkTimer().runTaskTimer(this, 1, 1);

		StartLocation = configLocation("Lobby");
		gm.Reset();

	}


	@Override
	public boolean onCommand(CommandSender s, Command c, String cl, String[] args) {
		boolean ret = false;
		Player p = null;

		if (s instanceof Player) p = (Player)s;

		//float pitch = rnd.nextInt(4) * 0.2F;

		if (c.getName().equalsIgnoreCase("s") || c.getName().equalsIgnoreCase("shop")) {

			if(ROLE.containsKey(p.getName())) {
				if(ROLE.get(p.getName()).equalsIgnoreCase("INNOCENT")) {
					ShopManager.openShop(p, ShopType.COMMON_SHOP);
					return ret;
				}
				if(ROLE.get(p.getName()).equalsIgnoreCase("MAGO")) {
					ShopManager.openShop(p, ShopType.COMMON_SHOP);
					return ret;
				}
				if(ROLE.get(p.getName()).equalsIgnoreCase("TYOUROU")) {
					ShopManager.openShop(p, ShopType.COMMON_SHOP);
					return ret;
				}
				if(ROLE.get(p.getName()).equalsIgnoreCase("DETECTIVE")) {
					ShopManager.openShop(p, ShopType.DETECTIVE_SHOP);
					return ret;
				}
				if(ROLE.get(p.getName()).equalsIgnoreCase("WEREWOLF")) {
					ShopManager.openShop(p, ShopType.WEREWOLF_SHOP);
					return ret;
				}
				if(ROLE.get(p.getName()).equalsIgnoreCase("HAKUROU")) {
					ShopManager.openShop(p, ShopType.WEREWOLF_SHOP);
					return ret;
				}
				if(ROLE.get(p.getName()).equalsIgnoreCase("JACKAL")) {
					ShopManager.openShop(p, ShopType.COMMON_SHOP);
					return ret;
				}


			} else {

			}

			return ret;
		}

		if (c.getName().equalsIgnoreCase("ww")) {

			if(!p.isOp()){
				return ret;
			}

			if(args.length > 0){
				String cmd = args[0];
				if(cmd != null){

					if(cmd.equalsIgnoreCase("int")){
						if (args.length > 1) {
							if (args.length > 2) {
								String warp = args[1];
								String n = args[2];

								getConfig().set(warp ,Integer.valueOf(n));
								saveConfig();
								reloadConfig();

								lib.sendPlayer(p, args[1] + "が"+args[2]+"にSETされました。");
							} else {
								lib.sendPlayer(p, args[1] +" の値を設定してください。");
							}
						} else {
							lib.sendPlayer(p,  args[1] +" の値を設定してください。");
						}
						return ret;
					}


					//ゲーム開始
					if(cmd.equalsIgnoreCase("start")){
						if (args.length > 4) {
							iMAGO = Integer.parseInt(args[1]);
							iDETECTIVE = Integer.parseInt(args[2]);
							iWEREWOLF = Integer.parseInt(args[3]);
							iJACKAL = Integer.parseInt(args[4]);
							if(GameStatus != 1) {
								lib.sendPlayer(p, "リセットしてください。");
							} else {
								gm.start(p);
							}

						} else {
							lib.sendPlayer(p, "引数が足りません。");
							lib.sendPlayer(p, "/ww start [孫の数][探偵の数][人狼の数][妖狐の数]");
							return ret;
						}
						return ret;
					}

					if(cmd.equalsIgnoreCase("reset")){
						gm.Reset();
						return ret;
					}

					if(cmd.equalsIgnoreCase("det")){
						if(DetectiveMode == false) {
							Bukkit.broadcastMessage(" 探偵モードオンッッッッッッッッッッッッ！！！！！！");
							DetectiveMode = true;
							return ret;
						} else {
							Bukkit.broadcastMessage(" 探偵モードオフッッッッッッッッッッッッ！！！！！！");
							DetectiveMode = false;
							return ret;
						}
					}

					if(cmd.equalsIgnoreCase("open")){
						gm.openRole();
						return ret;
					}

					if(cmd.equalsIgnoreCase("sf")){
						pm.GamePlayerStuff(p);
						return ret;
					}

					if(cmd.equalsIgnoreCase("shop1")) {
						ShopManager.openShop(p, ShopType.COMMON_SHOP);
					}
					if(cmd.equalsIgnoreCase("shop2")) {
						ShopManager.openShop(p, ShopType.WEREWOLF_SHOP);
					}
					if(cmd.equalsIgnoreCase("shop3")) {
						ShopManager.openShop(p, ShopType.DETECTIVE_SHOP);
					}


					if(cmd.equalsIgnoreCase("head")){
						if (args.length > 1) {

							lib.setSkin(args[1],p);

						} else {
							s.sendMessage("IDを指定してください。");
						}
						return ret;
					}

					if(cmd.equalsIgnoreCase("rh")){
						if (args.length > 1) {

							p.getInventory().addItem(sm.roleHead(args[1]));

						} else {
							s.sendMessage("IDを指定してください。");
						}
						return ret;
					}

					if(cmd.equalsIgnoreCase("loc")){
						if(args.length > 1){
							String st = args[1];
							getConfig().set(st +".x" , p.getLocation().getBlockX());
							getConfig().set(st +".y" , p.getLocation().getBlockY());
							getConfig().set(st +".z" , p.getLocation().getBlockZ());
							getConfig().set(st +".world", p.getLocation().getWorld().getName());

							saveConfig();
							reloadConfig();


							lib.sendPlayer(p, st + " のLocationを設定しました。");

						} else {

						}
						return ret;
					}


					if(cmd.equalsIgnoreCase("tp")){
						if(args.length > 1){
							String st = args[1];
							if(getConfig().contains(st)){
								configLocationTeleport(st, p, false);
							} else {
								lib.sendPlayer(p, "s");
							}
						}
						return ret;
					}


					if(cmd.equalsIgnoreCase("kick")){
						for(Player a : Bukkit.getOnlinePlayers()){
							if(a.isOp()){

							} else {
								a.kickPlayer("SHUT THE FUCK UP");
							}
						}
						return ret;
					}


				}
			}
		}
		return ret;
	}



	public void Timer(){

		//10秒
		this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){

			@Override
			public void run() {

				if(GameStatus == 1){
					for(Player a : Bukkit.getOnlinePlayers()){
						lib.sendActionBar(a,  ChatColor.GRAY + "" + ChatColor.BOLD + "ゲーム待機中");
					}
				}


				if(GameStatus == 2){

					if(Preparation != 0){

						Preparation--;

						for(Player a : Bukkit.getOnlinePlayers()){
							lib.sendActionBar(a,  ChatColor.GRAY + "ゲーム開始まで残り " + ChatColor.RED + Preparation + ChatColor.GRAY + " 秒");
						}

					} else {
						gm.roleOpen(iMAGO,iDETECTIVE, iWEREWOLF, iJACKAL);
					}
				}


				if(GameStatus == 3){

					PlayTime++;

					USER.setAllowFriendlyFire(true);

					for(Player a : Bukkit.getOnlinePlayers()){
						sm.haveSkullCheck(a);
					}

					if(PlayTime >= MoneyTime){
						for(Player a : Bukkit.getOnlinePlayers()){

							if(PLAYER.contains(a.getName()) && !DEATH.contains(a.getName())) {
								COIN.put(a.getName(), COIN.get(a.getName())+MoneyMoneyMoney);
								lib.sendTitleTarget(a, "", ChatColor.YELLOW + "" + MoneyMoneyMoney + " COIN" + ChatColor.RESET + " 付与されました。 ");
								//lib.SoundPlayer(a, Sound.ENTITY_PLAYER_LEVELUP, 1.4F);
							}
						}

						PlayTime = 0;
					}

					if(GameTime != 0){

						GameTime--;

					} else {
						//イノセントの勝ち
						gm.gameEnd(1, true);
					}

					for(Player a : Bukkit.getOnlinePlayers()){
						if(ROLE.containsKey(a.getName())) {
							lib.sendActionBar(a,  RoleManager.roleNameChanger(ROLE.get(a.getName())) + ChatColor.RESET + " | " + "COIN : " + COIN.get(a.getName()));
						}
					}
				}

				gm.setTabName();

			}
		}, 0L, 20L * 1);

	}


	public static void dropedItemsClear() {
		for(Entity e : StartLocation.getWorld().getNearbyEntities(StartLocation, 200, 100, 200)) {
			if(e instanceof Item){
				e.remove();
			}
		}
	}


	public Location configLocation(String xyz) {
		int x1 = getConfig().getInt(xyz + ".x");
		int y1 = getConfig().getInt(xyz + ".y");
		int z1 = getConfig().getInt(xyz + ".z");
		String st = getConfig().getString(xyz + ".world");
		World w1 = Bukkit.getWorld(st);
		Location loc = new Location(w1 , x1 +0.5D , y1 +0.5D , z1 +0.5D);
		return loc;
	}

	public void configLocationTeleport(String xyz , Player p, Boolean Spawn) {
		int x1 = getConfig().getInt(xyz + ".x");
		int y1 = getConfig().getInt(xyz + ".y");
		int z1 = getConfig().getInt(xyz + ".z");
		String st = getConfig().getString(xyz + ".world");
		World w1 = Bukkit.getWorld(st);

		Location loc = new Location(w1 , x1 +0.5D , y1 +0.5D , z1 +0.5D);

		loc.setYaw(p.getLocation().getYaw());
		loc.setPitch(p.getLocation().getPitch());
		p.teleport(loc);

		if(Spawn == true){
			p.setBedSpawnLocation(loc, true);
		}

		lib.SoundPlayer(p, Sound.ENTITY_ENDERMEN_TELEPORT, 0.2F);
	}

	public static Main getMain() {
		return main;
	}

}