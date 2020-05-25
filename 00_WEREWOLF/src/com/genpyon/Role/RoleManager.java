package com.genpyon.Role;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
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

	public static int nokoriInteger(String ROLE){
		int nokori = 0;

		for(Player a : Bukkit.getOnlinePlayers()) {
			if(Main.ROLE.containsKey(a.getName()) && Main.ROLE.get(a.getName()).equalsIgnoreCase(ROLE))
				if(!Main.FOUND.containsKey(a.getName())){
					nokori = nokori+1;
				}
		}
		return nokori;
	}


	public static String Haiyaku() {
		String role = "";
		int mura = 0;
		int mago = 0;
		int tyour = 0;
		int tante = 0;
		int zinro = 0;
		int hakuro = 0;
		int youko = 0;


		if(Main.ROLE.size() != 0) {

			for(String s : Main.ROLE.values()) {

				if(s.equalsIgnoreCase("INNOCENT")) {
					mura = mura+1;
				}
				if(s.equalsIgnoreCase("MAGO")) {
					mago = mago+1;
				}
				if(s.equalsIgnoreCase("TYOUROU")) {
					tyour = tyour+1;
				}
				if(s.equalsIgnoreCase("DETECTIVE")) {
					tante = tante+1;
				}
				if(s.equalsIgnoreCase("WEREWOLF")) {
					zinro = zinro+1;
				}
				if(s.equalsIgnoreCase("HAKUROU")) {
					hakuro = hakuro+1;
				}
				if(s.equalsIgnoreCase("JACKAL")) {
					youko = youko+1;
				}
			}
		}

		if(mura != 0) {
			role = role + ChatColor.GREEN + "村人" + mura + ChatColor.RESET + " ";
		}
		if(mago != 0) {
			role = role + ChatColor.GREEN + "孫" + mago + ChatColor.RESET + " ";
		}
		if(tyour != 0) {
			role = role + ChatColor.DARK_GREEN + "長老" + tyour + ChatColor.RESET + " ";
		}
		if(tante != 0) {
			role = role + ChatColor.BLUE + "探偵" + tante + ChatColor.RESET + " ";
		}
		if(zinro != 0) {
			role = role + ChatColor.RED + "人狼" + zinro + ChatColor.RESET + " ";
		}
		if(hakuro != 0) {
			role = role + ChatColor.DARK_RED + "白狼" + hakuro + ChatColor.RESET + " ";
		}
		if(youko != 0) {
			role = role + ChatColor.AQUA + "妖狐" + youko + ChatColor.RESET + " ";
		}
		return role;
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
		if(st.equalsIgnoreCase("GRAY")) {
			role = ChatColor.GRAY + "グレー" + ChatColor.RESET;
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
