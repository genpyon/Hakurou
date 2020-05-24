package com.genpyon.Item.Wolf;

import java.util.HashMap;
import java.util.HashSet;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.genpyon.Main;
import com.genpyon.Item.AbstractItem;
import com.genpyon.ItemStack.GameItemManager;

public class Blink extends AbstractItem {

	private static HashMap<Player, Integer> blinkPower = new HashMap<>();
	private static HashSet<Player> onCharge = new HashSet<>();

	public Blink(Main plugin) {
		super(plugin);
	}

	@Override
	public ItemStack getItemStack() {
		return GameItemManager.BLINK_ITEM();
	}

	@Override
	public ItemStack getIcon() {
		return GameItemManager.BLINK_ICON();
	}

	/**
	 * ToggleSneakEventで呼ぶ
	 * @param p
	 */
	public static void useBlink(Player p) {
		Integer power = blinkPower.get(p);
		if (power == null) {
			startBlink(p);
			return;
		} else {
			releaseBlink(p);
			return;
		}
	}

	public static void startBlink(Player p) {
		Integer power = blinkPower.get(p);
		if (power == null) {
			power = Integer.valueOf(0);
			blinkPower.put(p, 0);
			onCharge.add(p);
			p.playSound(p.getLocation(), Sound.ENTITY_GHAST_WARN, 0.5F, 1F);
		}
	}

	/**
	 * スケジューラで回す
	 */
	public static void chargeBlink() {
		if (onCharge.size() == 0) return;
		onCharge.forEach(p -> {
			blinkPower.put(p, blinkPower.get(p) + 1);
		});
	}

	public static void releaseBlink(Player p) {
		Integer power = blinkPower.get(p);
		if (power == null) {
			power = Integer.valueOf(0);
			blinkPower.put(p, 0);
		}
		p.getWorld().playSound(p.getLocation(), Sound.ENTITY_GHAST_SHOOT, 1.3F, 0.5F);

		Location loc = p.getLocation().clone();
		loc.add(0, p.getEyeHeight(), 0);
		if (power > 20) power = 20;

		Vector vec = loc.getDirection();
		Location to = p.getLocation().clone();
		for (int i = 0; i<power; i++) {
			loc.add(vec);
			if (loc.getBlock().getType() != Material.AIR) break;
			to = loc;
		}
		p.setFallDistance(0);
		p.teleport(to);
		blinkPower.remove(p);
		onCharge.remove(p);
	}

}
