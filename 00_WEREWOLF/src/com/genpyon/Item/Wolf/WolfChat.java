package com.genpyon.Item.Wolf;

import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;
import com.genpyon.Item.AbstractItem;
import com.genpyon.ItemStack.GameItemManager;

public class WolfChat extends AbstractItem {


	public WolfChat(Main plugin) {
		super(plugin);
	}

	@Override
	public ItemStack getItemStack() {
		return GameItemManager.WEREWOLF_CHAT_ITEM();
	}

	@Override
	public ItemStack getIcon() {
		return GameItemManager.WEREWOLF_CHAT_ICON();
	}

}
