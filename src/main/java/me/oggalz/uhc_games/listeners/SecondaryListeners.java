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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.inventory.ItemStack;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class SecondaryListeners implements Listener {

    private StateManager stateManager;


    public SecondaryListeners(StateManager stateManager) {
        this.stateManager = stateManager;

    }

    @EventHandler(priority = EventPriority.HIGH)

    public void playerInteractEvent(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Optional<ItemStack> itemStack = Optional.ofNullable(event.getItem());
        if (itemStack.isPresent() && itemStack.get().getType() == Material.COMPASS && player.isOp() && stateManager.hasNotStarted() && itemStack.get().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Config")) {
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
        if (stateManager.hasNotStarted()) {
            event.getDrops().clear();
        }
        if (PvpGui.getNumbersGaps() != 0) {
            world.dropItem(location, Item.createItemstack(Material.GOLDEN_APPLE, PvpGui.getNumbersGaps(), null, null));
        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void playerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        player.getInventory().clear();

        if (player.isOp() && stateManager.hasNotStarted()) {
            ItemStack itemStack = me.oggalz.uhc_games.utils.Item.createItemstack(Material.COMPASS, 1, ChatColor.BLUE + "Config", null);
            player.getInventory().addItem(itemStack);
        }
    }
}