package com.genpyon.Item;

import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Main;

/**
 * アイテムを作成するときはこのクラスを継承してね '-^b
 * @author gutitubo
 *
 */
public abstract class AbstractItem implements Listener {

	private Main plugin;

	public AbstractItem(Main plugin) {
		init(plugin);
	}

	/**
	 * 最初にこれを呼び出す
	 * @param plugin
	 */
	void init(Main plugin) {
		this.plugin = plugin;
		//this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);
	}

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

	public Main getMain() {
		return this.plugin;
	}

}
