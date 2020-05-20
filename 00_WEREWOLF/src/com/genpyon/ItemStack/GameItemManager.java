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

	static public ItemStack WEREWOLF_BOOK() {
		ItemStack is = lib.createItem(Material.BOOK, 1, ChatColor.BOLD + "偽物の本", "" ,ChatColor.RESET + "な役に立たない。");
		return is;
	}

	public static final String FOUND_HEADS_INV_NAME = ChatColor.RED + "" + ChatColor.BOLD + "発見済み死体情報";

	static public ItemStack FOUND_HEADS() {
		ItemStack is = lib.createItem(Material.ENCHANTMENT_TABLE, 1, FOUND_HEADS_INV_NAME, "" ,ChatColor.RESET + "発見された死体を確認することが",ChatColor.RESET + "できる。");
		return is;
	}

	public static final String PLAYERS_HEAD_INV_NAME = ChatColor.GOLD + "" + ChatColor.BOLD + "プレイヤーリスト";

	static public ItemStack PLAYERS_HEAD() {
		ItemStack is = lib.createItem(Material.OBSERVER, 1, PLAYERS_HEAD_INV_NAME, "" ,ChatColor.RESET + "プレイヤーの顔、ID、宣言情報を",ChatColor.RESET + "見ることができる。");
		return is;
	}



	static public String CO_INNOCENT_HEAD = ChatColor.GREEN + "村人";
	static public ItemStack CO_INNOCENT() {
		ItemStack is = lib.createItemMeta(Material.WOOL, 1 ,(byte) 5, ChatColor.RESET + "全体に " +  ChatColor.GREEN + "" + ChatColor.BOLD + "村人" + ChatColor.RESET + " を宣言する。");
		return is;
	}

	static public String CO_MAGO_HEAD = ChatColor.GREEN + "孫";
	static public ItemStack CO_MAGO() {
		ItemStack is = lib.createItemMeta(Material.WOOL, 1 ,(byte) 9, ChatColor.RESET + "全体に " +  ChatColor.GREEN + "" + ChatColor.BOLD + "孫" + ChatColor.RESET + " を宣言する。");
		return is;
	}

	static public String CO_TYOUROU_HEAD = ChatColor.DARK_GREEN + "長老";
	static public ItemStack CO_TYOUROU() {
		ItemStack is = lib.createItemMeta(Material.WOOL, 1 ,(byte) 13, ChatColor.RESET + "全体に " +  ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "長老" + ChatColor.RESET + " を宣言する。");
		return is;
	}

	static public String CO_DETECTIVE_HEAD = ChatColor.BLUE + "探偵";
	static public ItemStack CO_DETECTIVE() {
		ItemStack is = lib.createItemMeta(Material.WOOL, 1 ,(byte) 11, ChatColor.RESET + "全体に " +  ChatColor.BLUE + "" + ChatColor.BOLD + "探偵" + ChatColor.RESET + " を宣言する。");
		return is;
	}

	static public String CO_WEREWOLF_HEAD = ChatColor.RED + "人狼";
	static public ItemStack CO_WEREWOLF() {
		ItemStack is = lib.createItemMeta(Material.WOOL, 1 ,(byte) 14, ChatColor.RESET + "全体に " +  ChatColor.RED + "" + ChatColor.BOLD + "人狼" + ChatColor.RESET + " を宣言する。");
		return is;
	}

	static public String CO_HAKUROU_HEAD = ChatColor.DARK_RED + "白狼";
	static public ItemStack CO_HAKUROU() {
		ItemStack is = lib.createItem(Material.NETHER_WART_BLOCK, 1  ,ChatColor.RESET + "全体に " +  ChatColor.DARK_RED + "" + ChatColor.BOLD + "白狼" + ChatColor.RESET + " を宣言する。");
		return is;
	}

	static public String CO_JACKAL_HEAD = ChatColor.AQUA + "妖狐";
	static public ItemStack CO_JACKAL() {
		ItemStack is = lib.createItemMeta(Material.WOOL, 1 ,(byte) 3, ChatColor.RESET + "全体に " +  ChatColor.AQUA + "" + ChatColor.BOLD + "妖狐" + ChatColor.RESET + " を宣言する。");
		return is;
	}
	static public String CO_GRAY_HEAD = ChatColor.GRAY + "グレー";
	static public ItemStack CO_GRAY() {
		ItemStack is = lib.createItemMeta(Material.WOOL, 1 ,(byte) 7, ChatColor.RESET + "全体に " +  ChatColor.GRAY + "" + ChatColor.BOLD + "グレー" + ChatColor.RESET + " を宣言する。");
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
		ItemStack is = lib.createItem(Material.STONE_SWORD, 1, ChatColor.BOLD + "傷のついた石の剣", "" ,
				ChatColor.RESET + "普通の剣。");
		return is;
	}

	static public ItemStack STONE_SWORD_ICON() {
		ItemStack is = lib.createItem(Material.STONE_SWORD, 1, ChatColor.BOLD + "傷のついた石の剣", "" ,
				ChatColor.RESET + "普通の剣。" ,
				"" ,
				Cost + STONE_SWORD_COST);
		return is;
	}


	public static final int TSUYOI_BOW_COST = 200;

	static public ItemStack TSUYOI_BOW_ITEM() {
		ItemStack is = lib.createEnchantItem(Material.BOW, 1, Enchantment.ARROW_DAMAGE, 1, ChatColor.BOLD + "イカれた弓", "" ,
				ChatColor.RESET + "強めの弓。");
		return is;
	}

	static public ItemStack TSUYOI_BOW_ICON() {
		ItemStack is = lib.createEnchantItem(Material.BOW, 1, Enchantment.ARROW_DAMAGE, 1, ChatColor.BOLD + "イカれた弓", "" ,
				ChatColor.RESET + "強めの弓。" ,
				"" ,
				Cost + TSUYOI_BOW_COST);
		return is;
	}



	public static final String HOMERUN_BAT_COST_INV_NAME = ChatColor.BOLD + "ホームランバット";
	public static final int HOMERUN_BAT_COST = 150;

	static public ItemStack HOMERUN_BAT_ITEM() {
		ItemStack is = lib.createItemMetaDulaEnchant(Material.WOOD_SWORD, 1,(byte)1 ,(short)59 ,Enchantment.KNOCKBACK, 10, HOMERUN_BAT_COST_INV_NAME, "" ,
				ChatColor.RESET + "ワンパンふっ飛ばし棒");
		return is;
	}

	static public ItemStack HOMERUN_BAT_ICON() {
		ItemStack is = lib.createItemMetaDulaEnchant(Material.WOOD_SWORD, 1,(byte)1 ,(short)59 ,Enchantment.KNOCKBACK, 10, HOMERUN_BAT_COST_INV_NAME, "" ,
				ChatColor.RESET + "ワンパンふっ飛ばし棒" ,
				"" ,
				Cost + HOMERUN_BAT_COST);
		return is;
	}

	public static final int SPEED_POT_COST = 200;


	/**
	 * ■ ショップアイテム - 人狼向け  ■
	 */

	public static final String TELEPORT_INV_NAME = ChatColor.RED + "てれぽーたー";
	public static final int TELEPORT_COST = 300;

	public static ItemStack TELEPORT_ITEM() {
		return lib.createItem(Material.REDSTONE, 1, TELEPORT_INV_NAME, "",
				ChatColor.RESET + "右クリックで座標を登録する",
				ChatColor.RESET + "座標登録後殴ると相手をテレポート",
				ChatColor.RESET + "座標登録後右クリックで自分をテレポート");
	}

	public static ItemStack TELEPORT_ICON() {
		return lib.createItem(Material.REDSTONE, 1, TELEPORT_INV_NAME, "",
				ChatColor.RESET + "右クリックで座標を登録する",
				ChatColor.RESET + "座標登録後殴ると相手をテレポート",
				ChatColor.RESET + "座標登録後右クリックで自分をテレポート",
				"" ,
				Cost + + TELEPORT_COST);
	}


	public static final String WEREWOLF_CHAT_INV_NAME = ChatColor.RED + "狼の遠吠え";
	public static final int WEREWOLF_CHAT_COST = 100;

	public static ItemStack WEREWOLF_CHAT_ITEM() {
		return lib.createItem(Material.BONE, 1, WEREWOLF_CHAT_INV_NAME, "",
				ChatColor.RESET + "こいつを手に持った状態で",
				ChatColor.RESET + "チャットをすると狼向けにチャットを",
				ChatColor.RESET + "送信することができる");
	}

	public static ItemStack WEREWOLF_CHAT_ICON() {
		return lib.createItem(Material.BONE, 1, WEREWOLF_CHAT_INV_NAME, "",
				ChatColor.RESET + "こいつを手に持った状態で",
				ChatColor.RESET + "チャットをすると狼向けにチャットを",
				ChatColor.RESET + "送信することができる",
				"" , Cost + + WEREWOLF_CHAT_COST);
	}

	public static final String BLINK_INV_NAME = ChatColor.BOLD + "ブリンク";
	public static final int BLINK_COST = 150;

	public static ItemStack BLINK_ITEM() {
		return lib.createItem(Material.BLAZE_POWDER, 1, BLINK_INV_NAME, "",
				ChatColor.RESET + "オフハンドにこのアイテムをもつ",
				ChatColor.RESET + "Shiftを押してブリンクチャージ",
				ChatColor.RESET + "Shiftを離してその方向にブリンクする");
	}

	public static ItemStack BLINK_ICON() {
		return lib.createItem(Material.BLAZE_POWDER, 1, BLINK_INV_NAME, "",
				ChatColor.RESET + "オフハンドにこのアイテムをもつ",
				ChatColor.RESET + "Shiftを押してブリンクチャージ",
				ChatColor.RESET + "Shiftを離してその方向にブリンクする",
				"" , Cost + + BLINK_COST);
	}

	/**
	 * ■ ショップアイテム - 探偵向け  ■
	 */

	public static String URANAI_INV_NAME = ChatColor.BOLD + "探偵の手帳";
	public static final int URANAI_BOOK_COST = 200;

	static public ItemStack URANAI_BOOK_ITEM() {
		ItemStack is = lib.createItem(Material.BOOK, 1, ChatColor.BOLD + "探偵の手帳", "" ,
				ChatColor.RESET + "一定時間のうちに一人だけ",
				ChatColor.RESET + "役職を知ることができる。");
		return is;
	}

	static public ItemStack URANAI_BOOK_ICON() {
		ItemStack is = lib.createItem(Material.BOOK, 1, ChatColor.BOLD + "探偵の手帳", "" ,
				ChatColor.RESET + "一定時間のうちに一人だけ",
				ChatColor.RESET + "役職を知ることができる。" , "" ,
				Cost + + STONE_SWORD_COST);
		return is;
	}

	public static String SENRIGAN_INV_NAME = ChatColor.BOLD + "千里眼";
	public static final int SENRIGAN_COST = 200;

	public static ItemStack SENRIGAN_ITEM() {
		return lib.createItem(Material.EYE_OF_ENDER, 1, TELEPORT_INV_NAME, "",
				ChatColor.RESET + "右クリックで座標を登録する",
				ChatColor.RESET + "座標登録後殴ると相手をテレポート",
				ChatColor.RESET + "座標登録後右クリックで自分をテレポート");
	}

	public static ItemStack SENRIGAN_ICON() {
		return lib.createItem(Material.DIAMOND, 1, TELEPORT_INV_NAME, "",
				ChatColor.RESET + "右クリックで座標を登録する",
				ChatColor.RESET + "座標登録後殴ると相手をテレポート",
				ChatColor.RESET + "座標登録後右クリックで自分をテレポート",
				"" , Cost + + TELEPORT_COST);
	}
}
