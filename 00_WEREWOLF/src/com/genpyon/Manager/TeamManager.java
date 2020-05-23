package com.genpyon.Manager;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

import com.genpyon.Main;
import com.genpyon.Role.RoleManager;




public class TeamManager implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public TeamManager(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}



	public static Objective Sidebar;
	public static Team USER;


	public static void ScoreboardUpdate(Player p){

		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard Scoreboard = manager.getNewScoreboard();

		USER = Scoreboard.getTeam("USER");

		if(USER  != null){
			//USER.unregister();
		} else {
			USER = Scoreboard.registerNewTeam("USER");
		}

		USER.setOption(Option.COLLISION_RULE , OptionStatus.NEVER);

		if(Main.GameStatus != 3) {
			USER.setAllowFriendlyFire(false);
		} else {
			USER.setAllowFriendlyFire(true);
		}


		for(Player a : Bukkit.getOnlinePlayers()){
			if(USER.hasEntry(a.getName())){

			} else {
				USER.addEntry(a.getName());
			}
		}


		SidebarUpdate(Scoreboard , p);
		if(Bukkit.getOnlinePlayers().contains(p)){
			p.setScoreboard(Scoreboard);
		} else {
			//return;
		}
	}

	public static void SidebarUpdate(Scoreboard Scoreboard, Player p) {
		String name = p.getName();

		Sidebar = Scoreboard.getObjective("Sidebar");

		if(Sidebar  != null){
			Sidebar.unregister();
		}

		Sidebar = Scoreboard.registerNewObjective("Sidebar", "dummy");
		Sidebar.setDisplaySlot(DisplaySlot.SIDEBAR);
		Sidebar.setDisplayName(ChatColor.GOLD + name);
		int bar = 0;
		String Mode = null;

		if(Main.TTTMode == true) {
			Mode = ChatColor.GOLD + "TTT";
		} else {
			Mode = ChatColor.WHITE + "" +  ChatColor.BOLD + "白狼";
		}

		//待機中
		if(Main.GameStatus == 1 || Main.GameStatus == 2) {
			bar = bar-1;
			Score score0 = Sidebar.getScore(" ");
			score0.setScore(bar);

			bar = bar-1;
			Score score1 = Sidebar.getScore(ChatColor.GRAY + "ゲームモード : " + Mode);
			score1.setScore(bar);

			bar = bar-1;
			Score score2 = Sidebar.getScore("  ");
			score2.setScore(bar);

			bar = bar-1;
			Score score3 = Sidebar.getScore("■ 設定情報");
			score3.setScore(bar);

			bar = bar-1;
			Score score4 = Sidebar.getScore(ChatColor.GRAY + "- 孫    : " + ChatColor.RED + Main.iMAGO);
			score4.setScore(bar);

			bar = bar-1;
			Score score5 = Sidebar.getScore(ChatColor.GRAY + "- 探偵 : " + ChatColor.RED + Main.iDETECTIVE);
			score5.setScore(bar);

			bar = bar-1;
			Score score6 = Sidebar.getScore(ChatColor.GRAY + "- 人狼 : " + ChatColor.RED + Main.iWEREWOLF);
			score6.setScore(bar);

			bar = bar-1;
			Score score7 = Sidebar.getScore(ChatColor.GRAY + "- 妖狐 : " + ChatColor.RED + Main.iJACKAL);
			score7.setScore(bar);

			bar = bar-1;
			Score score8 = Sidebar.getScore(ChatColor.GRAY + "- 制限時間 : " + ChatColor.RED + Main.GameTime);
			score8.setScore(bar);

			bar = bar-1;
			Score score9 = Sidebar.getScore(ChatColor.GRAY + "- 準備時間 : " + ChatColor.RED + Main.Preparation);
			score9.setScore(bar);

			bar = -99;
			Score score99 = Sidebar.getScore(ChatColor.GRAY + "--------------");
			score99.setScore(bar);
		}

		if(Main.GameStatus == 3 || Main.GameStatus == 4) {

			if(!Main.ROLE.containsKey(name) && !Main.COIN.containsKey(name) && !Main.CO.containsKey(name)) {
				return;
			}

			bar = bar-1;
			Score score0 = Sidebar.getScore(" ");
			score0.setScore(bar);

			bar = bar-1;
			Score score1 = Sidebar.getScore(ChatColor.GRAY + "役職 : " + RoleManager.roleNameChanger(Main.ROLE.get(name)));
			score1.setScore(bar);

			bar = bar-1;
			Score score2 = Sidebar.getScore(ChatColor.GRAY + "陣営 : " + RoleManager.bookRoleNameChanger(Main.ROLE.get(name)));
			score2.setScore(bar);

			bar = bar-1;
			Score score2_2 = Sidebar.getScore(ChatColor.GRAY + "宣言 : " + RoleManager.roleNameChanger(Main.CO.get(name)));
			score2_2.setScore(bar);

			bar = bar-1;
			Score score3 = Sidebar.getScore("  ");
			score3.setScore(bar);

			bar = bar-1;
			Score score4 = Sidebar.getScore(ChatColor.YELLOW + "COIN : " + ChatColor.RED + Main.COIN.get(name));
			score4.setScore(bar);

			bar = bar-1;
			Score score5 = Sidebar.getScore(ChatColor.GRAY + "残り時間 : " + ChatColor.RED + Main.GameTime);
			score5.setScore(bar);



			bar = -99;
			Score score99 = Sidebar.getScore(ChatColor.GRAY + "--------------");
			score99.setScore(bar);
		}
	}

}
