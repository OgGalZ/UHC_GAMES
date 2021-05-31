package me.oggalz.uhc_games.gui;


import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.scenarios.CutClean;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;


public class MainGui implements Listener {

    private final StateManager stateManager;
    private final List<ItemStack> item = new ArrayList<>();
    private final Main main;


    public MainGui(StateManager stateManager, Main main) {
        this.stateManager = stateManager;
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.DIAMOND, 1, ChatColor.RED + "Scenarios", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.WOOL, 1, ChatColor.YELLOW + "Bordure", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.ARROW, 1, ChatColor.DARK_BLUE + "PVP", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.BOOK, 1, ChatColor.GREEN + "Roles", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.EMERALD_BLOCK, 1, ChatColor.GOLD + "Start", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.CHEST, 1, ChatColor.GRAY + "Inventaire", null));
        this.main = main;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerClickEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        Action action = event.getAction();
        Inventory inventory = Bukkit.createInventory(null, 9 * 4, "Config");

        inventory.setItem(12, item.get(0));
        inventory.setItem(13, item.get(1));
        inventory.setItem(14, item.get(2));
        inventory.setItem(21, item.get(3));
        inventory.setItem(22, item.get(4));
        inventory.setItem(23, item.get(5));


        if (itemStack == null) {
            return;
        }

        if (itemStack.getType() == Material.COMPASS && itemStack.getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Config")) {

            player.openInventory(inventory);
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        if (itemStack.hasItemMeta()) {
            event.setCancelled(true);
        }
    }


}



