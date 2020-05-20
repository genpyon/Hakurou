package com.genpyon.Event;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;

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

	static ChatColor cc = ChatColor.RESET;



	@EventHandler
	public void comingOutEvent(InventoryClickEvent b) {
		Player p = (Player) b.getWhoClicked();
		ItemStack click = b.getCurrentItem();
		String role = null;

		if(click == null){
			return;
		}

		if (click.getItemMeta() != null &&
				click.getItemMeta().getLore() != null &&
				click.getItemMeta().getLore().contains(Main.unbreakitem)){
			b.setCancelled(true);
		} else {

		}

		if (click.equals(GameItemManager.CO_INNOCENT())){
			b.setCancelled(true);
			role = ChatColor.GREEN + "村人" + ChatColor.RESET;
			if(Main.CO.containsKey(p.getName()) && Main.CO.get(p.getName()).equalsIgnoreCase("INNOCENT")) {
				lib.sendPlayer(p, Main.system + "すでに"+ role + "を宣言をしています。");
				return;
			} else {
				lib.setLeatherHead(p, Color.GREEN, GameItemManager.CO_INNOCENT_HEAD, Main.unbreakitem);
				Main.CO.put(p.getName(),"INNOCENT");
			}
		}

		if (click.equals(GameItemManager.CO_MAGO())){
			b.setCancelled(true);
			role = ChatColor.GREEN + "孫" + ChatColor.RESET;

			if(Main.CO.containsKey(p.getName()) && Main.CO.get(p.getName()).equalsIgnoreCase("MAGO")) {
				lib.sendPlayer(p, Main.system + "すでに"+ role + "を宣言をしています。");
				return;
			} else {
				lib.setLeatherHead(p, Color.GRAY, GameItemManager.CO_GRAY_HEAD, Main.unbreakitem);
				Main.CO.put(p.getName(),"MAGO");
			}
		}

		if (click.equals(GameItemManager.CO_TYOUROU())){
			b.setCancelled(true);
			role = ChatColor.DARK_GREEN + "長老" + ChatColor.RESET;
			if(Main.CO.containsKey(p.getName()) && Main.CO.get(p.getName()).equalsIgnoreCase("TYOUROU")) {
				lib.sendPlayer(p, Main.system + "すでに"+ role + "を宣言をしています。");
				return;
			} else {
				lib.setLeatherHead(p, Color.GRAY, GameItemManager.CO_GRAY_HEAD, Main.unbreakitem);
				Main.CO.put(p.getName(),"TYOUROU");
			}
		}

		if (click.equals(GameItemManager.CO_DETECTIVE())){
			b.setCancelled(true);
			role = ChatColor.BLUE + "探偵" + ChatColor.RESET;
			if(Main.CO.containsKey(p.getName()) && Main.CO.get(p.getName()).equalsIgnoreCase("DETECTIVE")) {
				lib.sendPlayer(p, Main.system + "すでに"+ role + "を宣言をしています。");
				return;
			} else {
				lib.setLeatherHead(p, Color.BLUE, GameItemManager.CO_DETECTIVE_HEAD, Main.unbreakitem);
				Main.CO.put(p.getName(),"DETECTIVE");
			}
		}

		if (click.equals(GameItemManager.CO_WEREWOLF())){
			b.setCancelled(true);
			role = ChatColor.RED + "人狼" + ChatColor.RESET;
			if(Main.CO.containsKey(p.getName()) && Main.CO.get(p.getName()).equalsIgnoreCase("WEREWOLF")) {
				lib.sendPlayer(p, Main.system + "すでに"+ role + "を宣言をしています。");
				return;
			} else {
				lib.setLeatherHead(p, Color.RED, GameItemManager.CO_WEREWOLF_HEAD, Main.unbreakitem);
				Main.CO.put(p.getName(),"WEREWOLF");
			}
		}

		if (click.equals(GameItemManager.CO_HAKUROU())){
			b.setCancelled(true);
			role = ChatColor.DARK_RED + "白狼" + ChatColor.RESET;
			if(Main.CO.containsKey(p.getName()) && Main.CO.get(p.getName()).equalsIgnoreCase("HAKUROU")) {
				lib.sendPlayer(p, Main.system + "すでに"+ role + "を宣言をしています。");
				return;
			} else {
				lib.setLeatherHead(p, Color.RED, GameItemManager.CO_HAKUROU_HEAD, Main.unbreakitem);
				Main.CO.put(p.getName(),"HAKUROU");
			}
		}

		if (click.equals(GameItemManager.CO_JACKAL())){
			b.setCancelled(true);
			role = ChatColor.AQUA + "妖狐" + ChatColor.RESET;
			if(Main.CO.containsKey(p.getName()) && Main.CO.get(p.getName()).equalsIgnoreCase("JACKAL")) {
				lib.sendPlayer(p, Main.system + "すでに"+ role + "を宣言をしています。");
				return;
			} else {
				lib.setLeatherHead(p, Color.AQUA, GameItemManager.CO_JACKAL_HEAD, Main.unbreakitem);
				Main.CO.put(p.getName(),"JACKAL");
			}
		}

		if (click.equals(GameItemManager.CO_GRAY())){
			b.setCancelled(true);
			role = ChatColor.GRAY + "グレー" + ChatColor.RESET;
			if(Main.CO.containsKey(p.getName()) && Main.CO.get(p.getName()).equalsIgnoreCase("GRAY")) {
				lib.sendPlayer(p, Main.system + "すでに"+ role + "を宣言をしています。");
				return;
			} else {
				lib.setLeatherHead(p, Color.GRAY, GameItemManager.CO_GRAY_HEAD, Main.unbreakitem);
				Main.CO.put(p.getName(),"GRAY");
			}
		}

		if(role != null) {
			String sengen = ChatColor.RED + " [!] " + ChatColor.YELLOW + p.getName() + ChatColor.RESET +  " は " + role + " を宣言しました。";
			Bukkit.broadcastMessage(sengen);
			lib.SoundAllPlayer(Sound.ENTITY_CHICKEN_EGG, 1.6F);
		} else {
			return;
		}

		return;
	}


	@EventHandler
	public void PlayerChatEvent(AsyncPlayerChatEvent b) {

		Player p = b.getPlayer();

		if(plugin.GameStatus != 3) {
			return;
		}

		b.setCancelled(true);

		String message = b.getMessage();

		String name = "<" + b.getPlayer().getName() + ">";

		if(Main.DEATH.contains(p.getName()) || !Main.PLAYER.contains(p.getName())) {
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
					if(Main.ROLE.containsKey(a.getName()) && Main.ROLE.get(a.getName()).equalsIgnoreCase("HAKUROU") || Main.ROLE.get(a.getName()).equalsIgnoreCase("WEREWOLF")) {
						lib.sendPlayer(a, ChatColor.DARK_RED + " [狼ONLY]" + name + message);
						p.getInventory().removeItem(GameItemManager.WEREWOLF_CHAT_ITEM());
					} else {

					}
				}
				b.setCancelled(true);
				return;
			}
		}




		String sendMessage = coNameChanger(p.getName()) + name + message;

		if(Main.CO.containsKey(p.getName())){
			if(Main.CO.get(p.getName()).equalsIgnoreCase("DETECTIVE")){
				lib.SoundAllPlayer(Sound.ENTITY_IRONGOLEM_HURT, 10F);
			}
		}

		Bukkit.broadcastMessage(sendMessage);
		lib.SoundAllPlayer(Sound.ENTITY_CHICKEN_EGG, 1.6F);

		if(cc.equals(ChatColor.RESET)) {
			cc = ChatColor.RESET;
		} else {
			cc = ChatColor.RESET;
		}
	}

	public static String coNameChanger(String name) {
		String CO = null;

		if(Main.CO.containsKey(name)) {
			CO = Main.CO.get(name);
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
		return CO;
	}


}






















































