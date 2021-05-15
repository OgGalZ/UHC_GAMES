package me.oggalz.uhc_games.listeners;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.State;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.tasks.SpawnLocation;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;

import java.lang.management.ManagementFactory;
import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Map;

public class PlayerJoinEvent implements Listener {
    private final Main main;
    private final PlayerManager playerManager;
    private final StateManager stateManager;

    public PlayerJoinEvent(Main main, PlayerManager playerManager, StateManager stateManager) {
        this.main = main;
        this.playerManager = playerManager;
        this.stateManager = stateManager;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerJoinEvent(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration configuration = main.getConfig();
        World world = Bukkit.getWorld("world");
        List<Integer> coordinate = configuration.getIntegerList("coordinatespawn");
        Location location = new Location(world, coordinate.get(0), coordinate.get(1), coordinate.get(2));

        if (stateManager.hasNotStarted()) {
            event.setJoinMessage(player.getName() + ChatColor.DARK_AQUA + " a rejoint la partie :) ");
            player.teleport(location);
            playerManager.addPlayer(player.getUniqueId());
            player.setFoodLevel(20);
            player.setHealth(20);
            player.getInventory().clear();
            player.setGameMode(GameMode.ADVENTURE);
            if ( playerManager.getPlayers() == 1) {
                stateManager.startGame();
                BukkitTask task = new SpawnLocation(playerManager).runTaskTimer(main , 100L , 20L);
            }

            } else {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.DARK_AQUA + "La partie a déjà commencé :/");
        }

    }

}
