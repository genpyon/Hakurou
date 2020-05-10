package com.genpyon.Item.Wolf;

import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.AbstractItem;
import com.genpyon.ItemStack.GameItemManager;

public class Teleport extends AbstractItem {

	public Teleport(Main plugin) {
		super(plugin);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public ItemStack getItemStack() {
		return GameItemManager.TELEPORT_ITEM();
	}

	@Override
	public ItemStack getIcon() {
		return GameItemManager.TELEPORT_ICON();
	}

}
