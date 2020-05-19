package com.genpyon.Event;

import java.util.Random;

import org.bukkit.event.Listener;

import com.genpyon.Main;



public class ShopListener implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public ShopListener(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;
	}


}


