package com.genpyon.Event;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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
import com.genpyon.Role.RoleManager;

import net.md_5.bungee.api.ChatColor;



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

	/**
	 * 占い本の処理関係
	 * @param e event
	 */


	@EventHandler
	public void uranaiBookEvent (PlayerInteractEvent b){
		final Player p = b.getPlayer();
		//Inventory inv = p.getInventory();
		Action act = b.getAction();

		if(act == Action.LEFT_CLICK_AIR || act == Action.RIGHT_CLICK_AIR || act == Action.LEFT_CLICK_BLOCK || act == Action.RIGHT_CLICK_BLOCK){
			if(ItemManager.hasMainHand(p, GameItemManager.URANAI_BOOK_ITEM())) {
				if(p.hasCooldown(Material.BOOK)) {
					lib.sendPlayer(p, " まだ使うトキではない。");
					return;

				}
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

				String name = click.getItemMeta().getDisplayName();

				if(Main.ROLE.containsKey(name)) {

					String role = Main.ROLE.get(name);
					lib.sendPlayer(p, "");
					lib.sendPlayer(p, " " + ChatColor.BLUE + GameItemManager.URANAI_INV_NAME + ChatColor.RESET + " を使用し");
					lib.sendPlayer(p, "  " + ChatColor.GOLD +  name + ChatColor.WHITE +" は " + RoleManager.bookRoleNameChanger(role) + " であることが分かりました。");
					lib.sendPlayer(p, "");

					p.closeInventory();
					p.setCooldown(Material.BOOK, 20*90);


				} else {

					Bukkit.broadcastMessage("そんなことはありませんエラー");
				}
			}
			b.setCancelled(true);
		}
		return;
	}
}


