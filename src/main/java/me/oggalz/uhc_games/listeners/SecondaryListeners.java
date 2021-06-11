package me.oggalz.uhc_games.listeners;

import fr.minuskube.inv.SmartInventory;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.MainGui;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.tasks.Pvp;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
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
        if ( itemStack != null && itemStack.getType() == Material.COMPASS && player.isOp() && stateManager.hasNotStarted() && itemStack.getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Config")) {
            MainGui.MainGUi.open(player);
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        event.setCancelled(stateManager.hasNotStarted());
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onTestEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getEntity() instanceof Player) {
                event.setCancelled(true);
            }
        } else if (Pvp.isEnablePvp()) {
            event.setCancelled(false);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerDeath(PlayerDeathEvent event) {
        Location location = event.getEntity().getLocation();
        World world = event.getEntity().getWorld();
        Player player = event.getEntity();
        player.getInventory().clear();
        if (PvpGui.getNumbersGaps() != 0) {
            world.dropItem(location, Item.createItemstack(Material.GOLDEN_APPLE, PvpGui.getNumbersGaps(), null, null));
        }
        player.getInventory().clear();

    }


}