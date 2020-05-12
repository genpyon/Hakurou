package com.genpyon.Event;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.genpyon.Main;
import com.genpyon.ItemStack.GameItemManager;
import com.genpyon.Library.lib;

import net.md_5.bungee.api.ChatColor;



public class ChatListener implements Listener {
	Random rnd = new Random();

	private Main plugin;

	public ChatListener(Main plugin) {
		this.plugin = plugin;

		this.plugin.getServer().getPluginManager().registerEvents(this, this.plugin);

		return;

	}


	@EventHandler
	public void PlayerChatEvent(AsyncPlayerChatEvent b) {
		b.setCancelled(true);
		Player p = b.getPlayer();


		String message = b.getMessage();

		String name = "<" + b.getPlayer().getName() + ">";

		if(Main.DEATH.contains(p.getName())) {
			for(Player a : Bukkit.getOnlinePlayers()) {
				if(Main.DEATH.contains(a.getName())) {
					lib.sendPlayer(a, ChatColor.GRAY + "[霊界]"+ name + message);
					lib.SoundAllPlayer(Sound.ENTITY_CHICKEN_EGG, 1.6F);
				}
			}
			b.setCancelled(true);
			return;
		}

		if(Main.WEREWOLF.contains(p.getName())) {
			if(p.getInventory().getItemInMainHand().equals(GameItemManager.WEREWOLF_CHAT_ITEM())){
				for(Player a : Bukkit.getOnlinePlayers()) {
					if(Main.ROLE.get(a.getName()).equalsIgnoreCase("HAKUROU") || Main.ROLE.get(a.getName()).equalsIgnoreCase("WEREWOLF")) {
						lib.sendPlayer(a, ChatColor.DARK_RED + " [狼ONLY]" + name + message);
					a.getInventory().removeItem(GameItemManager.WEREWOLF_CHAT_ITEM());
					} else {

					}
				}
				b.setCancelled(true);
				return;
			}
		}



		String CO = null;

		if(Main.CO.containsKey(p.getName())) {
			CO = Main.CO.get(p.getName());
		}

		if(CO == null) {
			CO = ChatColor.RESET + "[?]" + ChatColor.RESET;
		}

		if(CO.equalsIgnoreCase("INNOCENT")) {
			CO = ChatColor.GREEN + "[村人]" + ChatColor.RESET;
		}

		if(CO.equalsIgnoreCase("MAGO")) {
			CO = ChatColor.GREEN + "[孫]" + ChatColor.RESET;
		}

		if(CO.equalsIgnoreCase("TYOUROU")) {
			CO = ChatColor.DARK_GREEN + "[長老]" + ChatColor.RESET;
		}

		if(CO.equalsIgnoreCase("DETECTIVE")) {
			CO = ChatColor.BLUE + "[探偵]" + ChatColor.RESET;
		}

		if(CO.equalsIgnoreCase("WEREWOLF")) {
			CO = ChatColor.RED + "[人狼]" + ChatColor.RESET;
		}

		if(CO.equalsIgnoreCase("HAKUROU")) {
			CO = ChatColor.DARK_RED + "[白狼]" + ChatColor.RESET;
		}

		if(CO.equalsIgnoreCase("JACKAL")) {
			CO = ChatColor.AQUA + "[妖狐]" + ChatColor.RESET;
		}

		if(CO.equalsIgnoreCase("GRAY")) {
			CO = ChatColor.GRAY + "[グレー]" + ChatColor.RESET;
		}

		String sendMessage = CO + name + message;

		Bukkit.broadcastMessage(sendMessage);
		lib.SoundAllPlayer(Sound.ENTITY_CHICKEN_EGG, 1.6F);
	}


}
