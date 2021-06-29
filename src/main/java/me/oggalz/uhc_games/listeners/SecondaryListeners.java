package me.oggalz.uhc_games.listeners;

import fr.minuskube.inv.SmartInventory;
import fr.minuskube.netherboard.Netherboard;
import me.oggalz.uhc_games.gui.MainGui;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.ScenariosGui;
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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.swing.table.TableRowSorter;
import java.io.IOException;
import java.util.Optional;

public class SecondaryListeners implements Listener {
    private final SmartInventory mainGUi;
    private final StateManager stateManager;
    private final PlayerManager playerManager;
    private final PvpGui pvpGui;
    private final Pvp pvp;
    private final ScenariosGui scenariosGui;

    public SecondaryListeners(SmartInventory mainGUi, StateManager stateManager, PlayerManager playerManager, PvpGui pvpGui, Pvp pvp, ScenariosGui scenariosGui) {
        this.mainGUi = mainGUi;
        this.stateManager = stateManager;
        this.playerManager = playerManager;
        this.pvpGui = pvpGui;
        this.pvp = pvp;
        this.scenariosGui = scenariosGui;
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
                if (scenariosGui.isFinalHeal()) {
                    Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Activation du scénario FinalHeal ! ");
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        if (playerManager.containsplayers(player.getUniqueId())) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 99999999));
                        }
                    }
                }
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

        Optional<Player> player = Optional.ofNullable((Player) event.getEntity());
        event.setCancelled(!player.isPresent() || !player.get().hasPotionEffect(PotionEffectType.REGENERATION));


    }
}