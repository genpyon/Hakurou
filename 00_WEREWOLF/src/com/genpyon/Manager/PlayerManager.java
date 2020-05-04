package com.genpyon.Manager;

import java.util.Random;

import org.bukkit.GameMode;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import com.genpyon.Main;



public class PlayerManager implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public PlayerManager(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}

	public void DefaultStuff(Player p){
		Inventory inv = p.getInventory();
		p.setSneaking(false);


		p.setGameMode(GameMode.ADVENTURE);
		inv.clear();

		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
		p.setHealth(40);
		p.setFoodLevel(20);

		plugin.configLocationTeleport("Lobby", p, true);

		if(plugin.USER.hasEntry(p.getName())){

		} else {
			plugin.USER.addEntry(p.getName());
		}

	}

	public void GamePlayerStuff(Player p){
		Inventory inv = p.getInventory();
		p.setSneaking(true);


		p.setGameMode(GameMode.ADVENTURE);
		inv.clear();

		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
		p.setHealth(40);
		p.setFoodLevel(20);

		p.getInventory().clear();

	}

	/*

	@EventHandler
	public void onDamage(EntityDamageEvent b){
		if(b.getEntity() instanceof Player){
			b.setCancelled(true);
			if (b.getCause() == DamageCause.VOID){
				plugin.configLocationTeleport("Lobby", (Player)b.getEntity(), true);
			}
		} else {
			return;
		}
	}*/



}
