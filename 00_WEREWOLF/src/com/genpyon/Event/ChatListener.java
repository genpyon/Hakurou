package com.genpyon.Event;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
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

	ChatColor cc = ChatColor.RESET;


	@EventHandler
	public void PlayerChatEvent(AsyncPlayerChatEvent b) {

		Player p = b.getPlayer();

		if(plugin.GameStatus != 3) {
			return;
		}

		b.setCancelled(true);

		String message = b.getMessage();

		String name = "<" + b.getPlayer().getName() + ">";

		if(Main.DEATH.contains(p.getName())) {
			for(Player a : Bukkit.getOnlinePlayers()) {
				if(Main.DEATH.contains(a.getName())) {
					lib.sendPlayer(a, ChatColor.GRAY + "[霊界]"+ name + message);
					lib.SoundPlayer(a,Sound.ENTITY_CHICKEN_EGG, 1.6F);
				}
			}
			b.setCancelled(true);
			return;
		}

		if(Main.WEREWOLF.contains(p.getName()) || Main.HAKUROU.contains(p.getName())) {
			if(p.getInventory().getItemInMainHand().getType().equals(Material.BONE)){
				for(Player a : Bukkit.getOnlinePlayers()) {
					if(Main.ROLE.get(a.getName()).equalsIgnoreCase("HAKUROU") || Main.ROLE.get(a.getName()).equalsIgnoreCase("WEREWOLF")) {
						lib.sendPlayer(a, ChatColor.DARK_RED + " [狼ONLY]" + name + message);
						p.getInventory().removeItem(GameItemManager.WEREWOLF_CHAT_ITEM());
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
			CO = ChatColor.RESET + "[?]" + cc;
		}

		if(CO.equalsIgnoreCase("INNOCENT")) {
			CO = ChatColor.GREEN + "[村人]" + cc;
		}

		if(CO.equalsIgnoreCase("MAGO")) {
			CO = ChatColor.GREEN + "[孫]" + cc;
		}

		if(CO.equalsIgnoreCase("TYOUROU")) {
			CO = ChatColor.DARK_GREEN + "[長老]" + cc;
		}

		if(CO.equalsIgnoreCase("DETECTIVE")) {
			CO = ChatColor.BLUE + "[探偵]" + cc;
		}

		if(CO.equalsIgnoreCase("WEREWOLF")) {
			CO = ChatColor.RED + "[人狼]" + cc;
		}

		if(CO.equalsIgnoreCase("HAKUROU")) {
			CO = ChatColor.DARK_RED + "[白狼]" + cc;
		}

		if(CO.equalsIgnoreCase("JACKAL")) {
			CO = ChatColor.AQUA + "[妖狐]" + cc;
		}

		if(CO.equalsIgnoreCase("GRAY")) {
			CO = ChatColor.GRAY + "[グレー]" + cc;
		}

		String sendMessage = CO + name + message;

		Bukkit.broadcastMessage(sendMessage);
		lib.SoundAllPlayer(Sound.ENTITY_CHICKEN_EGG, 1.6F);

		if(cc.equals(ChatColor.RESET)) {
			cc = ChatColor.GRAY;
		} else {
			cc = ChatColor.RESET;
		}
	}


}
