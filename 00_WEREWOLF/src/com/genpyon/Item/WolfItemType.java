package com.genpyon.Item;

import org.bukkit.inventory.ItemStack;

import com.genpyon.Item.Wolf.Teleport;

public enum WolfItemType {
	TELEPORT, /* テレポートアイテム */
	;

	public ItemStack toItemStack() {
		ItemStack item = null;
		switch (this) {
		case TELEPORT:
			item = new Teleport().getItemStack();
			break;
		default:
			break;
		}
		return item;
	}

	public ItemStack toItemIcon() {
		ItemStack item = null;
		switch (this) {
		case TELEPORT:
			item = new Teleport().getIcon();
			break;
		default:
			break;
		}
		return item;
	}
}
