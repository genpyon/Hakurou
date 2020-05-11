package com.genpyon.Item.Wolf;

import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.AbstractItem;
import com.genpyon.ItemStack.GameItemManager;

public class Blink extends AbstractItem {

	public Blink(Main plugin) {
		super(plugin);
	}

	@Override
	public ItemStack getItemStack() {
		return GameItemManager.BLINK_ITEM();
	}

	@Override
	public ItemStack getIcon() {
		return GameItemManager.BLINK_ICON();
	}

}
