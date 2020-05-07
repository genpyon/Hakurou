package com.genpyon.ItemStack;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Library.lib;

import net.md_5.bungee.api.ChatColor;




public class GameItemManager {

	static Random rnd = new Random();

	/**
	 * ■ 初期アイテム ■
	 */

	static public ItemStack WOODEN_SWORD() {
		ItemStack is = lib.createItem(Material.WOOD_SWORD, 1, ChatColor.BOLD + "傷だらけの古びた木の剣", "" ,ChatColor.RESET + "今にも折れそうなほど",ChatColor.RESET + "使い込まれている");
		return is;
	}

	/**
	 * ■ コモンアイテム ■
	 */

	static int STONE_SWORD_ITEM_COST = 200;

	static public ItemStack STONE_SWORD_ITEM() {
		ItemStack is = lib.createItem(Material.STONE_SWORD, 1, ChatColor.BOLD + "傷のついた石の剣", "" ,ChatColor.RESET + "ごく普通だ。");
		return is;
	}

	static public ItemStack STONE_SWORD_ICON() {
		ItemStack is = lib.createItem(Material.STONE_SWORD, 1, ChatColor.BOLD + "傷のついた石の剣", "" ,ChatColor.RESET + "ごく普通だ。" + "" + ChatColor.YELLOW + " COST : " + STONE_SWORD_ITEM_COST);
		return is;
	}



	/**
	 * ■ 特殊アイテム ■
	 */

}
