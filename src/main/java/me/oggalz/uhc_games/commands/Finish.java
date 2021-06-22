package me.oggalz.uhc_games.commands;

import me.oggalz.uhc_games.utils.Item;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Objects;


public class Finish implements CommandExecutor {
    private static ItemStack[] itemStacks = null;
    private static ItemStack[] armor = null;
    private  boolean check = false;


    @Override
    public boolean onCommand(CommandSender commandSender, Command sender, String label, String[] args) {
        if (label.equals("finish") && commandSender instanceof Player) {
            Player player = (Player) commandSender;
            if (args.length == 0 && player.isOp() && player.getGameMode() == GameMode.CREATIVE) {
                if(check){
                    Arrays.stream(Finish.getItemStacks()).filter(Objects::nonNull).forEach(i -> i.setType(Material.AIR));
                    Arrays.stream(Finish.getArmor()).filter(Objects::nonNull).forEach(a -> a.setType(Material.AIR));
                }
                check = true;
                player.sendMessage("C'est fait ! ");
                itemStacks = player.getInventory().getContents();
                armor = player.getInventory().getArmorContents();
               player.setGameMode(GameMode.ADVENTURE);
                player.getInventory().clear();
                Item.clearArmor(player);
                player.getInventory().addItem(Item.createItemstack(Material.COMPASS, 1, ChatColor.BLUE + "Config", null));

                return true;

            }
        }

        return false;
    }

    public static ItemStack[] getItemStacks() {
        return itemStacks;
    }

    public static ItemStack[] getArmor() {
        return armor;
    }
}
