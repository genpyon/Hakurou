package com.genpyon.Event;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.ItemStack.GameItemManager;
import com.genpyon.Library.lib;
import com.genpyon.Manager.ItemManager;
import com.genpyon.Manager.SkullManager;



public class ShopListener implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public ShopListener(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;
	}


	public Inventory uranaiGetHeads() {

		int RoleSize = Main.PLAYER.size();
		int Size = Main.PLAYER.size();
		//Bukkit.broadcastMessage(RoleSize + " : 1");


		if(RoleSize <= 9) {
			RoleSize = 9;
		} else if(RoleSize <= 18) {
			RoleSize = 18;
		} else if(RoleSize <= 27) {
			RoleSize = 27;
		} else if(RoleSize <= 36) {
			RoleSize = 36;
		}



		//Bukkit.broadcastMessage(RoleSize + " : 2");

		Inventory inv = Bukkit.createInventory(null, RoleSize, GameItemManager.URANAI_INV_NAME);

		for (int i = 0; i < Size; i++){
			//Bukkit.broadcastMessage(Size + " : 3");
			inv.addItem(SkullManager.uranaiHeads(Main.PLAYER.get(i), Main.PLAYER.get(i) + "の頭を見る"));
		}

		return inv;

	}


	@EventHandler
	public void uranaiBookEvent (PlayerInteractEvent b){
		final Player p = b.getPlayer();
		//Inventory inv = p.getInventory();
		Action act = b.getAction();

		if(act == Action.LEFT_CLICK_AIR || act == Action.RIGHT_CLICK_AIR || act == Action.LEFT_CLICK_BLOCK || act == Action.RIGHT_CLICK_BLOCK){
			if(ItemManager.hasMainHand(p, GameItemManager.URANAI_BOOK_ITEM())) {
				p.openInventory(uranaiGetHeads());
			}

		}
	}


	@EventHandler
	public void uranaiBookInventory(InventoryClickEvent b) {
		Player p = (Player) b.getWhoClicked();
		ItemStack click = b.getCurrentItem();
		Inventory openInv = b.getInventory();

		if(click == null){
			return;
		}

		if (openInv.getName().equalsIgnoreCase(GameItemManager.URANAI_INV_NAME)){
			if(click.getItemMeta() != null) {
				String name = Main.ROLE.get(click.getItemMeta().getDisplayName());
				if(click.getItemMeta().getDisplayName().equalsIgnoreCase(name)) {
					lib.sendPlayer(p, "それは" + Main.ROLE.get(name));
				} else {
					lib.sendPlayer(p, "それじゃないです!");
				}
				b.setCancelled(true);
			}
			return;
		}
	}



}
