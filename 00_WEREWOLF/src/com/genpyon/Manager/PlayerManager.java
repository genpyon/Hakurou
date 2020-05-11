package com.genpyon.Manager;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.ItemStack.GameItemManager;
import com.genpyon.Library.lib;

import net.md_5.bungee.api.ChatColor;



public class PlayerManager implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public PlayerManager(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}

	@EventHandler
	public void comingOutEvent(InventoryClickEvent b) {
		Player p = (Player) b.getWhoClicked();
		ItemStack click = b.getCurrentItem();
		String role = null;

		if(click == null){
			return;
		}

		if (click.equals(GameItemManager.CO_INNOCENT())){
			role = ChatColor.GREEN + "村人" + ChatColor.RESET;
			Main.CO.put(p.getName(),"INNOCENT");
			b.setCancelled(true);
		}

		if (click.equals(GameItemManager.CO_MAGO())){
			role = ChatColor.GREEN + "孫" + ChatColor.RESET;
			Main.CO.put(p.getName(),"MAGO");
			b.setCancelled(true);
		}

		if (click.equals(GameItemManager.CO_TYOUROU())){
			role = ChatColor.DARK_GREEN + "長老" + ChatColor.RESET;
			Main.CO.put(p.getName(),"TYOUROU");
			b.setCancelled(true);
		}

		if (click.equals(GameItemManager.CO_DETECTIVE())){
			role = ChatColor.BLUE + "探偵" + ChatColor.RESET;
			Main.CO.put(p.getName(),"DETECTIVE");
			b.setCancelled(true);
		}

		if (click.equals(GameItemManager.CO_WEREWOLF())){
			role = ChatColor.RED + "人狼" + ChatColor.RESET;
			Main.CO.put(p.getName(),"WEREWOLF");
			b.setCancelled(true);
		}

		if (click.equals(GameItemManager.CO_HAKUROU())){
			role = ChatColor.DARK_RED + "白狼" + ChatColor.RESET;
			Main.CO.put(p.getName(),"HAKUROU");
			b.setCancelled(true);
		}

		if (click.equals(GameItemManager.CO_JACKAL())){
			role = ChatColor.AQUA + "妖狐" + ChatColor.RESET;
			Main.CO.put(p.getName(),"JACKAL");
			b.setCancelled(true);
		}

		if (click.equals(GameItemManager.CO_GRAY())){
			role = ChatColor.GRAY + "グレー" + ChatColor.RESET;
			Main.CO.put(p.getName(),"GRAY");
			b.setCancelled(true);
		}

		if(role != null) {
			String sengen = ChatColor.RED + " [!] " + ChatColor.YELLOW + p.getName() + ChatColor.RESET +  " は " + role + " を宣言しました。";
			Bukkit.broadcastMessage(sengen);
			lib.SoundAllPlayer(Sound.ENTITY_CHICKEN_EGG, 1.6F);
		} else {
			return;
		}

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
		p.setFoodLevel(15);
		if(Main.StartLocation == null) {
		plugin.configLocationTeleport("Lobby", p, true);
		} else {
			p.teleport(Main.StartLocation);
		}

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

		inv.setItem(8, GameItemManager.SHOP_FLOWER());

		inv.setItem(6+9, GameItemManager.CO_INNOCENT());
		inv.setItem(7+9, GameItemManager.CO_MAGO());
		inv.setItem(8+9, GameItemManager.CO_TYOUROU());

		inv.setItem(6+18, GameItemManager.CO_DETECTIVE());
		inv.setItem(7+18, GameItemManager.CO_WEREWOLF());
		inv.setItem(8+18, GameItemManager.CO_HAKUROU());

		inv.setItem(6+27, GameItemManager.CO_JACKAL());
		//inv.setItem(7+27, GameItemManager.CO_MAGO());
		inv.setItem(8+27, GameItemManager.CO_GRAY());

		if(Main.ROLE.containsKey(p.getName()) && Main.ROLE.get(p.getName()).equalsIgnoreCase("DETECTIVE")){
			inv.setItem(4, GameItemManager.URANAI_BOOK_ITEM());
		}

		if(Main.ROLE.containsKey(p.getName()) && Main.ROLE.get(p.getName()).equalsIgnoreCase("WEREWOLF")){
			inv.setItem(4, GameItemManager.WEREWOLF_BOOK());
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
