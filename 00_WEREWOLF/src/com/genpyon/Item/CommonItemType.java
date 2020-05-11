package com.genpyon.Item;

import static com.genpyon.ItemStack.GameItemManager.*;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.Common.StoneSword;
import com.genpyon.Item.Common.TsuyoiBow;

public enum CommonItemType {
	STONE_SWORD(STONE_SWORD_COST),
	TSUYOI_BOW(TSUYOI_BOW_COST),
	SPEED_POT(SPEED_POT_COST),
	;

	private final int cost;

	private CommonItemType(int cost) {
		this.cost = cost;
	}

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
			item = new ItemStack(Material.AIR);
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
			item = new ItemStack(Material.AIR);
			break;
		}
		return item;
	}

	public int getCost() {
		return this.cost;
	}
}
