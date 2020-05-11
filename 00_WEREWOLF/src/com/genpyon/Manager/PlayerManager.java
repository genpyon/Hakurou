package com.genpyon.Manager;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import com.genpyon.Main;
import com.genpyon.ItemStack.GameItemManager;



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
		p.getInventory().clear();

		p.setGameMode(GameMode.ADVENTURE);
		inv.clear();

		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
		p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);
		p.setHealth(40);
		p.setFoodLevel(15);

		plugin.configLocationTeleport("Lobby", p, true);

		if(Main.USER.hasEntry(p.getName())){

		} else {
			Main.USER.addEntry(p.getName());
		}

	}

	public void GamePlayerStuff(Player p){
		Inventory inv = p.getInventory();
		p.setSneaking(true);
		p.getInventory().clear();

		p.setGameMode(GameMode.ADVENTURE);
		inv.clear();

		p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);

		p.setHealth(40);

		p.setFoodLevel(15);

		//初期アイテムの配布
		inv.setItem(0, GameItemManager.WOODEN_SWORD());
		inv.setItem(1, GameItemManager.WOODEN_BOW());
		inv.setItem(2, GameItemManager.ARROW_ITEM());

		if(Main.ROLE.get(p.getName()).equalsIgnoreCase("DETECTIVE")){
			inv.setItem(4, GameItemManager.URANAI_BOOK_ITEM());
		}
	}

	public void DeathPlayer(Player p, Location loc){

		Bukkit.broadcastMessage(p.getName() + "が死んだ。");
		Main.DEATH.add(p.getName());

		p.getWorld().dropItemNaturally(loc, plugin.sm.roleHead(p.getName()));

		Inventory inv = p.getInventory();
		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(i) !=null && inv.getItem(i).getType().equals(Material.SKULL_ITEM)) {
				p.getWorld().dropItemNaturally(loc, inv.getItem(i));
				inv.removeItem(inv.getItem(i));
			}
		}
	}


	public void RespawnStuff(Player p){
		Inventory inv = p.getInventory();
		p.setSneaking(true);


		p.setGameMode(GameMode.SPECTATOR);
		inv.clear();

		p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);
		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(40);
		p.setHealth(40);

		p.setFoodLevel(15);

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
