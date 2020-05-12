package com.genpyon.Item;

import static com.genpyon.ItemStack.GameItemManager.*;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.Wolf.Blink;

public enum WolfItemType {
	TELEPORT(TELEPORT_COST), /* テレポートアイテム */
	BLINK(BLINK_COST),
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
