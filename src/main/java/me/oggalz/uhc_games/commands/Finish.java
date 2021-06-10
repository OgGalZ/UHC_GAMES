package me.oggalz.uhc_games.commands;

import me.oggalz.uhc_games.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Finish implements CommandExecutor {
    private static ItemStack[] itemStacks = null;

    @Override
    public boolean onCommand(CommandSender commandSender, Command sender, String label, String[] args) {
        if (label.equals("finish") && commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length == 0 && player.isOp() && player.getGameMode() == GameMode.CREATIVE) {
                player.sendMessage("C'est fait ! ");
                itemStacks = player.getInventory().getContents();
                player.setGameMode(GameMode.ADVENTURE);
                player.getInventory().clear();
                player.getInventory().addItem(Item.createItemstack(Material.COMPASS, 1, ChatColor.BLUE + "Config", null));


            }
            return true;
        }

        return false;
    }

    public static ItemStack[] getItemStacks() {
        return itemStacks;
    }
}
