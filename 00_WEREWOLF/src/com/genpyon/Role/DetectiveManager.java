package com.genpyon.Role;

import java.util.Random;

import org.bukkit.event.Listener;

import com.genpyon.Main;




public class DetectiveManager implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public DetectiveManager(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}

	/*
	private boolean isPlayer(String string) {
		for (String s: plugin.ROLE.keySet()) {
			if (string.contains(s)) return true;
		}
		return false;
	}*/

}
