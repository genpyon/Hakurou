package com.genpyon.Event;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import com.genpyon.Main;
import com.genpyon.Item.Wolf.Blink;
import com.genpyon.ItemStack.GameItemManager;
import com.genpyon.Library.lib;
import com.genpyon.Manager.DetectiveBookManager;
import com.genpyon.Manager.ItemManager;
import com.genpyon.Manager.ShopManager;
import com.genpyon.Manager.SkullManager;
import com.genpyon.Role.RoleManager;
import com.genpyon.Shop.ShopType;



public class EventListener implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public EventListener(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}

	@EventHandler
	public void playerHeadChange (PlayerInteractEvent b){
		Player p = b.getPlayer();

		Action act = b.getAction();

		if(act == Action.LEFT_CLICK_AIR || act == Action.RIGHT_CLICK_AIR || act == Action.LEFT_CLICK_BLOCK || act == Action.RIGHT_CLICK_BLOCK){


			if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().equals(GameItemManager.FOUND_HEADS())){
				if(Main.FOUND.size() != 0) {
					p.openInventory(DetectiveBookManager.foundGetHeads());
				} else {
					lib.sendPlayer(p, Main.system + "現在、死体発見情報がありません。");
				}
				return;
			}

			if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().equals(GameItemManager.PLAYERS_HEAD())){
				if(Main.PLAYER.size() != 0) {
					if(Main.CO.containsKey(p.getName()) && Main.CO.get(p.getName()).equalsIgnoreCase("DETECTIVE")) {
						p.openInventory(DetectiveBookManager.detectivePlayerGetHeads(p));
					} else {
						p.openInventory(DetectiveBookManager.playerGetHeads(p));
					}
				} else {
					lib.sendPlayer(p, Main.system + "参加プレイヤーが存在しません。");
				}
				return;
			}

			if(p.getInventory().getItemInMainHand() != null  && ItemManager.hasMainHand(p, GameItemManager.URANAI_BOOK_ITEM())) {
				if(p.hasCooldown(Material.BOOK)) {
					lib.sendPlayer(p, Main.system +"まだ使うトキではない。");
					return;
				}
				p.openInventory(DetectiveBookManager.uranaiGetHeads());
			}


			if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().equals(GameItemManager.SHOP_FLOWER())){
				if(Main.ROLE.containsKey(p.getName())) {

					if(Main.ROLE.get(p.getName()).equalsIgnoreCase("INNOCENT")) {
						ShopManager.openShop(p, ShopType.COMMON_SHOP);
						return;
					}
					if(Main.ROLE.get(p.getName()).equalsIgnoreCase("MAGO")) {
						ShopManager.openShop(p, ShopType.COMMON_SHOP);
						return;
					}
					if(Main.ROLE.get(p.getName()).equalsIgnoreCase("TYOUROU")) {
						ShopManager.openShop(p, ShopType.COMMON_SHOP);
						return;
					}
					if(Main.ROLE.get(p.getName()).equalsIgnoreCase("DETECTIVE")) {
						ShopManager.openShop(p, ShopType.DETECTIVE_SHOP);
						return;
					}
					if(Main.ROLE.get(p.getName()).equalsIgnoreCase("WEREWOLF")) {
						ShopManager.openShop(p, ShopType.WEREWOLF_SHOP);
						return;
					}
					if(Main.ROLE.get(p.getName()).equalsIgnoreCase("HAKUROU")) {
						ShopManager.openShop(p, ShopType.WEREWOLF_SHOP);
						return;
					}
					if(Main.ROLE.get(p.getName()).equalsIgnoreCase("JACKAL")) {
						ShopManager.openShop(p, ShopType.COMMON_SHOP);
						return;
					}
				}
			}

			if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType().equals(Material.SKULL_ITEM)){
				String name = null;
				String hakken = null;


				try {
					name = p.getInventory().getItemInMainHand().getItemMeta().getLore().get(2);
					hakken = p.getInventory().getItemInMainHand().getItemMeta().getLore().get(1);

				} catch (Exception e) {
					return;
				}

				//Bukkit.broadcastMessage(hakken);


				if(Main.ROLE.containsKey(name) && Main.PLAYER.contains(p.getName())){

					if(Main.GameStatus == 3) {

						if(Main.FOUND.containsKey(name)) {

						} else {
							Main.FOUND.put(name, Main.ROLE.get(name));
						}

						if(Main.ROLE.get(p.getName()).equalsIgnoreCase("DETECTIVE")){

							if(Main.DetectiveMode == true) {
								//頭から役職をわかるようにする。
								p.getInventory().remove(p.getInventory().getItemInMainHand());
								p.getInventory().addItem(SkullManager.roleHeadChangeDetective(name));

							} else {
								//頭から役職をわかるようにしない。
								p.getInventory().remove(p.getInventory().getItemInMainHand());
								p.getInventory().addItem(SkullManager.roleHeadChange(name));
							}

						} else if(hakken.equalsIgnoreCase(ChatColor.YELLOW + "未発見")){

							if(Main.TTTMode == true) {

								p.getInventory().remove(p.getInventory().getItemInMainHand());
								p.getInventory().addItem(SkullManager.roleHeadChangeDetective(name));

							} else {
								p.getInventory().remove(p.getInventory().getItemInMainHand());
								p.getInventory().addItem(SkullManager.roleHeadChange(name));
							}
						}


						if(hakken.equalsIgnoreCase(ChatColor.YELLOW + "未発見")){
							if(Main.TTTMode == true) {
								//Bukkit.broadcastMessage("");
								Bukkit.broadcastMessage(Main.system + ChatColor.RESET + p.getName() + " が " + ChatColor.RED + name + ChatColor.WHITE + "の生首を発見した。" + RoleManager.bookRoleNameChanger(Main.ROLE.get(name)) + "だった。");
							} else {
								//Bukkit.broadcastMessage("");
								Bukkit.broadcastMessage(Main.system + ChatColor.RESET + p.getName() + " が " + ChatColor.RED + name + ChatColor.WHITE + "の生首を発見した。");
							}

							if(Main.ROLE.get(name).equalsIgnoreCase("INNOCENT") || Main.ROLE.get(name).equalsIgnoreCase("DETECTIVE")) {
								Bukkit.broadcastMessage(Main.system + ChatColor.GRAY + "村人陣営の死体が発見され、制限時間が" + 20 + "秒延長されました。");

								for(Player a : Bukkit.getOnlinePlayers()) {
									if(Main.ROLE.containsKey(a.getName())){
										if(Main.ROLE.get(a.getName()).equalsIgnoreCase("WEREWOLF")) {

											Main.COIN.put(a.getName(), Main.COIN.get(a.getName())+Main.caWEREWOLF);
											lib.sendPlayer(a, Main.system + ChatColor.GOLD + Main.caWEREWOLF +"COIN が追加されました");

										}

										if(Main.ROLE.get(a.getName()).equalsIgnoreCase("JACKAL")) {
											Main.COIN.put(a.getName(), Main.COIN.get(a.getName())+Main.caJACKAL);
											lib.sendPlayer(a, Main.system + ChatColor.GOLD + Main.caJACKAL +"COIN が追加されました");
										}
									}
								}
								Main.GameTime = Main.GameTime+20;
								Bukkit.broadcastMessage(Main.system + ChatColor.GREEN + "村人の残り人数は、" + ChatColor.YELLOW + RoleManager.nokoriInteger("INNOCENT") + ChatColor.GREEN + " 人です。");

							}

							if(Main.ROLE.get(name).equalsIgnoreCase("WEREWOLF")) {
								Bukkit.broadcastMessage(Main.system + ChatColor.RED + "人狼陣営の死体が発見され、制限時間が" + 10 + "秒短縮されました。");

								for(Player a : Bukkit.getOnlinePlayers()) {
									if(Main.ROLE.containsKey(a.getName())){
										if(Main.ROLE.get(a.getName()).equalsIgnoreCase("INNOCENT")) {
											Main.COIN.put(a.getName(), Main.COIN.get(a.getName())+Main.caINNOCENT);
											lib.sendPlayer(a, Main.system + ChatColor.GOLD + Main.caINNOCENT +"COIN が追加されました");
										}

										if(Main.ROLE.get(a.getName()).equalsIgnoreCase("JACKAL")) {
											Main.COIN.put(a.getName(), Main.COIN.get(a.getName())+Main.caJACKAL);
											lib.sendPlayer(a, Main.system + ChatColor.GOLD + Main.caJACKAL +"COIN が追加されました");
										}
									}
								}

								Main.GameTime = Main.GameTime-10;
								Bukkit.broadcastMessage(Main.system + ChatColor.RED + "人狼の残り人数は、" + ChatColor.YELLOW + RoleManager.nokoriInteger("WEREWOLF") + ChatColor.RED + " 人です。");

							}

							//Bukkit.broadcastMessage("");
							lib.SoundAllPlayer(Sound.ENTITY_PLAYER_LEVELUP, 1.4F);
						}
					}


					return;
				} else {
					Bukkit.broadcastMessage(Main.system + "それですけど、それじゃないです。");
					Bukkit.broadcastMessage(Main.ROLE.toString());
				}

			} else {
				//Bukkit.broadcastMessage("それじゃありません!!");
			}

		} else {

		}
	}



	public boolean containsCaseInsensitive(String s, List<String> l){
		for (String string : l){
			if (string.equalsIgnoreCase(s)){
				return true;
			}
		}
		return false;
	}
	/**
	 * Blinkのやつ
	 */
	@EventHandler
	public void whenUseBlink(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		if (ItemManager.hasOffHand(p, new Blink(plugin).getItemStack())) {
			Blink.useBlink(p);
		}
	}

}
