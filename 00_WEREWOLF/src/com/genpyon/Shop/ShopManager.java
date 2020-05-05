package com.genpyon.Shop;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * プレイヤーがショップを開いたりとかのクラス
 * @author gutitubo
 *
 */
public class ShopManager {
	/**
	 * プレイヤーにショップを開かせるためのめそっど
	 * @param p 対象のプレイヤー
	 * @param type Shopのタイプ
	 */
	public static void openShop(Player p, ShopType type) {
		p.openInventory(getInventory(type));
	}

	/**
	 * 各タイプのインベントリを取得するためのメソッド
	 * @param type Shopのタイプ
	 * @return ショップのインベントリ
	 */
	private static Inventory getInventory(ShopType type) {
		Inventory inv = Bukkit.createInventory(null, 9, type.getTitle());
		// TODO アイテム一覧を取得してforでセットする
		return inv;
	}
}
