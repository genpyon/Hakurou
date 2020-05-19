package com.genpyon.Item;

import static com.genpyon.ItemStack.GameItemManager.*;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum DetItemType {
	ARROW(ARROW_COST),
	//SENRIGAN(SENRIGAN_COST);
	;

	private final int cost;

	private DetItemType(int cost) {
		this.cost = cost;
	}

	public ItemStack toItemStack() {
		ItemStack item = null;
		switch (this) {
		/*case ARROW:
			item = new Arrow(Main.getMain()).getItemStack();
			break;*/

		default:
			item = new ItemStack(Material.AIR);
			break;
		}
		return item;
	}

	public ItemStack toItemIcon() {
		ItemStack item = null;
		switch (this) {
		/*case ARROW:
			item = new Arrow(Main.getMain()).getIcon();
			break;*/

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
