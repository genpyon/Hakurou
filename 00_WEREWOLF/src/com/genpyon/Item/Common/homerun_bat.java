package com.genpyon.Item.Common;

import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.AbstractItem;
import com.genpyon.ItemStack.GameItemManager;

public class homerun_bat extends AbstractItem {

	public homerun_bat(Main plugin) {
		super(plugin);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public ItemStack getItemStack() {
		ItemStack is = GameItemManager.HOMERUN_BAT_ITEM();
		return is;
	}

	@Override
	public ItemStack getIcon() {
		ItemStack is = GameItemManager.HOMERUN_BAT_ICON();
		return is;
	}

}
