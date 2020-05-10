package com.genpyon.Item;

import static com.genpyon.ItemStack.GameItemManager.*;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.Detective.Uranai;

public enum DetItemType {
	URANAI_BOOK(URANAI_BOOK_COST), //占うヤツ
	;

	private final int cost;

	private DetItemType(int cost) {
		this.cost = cost;
	}

	public ItemStack toItemStack() {
		ItemStack item = null;
		switch (this) {
		case URANAI_BOOK:
			item = new Uranai(Main.getMain()).getItemStack();
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
		case URANAI_BOOK:
			item = new Uranai(Main.getMain()).getIcon();
			break;
		default:
			item = new ItemStack(Material.PAPER);
			break;
		}
		return item;
	}

	public int getCost() {
		return this.cost;
	}
}
