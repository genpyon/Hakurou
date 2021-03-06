package com.genpyon.Event;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
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
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
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
import com.genpyon.Library.lib;
import com.genpyon.Manager.ShopManager;
import com.genpyon.Shop.ShopType;



public class SystemListener implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public SystemListener(Main plugin) {
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
	public void yumiyaKankei(ProjectileHitEvent b){
		Projectile projectile = (Projectile) b.getEntity();
		Location hitted = projectile.getLocation();
		hitted.getWorld().playSound(hitted, Sound.ENTITY_ARROW_HIT, 0.5F, 1F);
		projectile.remove();
	}






	@EventHandler
	public void foodLevelChange(FoodLevelChangeEvent b){
		b.setCancelled(true);
	}


	@EventHandler
	public void HangingBreakByEntityEvent(HangingBreakByEntityEvent b){
		if(b.getRemover() instanceof Player) {
			Player p = (Player) b.getRemover();
			if(p.getGameMode().equals(GameMode.ADVENTURE)) {
				b.setCancelled(true);
			}
		}
	}



	@EventHandler
	public void onDamage(EntityDamageEvent b){
		if(b.getEntity() instanceof Player){
			if (b.getCause() == DamageCause.FALL){

				if(Main.GameStatus != 3){
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

			if (line1.equals("map")) {
				e.setLine(0, ChatColor.RED + "[HAKUROU MAP]");

				e.setLine(3, "=============");
			}
		}
	}

	@EventHandler
	public void onClick(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Action act = e.getAction();
		Block clickedBlock = e.getClickedBlock();

		if(!p.isOp()) {
			return;
		}


		if (act == Action.RIGHT_CLICK_BLOCK) {

			if(!(clickedBlock.getType()==Material.SIGN || clickedBlock.getType()==Material.SIGN_POST || clickedBlock.getType()==Material.WALL_SIGN))
				return;
			Sign sign = (Sign) clickedBlock.getState();
			String lineer = sign.getLine(0);

			if (lineer.contains(ChatColor.RED + "[HAKUROU MAP]")) {
				String lineer2 = sign.getLine(1);

				try {
					if(plugin.configLocation(lineer2) != null) {
						if(p.isOp()) {
							plugin.gm.start(p, plugin.configLocation(lineer2));
						}

					}
				} catch (Exception eb) {
					lib.sendPlayer(p, Main.system + "対象のMAPが設定されていません。");
				}
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
				if (type == null) continue;
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
