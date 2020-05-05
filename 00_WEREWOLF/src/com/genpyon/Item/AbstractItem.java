package com.genpyon.Item;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

/**
 * アイテムを作成するときはこのクラスを継承してね '-^b
 * @author gutitubo
 *
 */
public abstract class AbstractItem implements Listener {

	/**
	 * このアイテムの実態を返却するためのクラス
	 * @return
	 */
	public abstract ItemStack getItemStack();

	/**
	 * このアイテムのショップでのアイコンを返却するクラス
	 * @return
	 */
	public abstract ItemStack getIcon();

}
