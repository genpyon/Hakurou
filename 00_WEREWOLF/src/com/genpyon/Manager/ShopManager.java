package com.genpyon.Manager;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Item.CommonItemType;
import com.genpyon.Item.DetItemType;
import com.genpyon.Item.WolfItemType;
import com.genpyon.Shop.ShopType;

/**
 * プレイヤーがショップを開いたりとかのクラス
 * @author gutitubo
 *
 */
public class ShopManager {

	/** ***********************************
	 *  アイテムの追加手順
	 *  1. Item packageのenumにtypeを追加
	 *  2. 追加したtypeに対応するItemのクラスをAbstractItemを継承して作成
	 *  3. 各TypeのenumのgetItemStackとgetIconを追記
	 *  4. EventListenerに登録
	 * *************************************/

	//TODO インベントリクリックのEqualsを定義

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
		// アイテムのリストを作成
		ArrayList<ItemStack> shopItemList = new ArrayList<>();

		// 共通アイテムをリストに追加
		for (CommonItemType item : CommonItemType.values()) {
			shopItemList.add(item.toItemStack());
		}

		// タイプによってListにアイテムを加える
		if (type == ShopType.WEREWOLF_SHOP) {
			for (WolfItemType item : WolfItemType.values()) {
				shopItemList.add(item.toItemStack());
			}
		} else if (type == ShopType.DETECTIVE_SHOP) {
			for (DetItemType item : DetItemType.values()) {
				shopItemList.add(item.toItemStack());
			}
		}

		//インベントリを作成
		Inventory inv = Bukkit.createInventory(null, getInventorySize(shopItemList.size()), type.getTitle());

		//Listのアイテムをインベントリに追加
		for (ItemStack item : shopItemList) {
			inv.addItem(item);
		}
		return inv;
	}

	/**
	 * インベントリサイズをどうにかするためのメソッド
	 * @param size
	 * @return
	 */
	private static int getInventorySize(int size) {
		int ret = 9;
		if (size > 9) {
			ret = 18;
		}
		if (size > 18) {
			ret = 27;
		}
		if (size > 27) {
			ret = 36;
		}
		if (size > 36) {
			ret = 45;
		}
		if (size > 45) {
			ret = 54;
		}
		return ret;
	}
}
