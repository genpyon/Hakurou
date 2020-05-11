package com.genpyon.Item.Wolf;

import java.util.HashMap;

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

	public static HashMap<Player, Integer> blinkPower = new HashMap<>();

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

	public static void chargeBlink(Player p) {
		Integer power = blinkPower.get(p);
		if (power == null) {
			power = Integer.valueOf(0);
			blinkPower.put(p, 0);
			p.playSound(p.getLocation(), Sound.ENTITY_GHAST_WARN, 0.5F, 1F);
		}

		blinkPower.put(p, power + 1);
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

		Vector vec = loc.getDirection().normalize();
		Location to = p.getLocation().clone();
		for (int i = 0; i<power; vec.multiply(i)) {
			loc.add(vec);
			if (loc.getBlock().getType() != Material.AIR) break;
			to = loc;
			loc.subtract(vec);
		}

		p.teleport(to);
		blinkPower.remove(p);
	}

}
