package com.genpyon.Manager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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



	public ItemStack roleHead(String name){

		ItemStack head = roleHead(name ,
				ChatColor.RESET + "" + ChatColor.GRAY + "===========",
				ChatColor.YELLOW + "未発見",
				name,
				ChatColor.RESET + "" + ChatColor.GRAY + "役職 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "死因 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "死亡時間 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "===========");

		return head;
	}


	public ItemStack roleHeadChange(String name){

		ItemStack head = roleHead(name ,
				ChatColor.RESET + "" + ChatColor.GRAY + "===========",
				ChatColor.GREEN + "発見済み",
				name,
				ChatColor.RESET + "" + ChatColor.GRAY + "役職 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "死因 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "死亡時間 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "===========");

		return head;
	}

	public ItemStack roleHeadChangeDetective(String name){

		String role = null;
		if(plugin.ROLE.containsKey(name)){
			role = plugin.ROLE.get(name);
		}

		ItemStack head = roleHead(name ,
				ChatColor.RESET + "" + ChatColor.GRAY + "===========",
				ChatColor.GREEN + "発見済み",
				name,
				ChatColor.RESET + "" + ChatColor.GRAY + "役職 : " + ChatColor.RED + role,
				ChatColor.RESET + "" + ChatColor.GRAY + "死因 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "死亡時間 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "===========");

		return head;
	}


	public ItemStack roleHead(String name, String... list){

		List<String> lore = Arrays.asList(list);

		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(name);
		meta.setDisplayName(ChatColor.RESET + name + " の生首");

		meta.setLore(lore);
		skull.setItemMeta(meta);

		return skull;
	}


	@EventHandler
	public void playerHeadChange (PlayerInteractEvent b){
		Player p = b.getPlayer();

		Action act = b.getAction();

		if(act == Action.LEFT_CLICK_AIR || act == Action.RIGHT_CLICK_AIR || act == Action.LEFT_CLICK_BLOCK || act == Action.RIGHT_CLICK_BLOCK){


			if(p.getInventory().getItemInMainHand() != null && p.getInventory().getItemInMainHand().getType().equals(Material.SKULL_ITEM)){

				String name = p.getInventory().getItemInMainHand().getItemMeta().getLore().get(2);
				String hakken = p.getInventory().getItemInMainHand().getItemMeta().getLore().get(1);
				Bukkit.broadcastMessage(hakken);


				if(plugin.ROLE.containsKey(name)){

						if(plugin.ROLE.get(p.getName()).equalsIgnoreCase("DETECTIVE")){
							p.getInventory().remove(p.getInventory().getItemInMainHand());
							p.getInventory().addItem(roleHeadChangeDetective(name));

						} else if(hakken.equalsIgnoreCase(ChatColor.YELLOW + "未発見")){

							p.getInventory().remove(p.getInventory().getItemInMainHand());
							p.getInventory().addItem(roleHeadChange(name));
						}

						if(hakken.equalsIgnoreCase(ChatColor.YELLOW + "未発見")){
							Bukkit.broadcastMessage(ChatColor.RESET + p.getName() + " が " + ChatColor.RED + name + ChatColor.WHITE + "の生首を発見した。");
						}


					return;
				} else {
					Bukkit.broadcastMessage("それですけど、それじゃないです。");
					Bukkit.broadcastMessage(plugin.ROLE.toString());
				}

			} else {
				//Bukkit.broadcastMessage("それじゃありません!!");
			}

		} else {

		}
	}

}
