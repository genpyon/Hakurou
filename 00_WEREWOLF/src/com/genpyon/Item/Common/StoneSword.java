package com.genpyon.Item.Common;

import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.AbstractItem;
import com.genpyon.ItemStack.GameItemManager;

public class StoneSword extends AbstractItem {

	public StoneSword(Main plugin) {
		super(plugin);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public ItemStack getItemStack() {
		ItemStack is = GameItemManager.STONE_SWORD_ITEM();
		return is;
	}

	@Override
	public ItemStack getIcon() {
		ItemStack is = GameItemManager.STONE_SWORD_ICON();
		return is;
	}

}
