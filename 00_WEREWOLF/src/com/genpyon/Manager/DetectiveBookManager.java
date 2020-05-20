package com.genpyon.Manager;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Event.ChatListener;
import com.genpyon.ItemStack.GameItemManager;
import com.genpyon.Library.lib;
import com.genpyon.Role.RoleManager;

import net.md_5.bungee.api.ChatColor;



public class DetectiveBookManager implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public DetectiveBookManager(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;
	}


	public static Inventory playerGetHeads(Player p) {

		int invSize = Main.PLAYER.size();

		if(invSize <= 9) {
			invSize = 9;
		} else if(invSize <= 18) {
			invSize = 18;
		} else if(invSize <= 27) {
			invSize = 27;
		} else if(invSize <= 36) {
			invSize = 36;
		}


		Inventory inv = Bukkit.createInventory(null, invSize, GameItemManager.PLAYERS_HEAD_INV_NAME);

		for(String s : Main.PLAYER) {

			if(Main.TTTMode == true) {

				if(Main.ROLE.containsKey(p.getName())&& Main.ROLE.get(p.getName()).equalsIgnoreCase("WEREWOLF")) {
					if(Main.ROLE.containsKey(s) && Main.ROLE.get(s).equalsIgnoreCase("WEREWOLF")) {

						inv.addItem(SkullManager.baseHeads(s , Enchantment.DURABILITY , 1,
								ChatColor.RESET + "宣言: " + ChatListener.coNameChanger(s),
								ChatColor.RESET + "役職: " + RoleManager.roleNameChanger(Main.ROLE.get(s))));
					} else {
						inv.addItem(SkullManager.baseHeads(s , null , 0,
								ChatColor.RESET + "宣言: " + ChatListener.coNameChanger(s)));
					}


				} else {
				inv.addItem(SkullManager.baseHeads(s , null , 0,
						ChatColor.RESET + "宣言: " + ChatListener.coNameChanger(s)));
				}
			} else {
				inv.addItem(SkullManager.baseHeads(s , null , 0,ChatColor.RESET + "情報無し"));
			}
		}

		return inv;
	}


	public static Inventory foundGetHeads() {

		int invSize = Main.PLAYER.size();

		if(invSize <= 9) {
			invSize = 9;
		} else if(invSize <= 18) {
			invSize = 18;
		} else if(invSize <= 27) {
			invSize = 27;
		} else if(invSize <= 36) {
			invSize = 36;
		}


		Inventory inv = Bukkit.createInventory(null, invSize, GameItemManager.FOUND_HEADS_INV_NAME);

		for(String s : Main.FOUND.keySet()) {
			if(Main.TTTMode == true) {
				inv.addItem(SkullManager.baseHeads(s, null , 0 ,ChatColor.RESET + "役職: " + RoleManager.bookRoleNameChanger(Main.ROLE.get(s))));
			} else {
				inv.addItem(SkullManager.baseHeads(s, null , 0, ChatColor.RESET + "情報無し"));
			}
		}

		return inv;
	}


	public static Inventory uranaiGetHeads() {

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
			if(Main.FOUND.containsKey(Main.PLAYER.get(i))) {

			} else {
				inv.addItem(SkullManager.uranaiHeads(Main.PLAYER.get(i), Main.PLAYER.get(i) + "の頭を見る"));
			}
		}

		return inv;

	}

	/**
	 * 占い本の処理関係
	 * @param e event
	 */


	/*@EventHandler
	public void uranaiBookEvent (PlayerInteractEvent b){
		final Player p = b.getPlayer();
		//Inventory inv = p.getInventory();
		Action act = b.getAction();

		if(act == Action.LEFT_CLICK_AIR || act == Action.RIGHT_CLICK_AIR || act == Action.LEFT_CLICK_BLOCK || act == Action.RIGHT_CLICK_BLOCK){
			if(p.getInventory().getItemInMainHand() != null  && ItemManager.hasMainHand(p, GameItemManager.URANAI_BOOK_ITEM())) {
				if(p.hasCooldown(Material.BOOK)) {
					lib.sendPlayer(p, " まだ使うトキではない。");
					return;
				}
				p.openInventory(uranaiGetHeads());
			}
		}
	}*/


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
					p.setCooldown(Material.BOOK, 20*60);


				} else {
					//Bukkit.broadcastMessage("そんなことはありませんエラー");
				}
			}
			b.setCancelled(true);
		}

		if (openInv.getName().equalsIgnoreCase(GameItemManager.PLAYERS_HEAD_INV_NAME)){
			b.setCancelled(true);
		}

		if (openInv.getName().equalsIgnoreCase(GameItemManager.FOUND_HEADS_INV_NAME)){
			b.setCancelled(true);
		}

		return;
	}
}


