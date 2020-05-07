package com.genpyon.Item;

import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.Detective.Uranai;

public enum DetItemType {
	URANAI, //占うヤツ
	;

	public ItemStack toItemStack() {
		ItemStack item = null;
		switch (this) {
		case URANAI:
			item = new Uranai(Main.getMain()).getItemStack();
			break;
		default:
			break;
		}
		return item;
	}

	public ItemStack toItemIcon() {
		ItemStack item = null;
		switch (this) {
		case URANAI:
			item = new Uranai(Main.getMain()).getIcon();
			break;
		default:
			break;
		}
		return item;
	}
}
