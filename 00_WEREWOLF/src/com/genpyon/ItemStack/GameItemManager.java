package com.genpyon.ItemStack;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import com.genpyon.Library.lib;

import net.md_5.bungee.api.ChatColor;


public class GameItemManager {

	static Random rnd = new Random();
	static String Cost = ChatColor.YELLOW + "Cost : ";

	/**
	 * ■ 初期アイテム ■
	 */

	static public ItemStack WOODEN_SWORD() {
		ItemStack is = lib.createItem(Material.WOOD_SWORD, 1, ChatColor.BOLD + "傷だらけの古びた木の剣", "" ,ChatColor.RESET + "今にも折れそうなほど",ChatColor.RESET + "使い込まれている");
		return is;
	}

	/**
	 * ■ ショップアイテム - コモン  ■
	 */

	static int STONE_SWORD_COST = 200;

	static public ItemStack STONE_SWORD_ITEM() {
		ItemStack is = lib.createItem(Material.STONE_SWORD, 1, ChatColor.BOLD + "傷のついた石の剣", "" ,ChatColor.RESET + "普通の剣。");
		return is;
	}

	static public ItemStack STONE_SWORD_ICON() {
		ItemStack is = lib.createItem(Material.STONE_SWORD, 1, ChatColor.BOLD + "傷のついた石の剣", "" ,ChatColor.RESET + "普通の剣。" , "" , Cost + STONE_SWORD_COST);
		return is;
	}


	/**
	 * ■ ショップアイテム - 人狼向け  ■
	 */




	/**
	 * ■ ショップアイテム - 探偵向け  ■
	 */
	public static String URANAI_INV_NAME = ChatColor.BOLD + "探偵の手帳";
	static int URANAI_BOOK_COST = 200;

	static public ItemStack URANAI_BOOK_ITEM() {
		ItemStack is = lib.createItem(Material.BOOK, 1, ChatColor.BOLD + "探偵の手帳", "" ,ChatColor.RESET + "一定時間のうちに一人だけ", ChatColor.RESET + "役職を知ることができる。");
		return is;
	}

	static public ItemStack URANAI_BOOK_ICON() {
		ItemStack is = lib.createItem(Material.BOOK, 1, ChatColor.BOLD + "探偵の手帳", "" ,ChatColor.RESET + "一定時間のうちに一人だけ", ChatColor.RESET + "役職を知ることができる。" , "" , Cost + + STONE_SWORD_COST);
		return is;
	}
}
