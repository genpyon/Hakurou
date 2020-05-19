package com.genpyon.Event;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.genpyon.Main;
import com.genpyon.Item.CommonItemType;
import com.genpyon.Item.DetItemType;
import com.genpyon.Item.WolfItemType;
import com.genpyon.Item.Wolf.Blink;
import com.genpyon.Library.lib;
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
	public void PlayerHeadPickup3tumade(EntityPickupItemEvent b) {
		Entity ent = b.getEntity();
		if(ent instanceof Player) {
			Player p = (Player) ent;
			Inventory inv = p.getInventory();
			if(inv.contains(Material.SKULL_ITEM)) {
				//Bukkit.broadcastMessage("1こもってるよ！");
				b.setCancelled(true);
				return;
			}

		}
	}


	@EventHandler
	public void playerHeadChange (PlayerInteractEvent b){
		Player p = b.getPlayer();

		Action act = b.getAction();

		if(act == Action.LEFT_CLICK_AIR || act == Action.RIGHT_CLICK_AIR || act == Action.LEFT_CLICK_BLOCK || act == Action.RIGHT_CLICK_BLOCK){


			if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType().equals(Material.DOUBLE_PLANT)){
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

					if(plugin.GameStatus == 3) {

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
								plugin.GameTime = plugin.GameTime+20;
							}

							if(Main.ROLE.get(name).equalsIgnoreCase("WEREWOLF")) {
								Bukkit.broadcastMessage(Main.system + ChatColor.RED + "人狼陣営の死体が発見され、制限時間が" + 10 + "秒短縮されました。");
								plugin.GameTime = plugin.GameTime-10;
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


	@EventHandler
	public void yagakieru(ProjectileHitEvent b){
		Projectile projectile = (Projectile) b.getEntity();
		Location hitted = projectile.getLocation();
		hitted.getWorld().playSound(hitted, Sound.ENTITY_ARROW_HIT, 0.5F, 1F);
		projectile.remove();
	}

	public boolean containsCaseInsensitive(String s, List<String> l){
		for (String string : l){
			if (string.equalsIgnoreCase(s)){
				return true;
			}
		}
		return false;
	}





	@EventHandler
	public void foodLevelChange(FoodLevelChangeEvent b){
		b.setCancelled(true);
	}



	@EventHandler
	public void onDamage(EntityDamageEvent b){
		if(b.getEntity() instanceof Player){
			if (b.getCause() == DamageCause.FALL){

				if(plugin.GameStatus != 3){
					b.setCancelled(true);
					return;
				}

				Location loc = b.getEntity().getLocation();
				loc.setY(loc.getY() -2F);

				Block bl = loc.getWorld().getBlockAt(loc);
				if (bl !=null){
					if(bl.getType() == Material.SIGN_POST){
						Sign sign2 = (Sign) bl.getState();
						if(sign2 == null){
							return;
						} else {
							if(sign2.getLine(0).equalsIgnoreCase("jump")){
								b.setCancelled(true);
							}
						}
					}
				}
			}
		}
	}

	@EventHandler
	public void onSignCreate(SignChangeEvent e) {

		String line1 = e.getLine(0);
		//String line2 = e.getLine(1);
		if(e.getPlayer().isOp()){

			if (line1.equals("gent")) {
				e.setLine(0, ChatColor.RED + "[GenT]");
			}
		}
	}

	@EventHandler
	public void Teleport(PlayerMoveEvent b){
		Player p = b.getPlayer();
		Location loc = p.getLocation();
		//Location bed = p.getLocation();

		loc.setY(loc.getY() -0.5F);
		//Material blockOn = loc.getWorld().getBlockAt(loc).getType();


		if(p !=null){
			Location sign = p.getLocation();
			sign.setY(sign.getY() -1.5F);
			Block bl = sign.getWorld().getBlockAt(sign);
			if(bl.getType() == Material.SIGN_POST){


				if (bl !=null){
					Sign sign2 = (Sign) bl.getState();
					if(sign2 == null){
						return;
					} else {


						String line4 = sign2.getLine(0);
						if (line4.contains(ChatColor.RED + "[GenT]")) {
							double line1 = Double.parseDouble(sign2.getLine(1).split(" ")[0]);
							double line2 = Double.parseDouble(sign2.getLine(2).split(" ")[0]);
							double line3 = Double.parseDouble(sign2.getLine(3).split(" ")[0]);
							Location world = p.getPlayer().getLocation();
							World world2 = world.getWorld();

							Location l = new Location(world2, line1 + 0.5D, line2 + 0.5D, line3 + 0.5D);
							l.setPitch(p.getLocation().getPitch());
							l.setYaw(p.getLocation().getYaw());

							p.teleport(l);
							p.setBedSpawnLocation(l,true);
							lib.SoundPlayer(p, Sound.ENTITY_ENDERMEN_TELEPORT, 0.2F);
						}
					}
				}
			}

		}
	}



	/**
	 * 看板が下にあるブロック上でスニークしたときのイベントを定義
	 * @param e event
	 */
	@EventHandler
	public void whenSneakingOnBlock(PlayerToggleSneakEvent e) {
		// 1. プレイヤーの1.5マス下の座標を取得
		Player p = e.getPlayer();
		// Bukkit.broadcastMessage("1");
		Location loc = p.getLocation();
		loc.setY(loc.getY() - 1.5F);

		// 2. 手順1の座標のブロックをnull check
		Block b = loc.getWorld().getBlockAt(loc);
		// Bukkit.broadcastMessage("2");
		if (b == null) return;

		// 3. ブロックが看板だった場合 状態を取得して処理
		if (b.getType().equals(Material.SIGN_POST)) {
			//Bukkit.broadcastMessage("3");
			Sign sign =(Sign) b.getState();
			if (sign == null) return;
			giveVelocityFromSign(p, sign);
		}
	}

	/**
	 * 看板から値を取得して速度としてプレイヤーに与える
	 * @param p
	 * @param sign
	 */
	private static void giveVelocityFromSign(Player p, Sign sign) {
		if (sign.getLine(0).equalsIgnoreCase("jump")) {
			// Bukkit.broadcastMessage("4");
			String strValue = sign.getLine(1);
			if (strValue != null) {
				strValue.trim();
				// Bukkit.broadcastMessage("5");
				try {
					// Bukkit.broadcastMessage("6");
					Double value = Double.parseDouble(strValue);
					Vector vel = new Vector(0, value, 0);
					p.setVelocity(vel);
				} catch (NumberFormatException e) {
					Bukkit.getLogger().warning("[LMS] 看板やばいよException");
				}
			}
		}
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

	/**
	 * Shopをクリックした際のイベントを定義する
	 * @param e
	 */
	@EventHandler
	public void whenShopClicked(InventoryClickEvent e) {
		// インベントリを取得してタイトルがあるかどうかを確認する
		try {
			Inventory inv = e.getInventory();
			String invTitle = inv.getTitle();
			if (invTitle == null) return;

			// ShopInventoryのTitleにあってるかどうか
			boolean isShop = false;
			for (ShopType type :ShopType.values()) {
				if (type.getTitle().equals(invTitle)) isShop = true;
			}
			if (!isShop) return;
			e.setCancelled(true);

			// クリックしたプレイヤーとアイテムを取得
			Player p = null;
			ItemStack item = e.getCurrentItem();
			if (e.getWhoClicked() instanceof Player)
				p = (Player) e.getWhoClicked();
			if (p == null || item == null) return;

			// 各ショップタイプの購入処理
			// FIXME: ちょっと原始的でムカつくので開いてるときに方法考える
			for (CommonItemType type : CommonItemType.values()) {
				if (type.toItemIcon().equals(item)) {
					ShopManager.purchaseItem(p, type.toItemStack(), type.getCost());
					return;
				}
			}
			for (DetItemType type : DetItemType.values()) {
				if (type.toItemIcon().equals(item)) {
					ShopManager.purchaseItem(p, type.toItemStack(), type.getCost());
					return;
				}
			}
			for (WolfItemType type : WolfItemType.values()) {
				if (type.toItemIcon().equals(item)) {
					ShopManager.purchaseItem(p, type.toItemStack(), type.getCost());
					return;
				}
			}
		} catch (Exception exception) {

		}
	}

}
