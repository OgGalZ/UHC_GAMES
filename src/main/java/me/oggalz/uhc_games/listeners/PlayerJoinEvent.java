package me.oggalz.uhc_games.listeners;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.scenarios.RegisterUnRegister;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;

public class PlayerJoinEvent implements Listener {
    private final Main main;
    private final PlayerManager playerManager;
    private final StateManager stateManager;
    private final ScoreboardCreator scoreboardCreator;
    private final RegisterUnRegister unRegister;

    public PlayerJoinEvent(Main main, PlayerManager playerManager, StateManager stateManager, ScoreboardCreator scoreboardCreator, RegisterUnRegister unRegister) {
        this.main = main;
        this.playerManager = playerManager;
        this.stateManager = stateManager;
        this.scoreboardCreator = scoreboardCreator;
        this.unRegister = unRegister;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerJoinEvent(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration configuration = main.getConfig();
        World world = Bukkit.getWorld("world");
        List<Integer> coordinate = configuration.getIntegerList("coordinatespawn");
        Location location = new Location(world, coordinate.get(0), coordinate.get(1), coordinate.get(2), 1, 1);

        if (stateManager.hasNotStarted()) {

            player.removePotionEffect(PotionEffectType.INVISIBILITY);
            event.setJoinMessage(player.getName() + ChatColor.DARK_AQUA + " a rejoint la partie :) ");
            player.teleport(location);
            playerManager.addPlayer(player.getUniqueId());
            scoreboardCreator.createScoreboard(player);
            scoreboardCreator.refresh();
            player.setFoodLevel(20);
            player.setHealth(20);
            player.getInventory().clear();
            player.setGameMode(GameMode.ADVENTURE);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 9));
            if (player.isOp()) {
                player.getInventory().clear();
                ItemStack itemStack = me.oggalz.uhc_games.utils.Item.createItemstack(Material.COMPASS, 1, ChatColor.BLUE + "Config", null);
                player.getInventory().setItem(4, itemStack);
            }
        } else {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.DARK_AQUA + "La partie a déjà commencé :/");
        }

    }

    @EventHandler(priority = EventPriority.HIGH)

    public void playerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        playerManager.removePlayer(player.getUniqueId());
        scoreboardCreator.refresh();
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onTestEntityDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            if (event.getEntity() instanceof Player && stateManager.hasNotStarted()) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        if (itemStack == null) {

        } else if (stateManager.hasNotStarted() && itemStack.hasItemMeta() && itemStack.getType() == Material.COMPASS) {
            event.setCancelled(true);
        }

    }


}

