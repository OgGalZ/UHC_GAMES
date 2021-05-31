package me.oggalz.uhc_games.gui;

import me.oggalz.uhc_games.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GCutClean implements Listener {
    @EventHandler(priority = EventPriority.NORMAL)
    public void onClick(InventoryClickEvent event) {

        ItemStack itemStack = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        InventoryAction action = event.getAction();
        Inventory gui = Bukkit.createInventory(null, 9 * 4, ChatColor.DARK_AQUA + "Scenarios");
        gui.setItem(11, Item.createItemstack(Material.IRON_INGOT, 1, ChatColor.RED + "Cut Clean", null));
        gui.setItem(15, Item.createItemstack(Material.APPLE, 1, ChatColor.BLUE + "Vanilla+", null));
        gui.setItem(21, Item.createItemstack(Material.DIAMOND_ORE, 1, ChatColor.DARK_BLUE + "Diamond Limite ", null));
        gui.setItem(23, Item.createItemstack(Material.DIAMOND_PICKAXE, 1, ChatColor.GREEN + "Hasty Boy", null));
        gui.setItem(31, Item.createItemstack(Material.POTION, 1, ChatColor.WHITE + "Final HEal", null));
        if (itemStack == null) {
        } else if (itemStack.getType() == Material.DIAMOND && itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Scenarios")) {

            player.openInventory(gui);
        }


    }
}