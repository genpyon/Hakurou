package com.genpyon.Manager;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.genpyon.Main;
import com.genpyon.Role.RoleManager;

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

		ItemStack head = roleHeads(ChatColor.YELLOW + "[未発見]"+ ChatColor.RESET ,name ,
				ChatColor.RESET + "" + ChatColor.GRAY + "===========",
				ChatColor.YELLOW + "未発見",
				name,
				ChatColor.RESET + "" + ChatColor.GRAY + "役職 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "死因 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "死亡時間 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "===========");

		return head;
	}


	public static ItemStack roleHeadChange(String name){

		ItemStack head = roleHeads(ChatColor.GREEN + "[発見済み]"+ ChatColor.RESET ,name ,
				ChatColor.RESET + "" + ChatColor.GRAY + "===========",
				ChatColor.GREEN + "発見済み",
				name,
				ChatColor.RESET + "" + ChatColor.GRAY + "役職 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "死因 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "死亡時間 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "===========");

		return head;
	}

	public static ItemStack roleHeadChangeDetective(String name){

		String role = null;
		if(Main.ROLE.containsKey(name)){
			role = Main.ROLE.get(name);
		}

		ItemStack head = roleHeads(ChatColor.GREEN + "[発見済み]"+ ChatColor.RESET ,name ,
				ChatColor.RESET + "" + ChatColor.GRAY + "===========",
				ChatColor.GREEN + "発見済み",
				name,
				ChatColor.RESET + "" + ChatColor.GRAY + "役職 : " + ChatColor.RED + RoleManager.bookRoleNameChanger(role),
				ChatColor.RESET + "" + ChatColor.GRAY + "死因 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "死亡時間 : " + ChatColor.RED + "???",
				ChatColor.RESET + "" + ChatColor.GRAY + "===========");

		return head;
	}


	public static ItemStack roleHeads(String found, String name, String... list){

		List<String> lore = Arrays.asList(list);
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(name);
		meta.setDisplayName(found + name + "の頭");

		meta.setLore(lore);
		skull.setItemMeta(meta);

		return skull;
	}

	public static ItemStack baseHeads(String name, Enchantment enc, int Lv , String... list){

		List<String> lore = Arrays.asList(list);
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(name);
		meta.setDisplayName(name);

		meta.setLore(lore);
		skull.setItemMeta(meta);
		if(enc != null) {
			skull.addUnsafeEnchantment(enc, Lv);
		}

		return skull;
	}



	public static ItemStack uranaiHeads(String name, String... list){

		List<String> lore = Arrays.asList(list);

		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());

		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		meta.setOwner(name);

		meta.setDisplayName(name);

		meta.setLore(lore);
		skull.setItemMeta(meta);

		return skull;
	}






}
