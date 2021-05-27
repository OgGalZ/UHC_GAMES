package me.oggalz.uhc_games.gui;


import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.InventoryClass;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainGui implements Listener {

    private final StateManager stateManager;


    private final List<ItemStack> item = new ArrayList<>();

    public MainGui(StateManager stateManager) {
        this.stateManager = stateManager;
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.DIAMOND, 1, ChatColor.RED + "Scénarios", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.WOOL, 1, ChatColor.YELLOW + "Bordure", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.DIAMOND_SWORD, 1, ChatColor.DARK_BLUE + "PVP", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.BOOK, 1, ChatColor.GREEN + "Roles", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.EMERALD_BLOCK, 1, ChatColor.GOLD + "Start", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.CHEST, 1, ChatColor.GRAY + "Inventaire", null));


    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerClickEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        Action action = event.getAction();


        if (itemStack == null) {
            return;
        }

        if (itemStack.getType() == Material.COMPASS && itemStack.getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Config")) {
            Inventory inventory = InventoryClass.createdInventory(9 * 6, "Configuration");

            inventory.setItem(21, item.get(0));
            inventory.setItem(22, item.get(1));
            inventory.setItem(23, item.get(2));
            inventory.setItem(30, item.get(3));
            inventory.setItem(31, item.get(4));
            inventory.setItem(32, item.get(5));
            player.openInventory(inventory);

        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) throws IOException {
        ItemStack itemStack = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();

        if (itemStack == null) {
        } if(inventory.getName().equals("Configuration")){
            player.closeInventory();
        }
    }
}

