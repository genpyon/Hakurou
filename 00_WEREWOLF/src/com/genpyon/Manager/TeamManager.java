package com.genpyon.Manager;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

import com.genpyon.Main;




public class TeamManager implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public TeamManager(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}

	public void ScoreboardCreate(){

		ScoreboardManager manager = Bukkit.getScoreboardManager();
		Scoreboard board = manager.getMainScoreboard();


		Main.USER = board.getTeam("USER");

		if(Main.USER  != null){
			Main.USER.unregister();
		}

		Main.USER = board.registerNewTeam("USER");
		Main.USER.setOption(Option.COLLISION_RULE , OptionStatus.NEVER);


	}

}
