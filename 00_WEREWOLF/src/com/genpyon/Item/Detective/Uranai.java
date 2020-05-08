package com.genpyon.Item.Detective;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.AbstractItem;
import com.genpyon.ItemStack.GameItemManager;
import com.genpyon.Manager.ItemManager;
import com.genpyon.Manager.SkullManager;

public class Uranai extends AbstractItem {


	public Uranai(Main plugin) {
		super(plugin);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public ItemStack getItemStack() {
		ItemStack is = GameItemManager.URANAI_BOOK_ITEM();
		return is;
	}

	@Override
	public ItemStack getIcon() {
		ItemStack is = GameItemManager.URANAI_BOOK_ICON();
		return is;
	}

	public Inventory uranaiGetHeads() {

		int RoleSize = Main.PLAYER.size();
		int Size = Main.PLAYER.size();
		Bukkit.broadcastMessage(RoleSize + " : 1");


		if(RoleSize <= 9) {
			RoleSize = 9;
		} else if(RoleSize <= 18) {
			RoleSize = 18;
		} else if(RoleSize <= 27) {
			RoleSize = 27;
		} else if(RoleSize <= 36) {
			RoleSize = 36;
		}



		Bukkit.broadcastMessage(RoleSize + " : 2");

		Inventory inv = Bukkit.createInventory(null, RoleSize, ChatColor.BOLD + "探偵の手帳");

		for (int i = 0; i < Size; i++){
			Bukkit.broadcastMessage(Size + " : 3");
			inv.addItem(SkullManager.uranaiHeads(Main.PLAYER.get(i), "すごい"));
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
}
