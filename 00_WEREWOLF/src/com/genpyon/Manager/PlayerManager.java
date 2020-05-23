package com.genpyon.Manager;

import java.util.Random;

import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import com.genpyon.Main;
import com.genpyon.ItemStack.GameItemManager;
import com.genpyon.Library.lib;



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

		p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
		p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);
		p.setFoodLevel(15);
		if(Main.StartLocation == null) {
			plugin.configLocationTeleport("Lobby", p, true);
		} else {
			p.teleport(Main.StartLocation);
			p.setBedSpawnLocation(Main.StartLocation, true);
		}

	}

	public void GameReadyPlayerStuff(Player a, Location loc) {
		a.setGameMode(GameMode.ADVENTURE);
		Main.ROLE.put(a.getName(), "INNOCENT");

		Main.INNOCENT.add(a.getName());
		Main.PLAYER.add(a.getName());
		Main.NONROLE.add(a.getName());

		loc.setYaw(a.getLocation().getYaw());
		loc.setPitch(a.getLocation().getPitch());
		a.teleport(loc);
		a.setBedSpawnLocation(loc, true);

		lib.SoundPlayer(a, Sound.ENTITY_ENDERMEN_TELEPORT, 0.2F);

		a.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
		a.setHealth(a.getHealthScale());
	}

	public void GamePlayerStuff(Player p){
		Inventory inv = p.getInventory();
		p.setSneaking(true);
		p.getInventory().clear();

		p.setGameMode(GameMode.ADVENTURE);
		inv.clear();

		p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);

		p.setHealth(p.getHealthScale());

		p.setFoodLevel(15);

		//初期アイテムの配布
		inv.setItem(0, GameItemManager.WOODEN_SWORD());
		inv.setItem(1, GameItemManager.WOODEN_BOW());
		inv.setItem(2, GameItemManager.ARROW_ITEM());

		if(Main.ROLE.containsKey(p.getName()) && Main.ROLE.get(p.getName()).equalsIgnoreCase("DETECTIVE")){
			inv.setItem(4, GameItemManager.URANAI_BOOK_ITEM());
		}

		if(Main.ROLE.containsKey(p.getName()) && Main.ROLE.get(p.getName()).equalsIgnoreCase("WEREWOLF")){
			inv.setItem(4, GameItemManager.WEREWOLF_BOOK());
			inv.setItem(5, GameItemManager.WEREWOLF_CHAT_ITEM());
		}

		if(Main.ROLE.containsKey(p.getName()) && Main.ROLE.get(p.getName()).equalsIgnoreCase("HAKUROU")){
			inv.setItem(5, GameItemManager.WEREWOLF_CHAT_ITEM());
		}


		inv.setItem(6, GameItemManager.PLAYERS_HEAD());
		inv.setItem(7, GameItemManager.FOUND_HEADS());
		inv.setItem(8, GameItemManager.SHOP_FLOWER());



		inv.setItem(6+9, GameItemManager.CO_INNOCENT());
		if(Main.TTTMode == false) {
			inv.setItem(7+9, GameItemManager.CO_MAGO());
			inv.setItem(8+9, GameItemManager.CO_TYOUROU());
		}

		inv.setItem(6+18, GameItemManager.CO_DETECTIVE());
		inv.setItem(7+18, GameItemManager.CO_WEREWOLF());
		if(Main.TTTMode == false) {
			inv.setItem(8+18, GameItemManager.CO_HAKUROU());
		}
		inv.setItem(6+27, GameItemManager.CO_JACKAL());
		//inv.setItem(7+27, GameItemManager.CO_MAGO());
		inv.setItem(8+27, GameItemManager.CO_GRAY());

		if(Main.ROLE.containsKey(p.getName()) && Main.ROLE.get(p.getName()).equalsIgnoreCase("DETECTIVE")){
			inv.setItem(4, GameItemManager.URANAI_BOOK_ITEM());
		}

		if(Main.ROLE.containsKey(p.getName()) && Main.ROLE.get(p.getName()).equalsIgnoreCase("WEREWOLF")){
			inv.setItem(4, GameItemManager.WEREWOLF_BOOK());
			inv.setItem(5, GameItemManager.WEREWOLF_CHAT_ITEM());
		}

		if(Main.ROLE.containsKey(p.getName()) && Main.ROLE.get(p.getName()).equalsIgnoreCase("HAKUROU")){
			inv.setItem(5, GameItemManager.WEREWOLF_CHAT_ITEM());
		}

		lib.setLeatherHead(p, Color.GRAY, GameItemManager.CO_GRAY_HEAD, Main.unbreakitem);
		Main.CO.put(p.getName(),"GRAY");

		p.getInventory().setHeldItemSlot(0);
	}

	public void RespawnStuff(Player p){
		Inventory inv = p.getInventory();
		p.setSneaking(true);


		p.setGameMode(GameMode.SPECTATOR);
		inv.clear();

		p.getAttribute(Attribute.GENERIC_ATTACK_SPEED).setBaseValue(100);
		p.setFoodLevel(15);

		p.teleport(Main.StartLocation);
		p.setBedSpawnLocation(Main.StartLocation, true);

	}

	public void DeathPlayer(Player p, Location loc){

		Main.DEATH.add(p.getName());

		p.getWorld().dropItemNaturally(loc, plugin.sm.roleHead(p.getName()));

		Inventory inv = p.getInventory();
		for(int i = 0; i < inv.getSize(); i++) {
			if(inv.getItem(i) !=null && inv.getItem(i).getType().equals(Material.SKULL_ITEM)) {
				p.getWorld().dropItemNaturally(loc, inv.getItem(i));
				inv.removeItem(inv.getItem(i));
			}
		}

		p.setGameMode(GameMode.SPECTATOR);
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
