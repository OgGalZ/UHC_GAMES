package me.oggalz.uhc_games.listeners;

import fr.minuskube.inv.SmartInventory;
import fr.minuskube.netherboard.Netherboard;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.tasks.Pvp;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;


import java.util.Optional;

public class SecondaryListeners implements Listener {
    private final SmartInventory mainGUi;
    private final StateManager stateManager;
    private final PlayerManager playerManager;
    private final PvpGui pvpGui;
    private final Pvp pvp;

    public SecondaryListeners(SmartInventory mainGUi, StateManager stateManager, PlayerManager playerManager, PvpGui pvpGui, Pvp pvp) {
        this.mainGUi = mainGUi;
        this.stateManager = stateManager;
        this.playerManager = playerManager;
        this.pvpGui = pvpGui;
        this.pvp = pvp;
    }

    @EventHandler(priority = EventPriority.HIGH)

    public void playerInteractEvent(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        Optional<ItemStack> itemStack = Optional.ofNullable(event.getItem());
        if (itemStack.isPresent() && itemStack.get().getType() == Material.COMPASS && player.isOp() && stateManager.hasNotStarted() && itemStack.get().getItemMeta().getDisplayName().equals(ChatColor.BLUE + "Config")) {
            mainGUi.open(player);
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
            if (pvp.isEnablePvp()) {
                event.setCancelled(false);
                }
            }
        }


    @EventHandler(priority = EventPriority.HIGH)
    public void playerDeath(PlayerDeathEvent event) {
        Location location = event.getEntity().getLocation();
        Player player = event.getEntity().getKiller();
        Player playerDeath = event.getEntity();
        World world = event.getEntity().getWorld();
        if (stateManager.hasNotStarted()) {
            event.getDrops().clear();
        }
        if (pvpGui.getNumbersGaps() != 0 && stateManager.hasStarted()) {
            world.dropItem(location, Item.createItemstack(Material.GOLDEN_APPLE, pvpGui.getNumbersGaps(), null, null));
        }
        if (player != null && playerDeath != null) {
            playerManager.getPlayer(player.getUniqueId()).addKill(1);
            Netherboard.instance().getBoard(player).set(ChatColor.RED + "Kill(s) : " + playerManager.getPlayer(player.getUniqueId()).getKill(), 11);
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

    @EventHandler(priority = EventPriority.NORMAL)
    public void uhcEnable(EntityRegainHealthEvent event) {
        if(event.getEntity() instanceof Player ){
            Player player = (Player) event.getEntity();
            event.setCancelled(!player.hasPotionEffect(PotionEffectType.REGENERATION));
        }

    }
}