package com.genpyon.Item.Common;

import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.AbstractItem;
import com.genpyon.ItemStack.GameItemManager;

public class TsuyoiBow extends AbstractItem {

	public TsuyoiBow(Main plugin) {
		super(plugin);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public ItemStack getItemStack() {
		return GameItemManager.TSUYOI_BOW_ITEM();
	}

	@Override
	public ItemStack getIcon() {
		return GameItemManager.TSUYOI_BOW_ICON();
	}
}
