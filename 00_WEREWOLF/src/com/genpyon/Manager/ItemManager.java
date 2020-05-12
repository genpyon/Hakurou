package com.genpyon.Manager;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Item関連の処理をかんたんにアレするためのマネイジェア
 * @author gutitubo
 *
 */
public class ItemManager {

	/**
	 * 指定のアイテムをハンドに持っているかの判定
	 */
	public static boolean hasMainHand(Player p, ItemStack item) {
		boolean hasMainHand = false;
		ItemStack hand = p.getInventory().getItemInMainHand();
		hasMainHand = isSameItem(item, hand);
		return hasMainHand;
	}

	/**
	 * 指定のアイテムをおっはんどに持っているか判定
	 */
	public static boolean hasOffHand(Player p, ItemStack item) {
		boolean hasOffHand = false;
		ItemStack off = p.getInventory().getItemInOffHand();
		hasOffHand = isSameItem(item, off);
		return hasOffHand;
	}

	/**
	 * ２つのアイテムが同じものかをゆるめに判定する
	 * 基本的に名前だけで決めつけていこうな。
	 * @return
	 */
	public static boolean isSameItem (ItemStack item, ItemStack comp) {
		boolean bool = false;
		if (comp != null) {
			if (item.getItemMeta() == null && comp.getItemMeta() == null) {
				if (item.getType() == comp.getType()) bool = true;
			} else {
				if (comp.getItemMeta() == null) {
					bool = false;
				} else {
					if (item.getItemMeta().getDisplayName() != null) {
						if (comp.getItemMeta().getDisplayName() != null) {
							if (item.getItemMeta().getDisplayName().equals(comp.getItemMeta().getDisplayName())) {
								bool = true;
							}
						} else {
							bool = false;
						}
					}
				}
			}
		}
		return bool;
	}
}
