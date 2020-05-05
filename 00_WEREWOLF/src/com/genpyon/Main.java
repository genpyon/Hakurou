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
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

import com.genpyon.Event.EventListener;
import com.genpyon.Library.lib;
import com.genpyon.Manager.GameManager;
import com.genpyon.Manager.PlayerManager;
import com.genpyon.Manager.SkullManager;
import com.genpyon.Manager.TeamManager;
import com.genpyon.Role.DetectiveManager;

import net.md_5.bungee.api.ChatColor;



public class Main extends JavaPlugin implements Listener {

	public Team USER;

	//1待機 2準備 3ゲーム 4終了
	public int GameStatus = 1;

	public int GameTime = getConfig().getInt("GAMETIME");
	public int Preparation = getConfig().getInt("PREPARATION");

	public Location StartLocation = null;


	public int iWEREWOLF = 0;
	public int iDETECTIVE = 0;
	public int iJACKAL = 0;




	//全員の役職まとめ
	public HashMap <String, String> ROLE = new HashMap<String, String>();
	public ArrayList<String> PLAYER = new ArrayList<String>();
	public ArrayList<String> DEATH = new ArrayList<String>();


	public ArrayList<String> NONROLE = new ArrayList<String>();
	public ArrayList<String> TYOUROU = new ArrayList<String>();
	public ArrayList<String> WEREWOLF = new ArrayList<String>();
	public ArrayList<String> HAKUROU = new ArrayList<String>();

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
	public DetectiveManager dm = null;


	Random rnd = new Random();

	public void onEnable(){

		el = new EventListener(this);
		tm = new TeamManager(this);
		pm = new PlayerManager(this);
		gm = new GameManager(this);
		sm = new SkullManager(this);
		dm = new DetectiveManager(this);


		tm.ScoreboardCreate();
		saveConfig();
		Timer();

		gm.Reset();

	}



	@Override
	public boolean onCommand(CommandSender s, Command c, String cl, String[] args) {
		boolean ret = false;
		Player p = null;

		if (s instanceof Player) p = (Player)s;

		//float pitch = rnd.nextInt(4) * 0.2F;



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
						if (args.length > 3) {
							iWEREWOLF = Integer.parseInt(args[1]);
							iDETECTIVE = Integer.parseInt(args[2]);
							iJACKAL = Integer.parseInt(args[3]);

							gm.start(p);

						} else {
							lib.sendPlayer(p, "引数が足りません。");
							lib.sendPlayer(p, "/ww ro [人狼の数] [探偵の数] [妖狐の数]");
							return ret;
						}
						return ret;
					}

					if(cmd.equalsIgnoreCase("reset")){
						gm.Reset();
						return ret;
					}

					if(cmd.equalsIgnoreCase("open")){
						gm.roleOpen();
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


					if(cmd.equalsIgnoreCase("sneak")){
						if (args.length > 1) {
							Player t = Bukkit.getServer().getPlayer(args[1]);

							if (t == null) {
								s.sendMessage(args[1] + " が存在しません。");
								return true;

							}
							if(t !=null){

								if(t.isSneaking() == false){
									t.setSneaking(true);
									lib.sendPlayer(p, "スニーク状態");
								} else {
									t.setSneaking(false);
									lib.sendPlayer(p, "スニーク状態じゃないよ");
								}
							}

						} else {
							s.sendMessage("IDを指定してください。");
						}
						return ret;
					}

					if(cmd.equalsIgnoreCase("test2")){
						return ret;
					}

					if(cmd.equalsIgnoreCase("game")){
						if(GameStatus == 1){
							GameStatus = 2;
							lib.sendPlayer(p, "GameStatus is "+ GameStatus);
							return ret;
						}

						if(GameStatus == 2){
							GameStatus = 3;
							lib.sendPlayer(p, "GameStatus is "+ GameStatus);
							return ret;
						}

						if(GameStatus == 3){
							GameStatus = 1;
							lib.sendPlayer(p, "GameStatus is "+ GameStatus);
							return ret;
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


				if(GameStatus == 2){

					if(Preparation != 0){

						Preparation--;

					} else {
						gm.roleOpen(iWEREWOLF, iDETECTIVE, iJACKAL);
					}
				}


				if(GameStatus == 3){

					for(Player a : Bukkit.getOnlinePlayers()){
						sm.haveSkullCheck(a);
					}

					if(GameTime != 0){

						GameTime--;

					} else {
						//イノセントの勝ち
						gm.gameEnd(1, true);
					}
				}

				gm.setTabName();



				for(Player a : Bukkit.getOnlinePlayers()){
					lib.sendActionBar(a, Preparation + " / " + GameTime + " / " + ChatColor.RED + ROLE.get(a.getName()) + ChatColor.RESET + " / " + "Status : " + GameStatus );
				}

			}
		}, 0L, 20L * 1);

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

}