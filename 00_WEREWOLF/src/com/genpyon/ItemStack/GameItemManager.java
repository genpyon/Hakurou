package com.genpyon.ItemStack;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
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

	static public ItemStack WOODEN_BOW() {
		ItemStack is = lib.createItem(Material.BOW, 1, ChatColor.BOLD + "傷だらけの古びた弓", "" ,ChatColor.RESET + "今にも折れそうなほど",ChatColor.RESET + "使い込まれている");
		return is;
	}

	static public ItemStack SHOP_FLOWER() {
		ItemStack is = lib.createItem(Material.DOUBLE_PLANT, 1, ChatColor.BOLD + "ショップ", "" ,ChatColor.RESET + "ショップを開くためのやつ");
		return is;
	}

	/**
	 * ■ ショップアイテム - コモン  ■
	 *
	 */

	public static final int ARROW_COST = 50;

	static public ItemStack ARROW_ITEM() {
		ItemStack is = lib.createItem(Material.ARROW, 16, "矢", "", "普通の矢");
		return is;
	}

	static public ItemStack ARROW_ICON() {
		ItemStack is = lib.createItem(Material.ARROW, 16, "矢", "", "普通の矢" , "" , Cost + ARROW_COST);
		return is;
	}




	public static final int STONE_SWORD_COST = 200;

	static public ItemStack STONE_SWORD_ITEM() {
		ItemStack is = lib.createItem(Material.STONE_SWORD, 1, ChatColor.BOLD + "傷のついた石の剣", "" ,ChatColor.RESET + "普通の剣。");
		return is;
	}

	static public ItemStack STONE_SWORD_ICON() {
		ItemStack is = lib.createItem(Material.STONE_SWORD, 1, ChatColor.BOLD + "傷のついた石の剣", "" ,ChatColor.RESET + "普通の剣。" , "" , Cost + STONE_SWORD_COST);
		return is;
	}


	public static final int TSUYOI_BOW_COST = 200;

	static public ItemStack TSUYOI_BOW_ITEM() {
		ItemStack is = lib.createEnchantItem(Material.BOW, 1, Enchantment.ARROW_DAMAGE, 1, ChatColor.BOLD + "イカれた弓", "" ,ChatColor.RESET + "強めの弓。");
		return is;
	}

	static public ItemStack TSUYOI_BOW_ICON() {
		ItemStack is = lib.createEnchantItem(Material.BOW, 1, Enchantment.ARROW_DAMAGE, 1, ChatColor.BOLD + "イカれた弓", "" ,ChatColor.RESET + "強めの弓。" , "" , Cost + TSUYOI_BOW_COST);
		return is;
	}

	public static final int SPEED_POT_COST = 200;


	/**
	 * ■ ショップアイテム - 人狼向け  ■
	 */

	public static final String TELEPORT_INV_NAME = ChatColor.BOLD + "てれぽーたー";
	public static final int TELEPORT_COST = 300;

	public static ItemStack TELEPORT_ITEM() {
		return lib.createItem(Material.DIAMOND, 1, TELEPORT_INV_NAME, "",
				ChatColor.RESET + "右クリックで座標を登録する",
				ChatColor.RESET + "座標登録後殴ると相手をテレポート",
				ChatColor.RESET + "座標登録後右クリックで自分をテレポート");
	}

	public static ItemStack TELEPORT_ICON() {
		return lib.createItem(Material.DIAMOND, 1, TELEPORT_INV_NAME, "",
				ChatColor.RESET + "右クリックで座標を登録する",
				ChatColor.RESET + "座標登録後殴ると相手をテレポート",
				ChatColor.RESET + "座標登録後右クリックで自分をテレポート",
				"" , Cost + + TELEPORT_COST);
	}


	/**
	 * ■ ショップアイテム - 探偵向け  ■
	 */

	public static String URANAI_INV_NAME = ChatColor.BOLD + "探偵の手帳";
	public static final int URANAI_BOOK_COST = 200;

	static public ItemStack URANAI_BOOK_ITEM() {
		ItemStack is = lib.createItem(Material.BOOK, 1, ChatColor.BOLD + "探偵の手帳", "" ,ChatColor.RESET + "一定時間のうちに一人だけ", ChatColor.RESET + "役職を知ることができる。");
		return is;
	}

	static public ItemStack URANAI_BOOK_ICON() {
		ItemStack is = lib.createItem(Material.BOOK, 1, ChatColor.BOLD + "探偵の手帳", "" ,ChatColor.RESET + "一定時間のうちに一人だけ", ChatColor.RESET + "役職を知ることができる。" , "" , Cost + + STONE_SWORD_COST);
		return is;
	}

	public static String SENRIGAN_INV_NAME = ChatColor.BOLD + "千里眼";
	public static final int SENRIGAN_COST = 200;



	/**
	 * ■ ショップアイテム - 探偵向け  ■
	 */
}
