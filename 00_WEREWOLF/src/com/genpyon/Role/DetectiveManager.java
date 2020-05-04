package com.genpyon.Role;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.genpyon.Main;




public class DetectiveManager implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public DetectiveManager(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}
	@EventHandler
	public void playerHeadChange (PlayerInteractEvent b){
		Player p = b.getPlayer();

		Action act = b.getAction();

		if(act == Action.LEFT_CLICK_AIR || act == Action.RIGHT_CLICK_AIR || act == Action.LEFT_CLICK_BLOCK || act == Action.RIGHT_CLICK_BLOCK){


			if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType().equals(Material.SKULL_ITEM)){

				String name = p.getInventory().getItemInMainHand().getItemMeta().getLore().get(1);

				Bukkit.broadcastMessage("それです!!1 - " + name);

				if(isPlayer(name)){

					Bukkit.broadcastMessage("それです!!2");
					p.getInventory().remove(p.getInventory().getItemInMainHand());
					p.getInventory().addItem(plugin.sm.roleHeadChange(name));

					Bukkit.broadcastMessage("それです!!3");
					return;
				} else {
					Bukkit.broadcastMessage("それですけど、それじゃないです。");
					Bukkit.broadcastMessage(plugin.ROLE.toString());
				}

			} else {
				//Bukkit.broadcastMessage("それじゃありません!!");
			}



		} else {

		}
	}

	private boolean isPlayer(String string) {
		for (String s: plugin.ROLE.keySet()) {
			if (s.equalsIgnoreCase(string)) return true;
		}
		return false;
	}

}
