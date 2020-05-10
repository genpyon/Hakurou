package com.genpyon.Item.Detective;

import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.AbstractItem;
import com.genpyon.ItemStack.GameItemManager;

public class Uranai extends AbstractItem {


	public Uranai(Main plugin) {
		super(plugin);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public ItemStack getItemStack() {
		ItemStack is = GameItemManager.URANAI_BOOK_ITEM();
		return is;
	}

	@Override
	public ItemStack getIcon() {
		ItemStack is = GameItemManager.URANAI_BOOK_ICON();
		return is;
	}
}
