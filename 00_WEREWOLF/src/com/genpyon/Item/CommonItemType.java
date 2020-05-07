package com.genpyon.Item;

import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.Common.StoneSword;
import com.genpyon.Item.Common.TsuyoiBow;

public enum CommonItemType {
	STONE_SWORD,
	TSUYOI_BOW,
	SPEED_POT,
	;

	public ItemStack toItemStack() {
		ItemStack item = null;
		switch (this) {
		case STONE_SWORD:
			item = new StoneSword(Main.getMain()).getItemStack();
			break;
		case TSUYOI_BOW:
			item = new TsuyoiBow(Main.getMain()).getItemStack();
			break;
		default:
			break;
		}
		return item;
	}

	public ItemStack toItemIcon() {
		ItemStack item = null;
		switch (this) {
		case STONE_SWORD:
			item = new StoneSword(Main.getMain()).getIcon();
			break;
		case TSUYOI_BOW:
			item = new TsuyoiBow(Main.getMain()).getIcon();
			break;
		default:
			break;
		}
		return item;
	}
}
