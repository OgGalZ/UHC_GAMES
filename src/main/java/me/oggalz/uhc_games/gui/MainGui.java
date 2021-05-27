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


public class MainGui implements Listener {

    private final StateManager stateManager;


    public MainGui(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerClickEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        Action action = event.getAction();


        if (itemStack == null) {
            return;
        }

        if (itemStack.getType() == Material.COMPASS && itemStack.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "CONFIGURATION") ) {

                Inventory inventory = InventoryClass.createdInventory(9 * 6, ChatColor.DARK_BLUE + "Configuration");
                inventory.setItem(21, Item.createItemstack(Material.DIRT, 1, ChatColor.DARK_AQUA + "Scénario"));
                player.openInventory(inventory);

        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {
        ItemStack itemStack = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        if (itemStack == null) {
        } else if(inventory.getName().equals(ChatColor.DARK_BLUE + "Configuration")) {
            player.closeInventory();
        }
    }

}

