package com.genpyon.Item.Wolf;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.genpyon.Main;
import com.genpyon.Item.AbstractItem;

public class Teleport extends AbstractItem {

	public Teleport(Main plugin) {
		super(plugin);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	@Override
	public ItemStack getItemStack() {
		// TODO 自動生成されたメソッド・スタブ
		ItemStack item = new ItemStack(Material.DIAMOND);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName("Teleporter");
		item.setItemMeta(itemMeta);
		return item;
	}

	@Override
	public ItemStack getIcon() {
		// TODO 自動生成されたメソッド・スタブ
		ItemStack item = new ItemStack(Material.DIAMOND);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName("Teleporterですよ");
		item.setItemMeta(itemMeta);
		return item;
	}

}
