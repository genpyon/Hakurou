package com.genpyon.Role;

import java.util.Random;

import org.bukkit.event.Listener;

import com.genpyon.Main;

import net.md_5.bungee.api.ChatColor;




public class RoleManager implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public RoleManager(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}

	public static String roleNameChanger(String st) {
		String role = st;

		if(st.equalsIgnoreCase("INNOCENT")) {
			role = ChatColor.GREEN + "村人" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("TYOUROU")) {
			role = ChatColor.DARK_GREEN + "長老" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("DETECTIVE")) {
			role = ChatColor.BLUE + "探偵" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("MAGO")) {
			role = ChatColor.GREEN + "孫" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("WEREWOLF")) {
			role = ChatColor.RED + "人狼" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("HAKUROU")) {
			role = ChatColor.DARK_RED + "白狼" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("JACKAL")) {
			role = ChatColor.AQUA + "妖狐" + ChatColor.RESET;
		}
		return role;
	}


	public static String bookRoleNameChanger(String st) {
		String role = st;

		if(st.equalsIgnoreCase("INNOCENT")) {
			role = ChatColor.GREEN + "村人陣営" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("TYOUROU")) {
			role = ChatColor.GREEN + "村人陣営" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("DETECTIVE")) {
			role = ChatColor.GREEN + "村人陣営" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("MAGO")) {
			role = ChatColor.GREEN + "村人陣営" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("WEREWOLF")) {
			role = ChatColor.RED + "人狼陣営" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("HAKUROU")) {
			role = ChatColor.RED + "人狼陣営" + ChatColor.RESET;
		}
		if(st.equalsIgnoreCase("JACKAL")) {
			role = ChatColor.AQUA + "妖狐" + ChatColor.RESET;
		}
		return role;
	}

}
