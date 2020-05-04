package com.genpyon.Manager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.genpyon.Main;

import net.md_5.bungee.api.ChatColor;



public class SkullManager implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public SkullManager(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}

	@SuppressWarnings("deprecation")
	public void haveSkullCheck(Player a){
		Inventory inv = a.getInventory();
		if(inv.contains(Material.SKULL_ITEM)){
			Location loc = a.getLocation();
			loc.setY(loc.getY()+2);
			a.getWorld().playEffect(loc, Effect.HEART,10);
			//lib.sendPlayer(a, "頭持ってね？");
		}
	}



	public ItemStack roleHeadSpawn(String name){

		ItemStack head = roleHead(name ,
		ChatColor.RESET + "" + ChatColor.GRAY + "===========",
		ChatColor.RESET + "" + ChatColor.GRAY + "ID : " + ChatColor.RED + name,
		ChatColor.RESET + "" + ChatColor.GRAY + "役職 : " + ChatColor.RED + "???",
		ChatColor.RESET + "" + ChatColor.GRAY + "死因 : " + ChatColor.RED + "???",
		ChatColor.RESET + "" + ChatColor.GRAY + "死亡時間 : " + ChatColor.RED + "???",
		ChatColor.RESET + "" + ChatColor.GRAY + "===========");

		return head;
	}


	public ItemStack roleHeadChange(String name){

		ItemStack head = roleHead(name ,
		ChatColor.RESET + "" + ChatColor.GRAY + "===========",
		ChatColor.RESET + "" + ChatColor.GRAY + "ID : " + ChatColor.RED + name,
		ChatColor.RESET + "" + ChatColor.GRAY + "役職 : ",
		ChatColor.RESET + "" + ChatColor.GRAY + "死因 : ",
		ChatColor.RESET + "" + ChatColor.GRAY + "死亡時間 : ",
		ChatColor.RESET + "" + ChatColor.GRAY + "===========");

		return head;
	}


	public ItemStack roleHead(String name, String... list){

		List<String> lore = Arrays.asList(list);

		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(name);
		meta.setDisplayName(ChatColor.RESET + "" +ChatColor.GOLD + name + ChatColor.RESET + " の頭");

		meta.setLore(lore);
		skull.setItemMeta(meta);

		return skull;
	}

}
