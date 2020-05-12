package com.genpyon.Item;

import static com.genpyon.ItemStack.GameItemManager.*;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.Wolf.Blink;
import com.genpyon.Item.Wolf.WolfChat;

public enum WolfItemType {
	TELEPORT(TELEPORT_COST), /* テレポートアイテム */
	BLINK(BLINK_COST),
	WEREWOLF_CHAT_ITEM(WEREWOLF_CHAT_COST),
	;

	private final int cost;

	private WolfItemType(int cost) {
		this.cost = cost;
	}

	public ItemStack toItemStack() {
		ItemStack item = null;
		switch (this) {
		case BLINK:
			item = new Blink(Main.getMain()).getItemStack();
			break;
		case WEREWOLF_CHAT_ITEM:
			item = new WolfChat(Main.getMain()).getItemStack();
			break;
		default:
			item = new ItemStack(Material.AIR);
			break;
		}
		return item;
	}

	public ItemStack toItemIcon() {
		ItemStack item = null;
		switch (this) {
		case BLINK:
			item = new Blink(Main.getMain()).getIcon();
			break;
		case WEREWOLF_CHAT_ITEM:
			item = new WolfChat(Main.getMain()).getIcon();
			break;
		default:
			item = new ItemStack(Material.AIR);
			break;
		}
		return item;
	}

	public int getCost() {
		return this.cost;
	}
}
