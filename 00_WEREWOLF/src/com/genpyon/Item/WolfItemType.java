package com.genpyon.Item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.Wolf.Teleport;

public enum WolfItemType {
	TELEPORT, /* テレポートアイテム */
	;

	public ItemStack toItemStack() {
		ItemStack item = null;
		switch (this) {
		case TELEPORT:
			item = new Teleport(Main.getMain()).getItemStack();
			break;
		default:
			item = new ItemStack(Material.PAPER);
			break;
		}
		return item;
	}

	public ItemStack toItemIcon() {
		ItemStack item = null;
		switch (this) {
		case TELEPORT:
			item = new Teleport(Main.getMain()).getIcon();
			break;
		default:
			item = new ItemStack(Material.PAPER);
			break;
		}
		return item;
	}
}
