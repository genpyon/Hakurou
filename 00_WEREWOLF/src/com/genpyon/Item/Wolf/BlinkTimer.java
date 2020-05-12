package com.genpyon.Item.Wolf;

import org.bukkit.scheduler.BukkitRunnable;

public class BlinkTimer extends BukkitRunnable {

	@Override
	public void run() {
		Blink.chargeBlink();
	}

}
