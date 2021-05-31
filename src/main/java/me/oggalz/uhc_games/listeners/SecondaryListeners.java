package me.oggalz.uhc_games.listeners;

import fr.minuskube.inv.SmartInventory;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.MainGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class SecondaryListeners implements Listener {

    private StateManager stateManager;

    public SecondaryListeners(StateManager stateManager) {

        this.stateManager = stateManager;

    }


    @EventHandler(priority = EventPriority.HIGH)

    public void playerInteractEvent(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        if (itemStack == null) {
            return;
        }
       else if (itemStack.getType() == Material.COMPASS && player.isOp() && stateManager.hasNotStarted() &&    itemStack.getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Config")){
           MainGui.MainGUi.open(player);
        }


    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (stateManager.hasNotStarted()) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }


    }


}