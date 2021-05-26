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

import javax.swing.*;

public class MainGui implements Listener {
    private final InventoryClass inventory;
    private final Item item;
    private final StateManager stateManager;


    public MainGui(InventoryClass inventory, Item item, StateManager stateManager) {
        this.inventory = inventory;
        this.item = item;
        this.stateManager = stateManager;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerClickEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        Action action = event.getAction();
        if (stateManager.hasNotStarted() && player.isOp()) {
            if (itemStack.getType() == Material.COMPASS && itemStack.hasItemMeta()) {

                Inventory inventory = this.inventory.createdInventory(9 * 6, ChatColor.DARK_BLUE + "Configuration");
                inventory.setItem(22, item.createItemstack(Material.COMMAND_MINECART, 1));
                player.openInventory(inventory);

            }

        }

    }
}
