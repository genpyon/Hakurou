package com.genpyon.Item;

import static com.genpyon.ItemStack.GameItemManager.*;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.Wolf.Teleport;

public enum WolfItemType {
	TELEPORT(TELEPORT_COST), /* テレポートアイテム */
	;

	private final int cost;

	private WolfItemType(int cost) {
		this.cost = cost;
	}

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

	public int getCost() {
		return this.cost;
	}
}
