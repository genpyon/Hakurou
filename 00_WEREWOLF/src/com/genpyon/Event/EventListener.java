package com.genpyon.Event;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.util.Vector;

import com.genpyon.Main;



public class EventListener implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public EventListener(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

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
	public void PlayerJoinEvent(PlayerJoinEvent b){
		Player p = b.getPlayer();

		if(!p.getGameMode().equals(GameMode.CREATIVE)){
			plugin.pm.DefaultStuff(p);
		}

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



}
