package com.cryptic.repair;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	ConsoleCommandSender clogger = this.getServer().getConsoleSender();

	@Override
	public void onEnable() {
		clogger.sendMessage(ChatColor.AQUA + "-------------------------------------------------------------------");
		clogger.sendMessage("---                 " + ChatColor.GREEN + "RP(Repair) v1.0.0 is enabled" + ChatColor.AQUA
				+ "                ---");
		clogger.sendMessage(ChatColor.AQUA + "-------------------------------------------------------------------");
	}

	@Override
	public void onDisable() {
		clogger.sendMessage(ChatColor.AQUA + "-------------------------------------------------------------------");
		clogger.sendMessage(ChatColor.AQUA + "---          " + ChatColor.RED + "RP(Repair) v1.0.0 is disbaled"
				+ ChatColor.AQUA + "                      ---");
		clogger.sendMessage(ChatColor.AQUA + "-------------------------------------------------------------------");
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
		final Player player = (Player) sender;

		if (command.getName().equalsIgnoreCase("rp")) {
			if (!sender.hasPermission("rp")) {
				sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + "Usage:");
				sender.sendMessage(ChatColor.GREEN + "/rp hand");
				sender.sendMessage(ChatColor.AQUA + "Repair the item in your hand");
				sender.sendMessage(ChatColor.GREEN + "/rp bar");
				sender.sendMessage(ChatColor.AQUA + "Repair all items in your hotbar");
				sender.sendMessage(ChatColor.GREEN + "/rp all");
				sender.sendMessage(ChatColor.AQUA + "Repair all items in your inventory");
				return true;
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("hand")) {
					if (!sender.hasPermission("rp.hand")) {
						sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command");
						return true;
					}
					if (canRepair(player.getInventory().getItemInMainHand().getType().name())) {
						if (player.getInventory().getItemInMainHand().getDurability() > 0) {
							player.getInventory().getItemInMainHand().setDurability((short) 0);

						}
						sender.sendMessage(ChatColor.AQUA + "Repaired item in your hand");
						return true;
					}
				}

				else if (args[0].equalsIgnoreCase("bar")) {
					if (!sender.hasPermission("rp.bar")) {
						sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command");
						return true;
					}
					for (int i = 0; i < 9; i++) {
						if (player.getInventory().getItem(i) != null) {
							if (canRepair(player.getInventory().getItem(i).getType().name())) {
								if (player.getInventory().getItem(i).getDurability() > 0) {
									player.getInventory().getItem(i).setDurability((short) 0);
								}
							}
						}
					}
					sender.sendMessage(ChatColor.AQUA + "Repaired all items in your hotbar");
					return true;
				} else if (args[0].equalsIgnoreCase("all")) {
					if (!sender.hasPermission("rp.all")) {
						sender.sendMessage(ChatColor.DARK_RED + "You do not have access to that command");
						return true;
					}
					for (int i = 0; i < 41; i++) {
						if (player.getInventory().getItem(i) != null) {
							if (canRepair(player.getInventory().getItem(i).getType().name())) {
								if (player.getInventory().getItem(i).getDurability() > 0) {
									player.getInventory().getItem(i).setDurability((short) 0);
								}
							}
						}
					}
					sender.sendMessage(ChatColor.AQUA + "Repaired all items in your inventory");
					return true;
				}
			}
			return true;

		}
		return true;

	}

	private boolean canRepair(String itemType) {
		if ((itemType.contains("HELMET")) || (itemType.contains("CHESTPLATE")) || (itemType.contains("LEGGINGS"))
				|| (itemType.contains("BOOTS")) || (itemType.contains("AXE")) || (itemType.contains("BOW"))
				|| (itemType.contains("FISHING_ROD")) || (itemType.contains("SWORD")) || (itemType.contains("HOE"))
				|| (itemType.contains("PICKAXE")) || (itemType.contains("SPADE")) || (itemType.contains("CARROT_STICK"))
				|| (itemType.contains("FLINT_AND_STEEL")) || (itemType.contains("SHEARS"))) {
			return true;
		}
		return false;
	}
}