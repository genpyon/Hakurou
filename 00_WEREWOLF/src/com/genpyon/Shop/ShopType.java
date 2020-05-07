package com.genpyon.Shop;

/**
 * ShopType追加の手順
 * ここに追記してShopManagerにも追加する
 * ItemPackageに新しいパッケージとItemTypeを追加
 * @author gutitubo
 *
 */
public enum ShopType {
	COMMON_SHOP("アイテムショップ"), /* 共通ショップ */
	WEREWOLF_SHOP("人狼ショップ"), /* 人狼ショップ */
	DETECTIVE_SHOP("探偵ショップ"), /* 探偵ショップ */
	;

	private String title;

	private ShopType(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}
}
