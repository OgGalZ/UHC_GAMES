package me.oggalz.uhc_games.listeners;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.State;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.management.ManagementFactory;
import java.sql.BatchUpdateException;
import java.util.List;
import java.util.Map;

public class PlayerJoinEvent implements Listener {
    private final Main main;
    private PlayerManager playerManager;
    private StateManager stateManager;

    public PlayerJoinEvent(Main main, PlayerManager playerManager, StateManager stateManager) {
        this.main = main;
        this.playerManager = playerManager;
        this.stateManager = stateManager;
    }

    @EventHandler
    public void playerJoinEvent(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration configuration = main.getConfig();
        World world = Bukkit.getWorld("world");
        List<Integer> coordinate = configuration.getIntegerList("coordinate");
        Location location = new Location(world, coordinate.get(0), coordinate.get(1), coordinate.get(2));

        if (stateManager.hasStarted()) {
            player.setGameMode(GameMode.SPECTATOR);
        } else if (stateManager.hasNotStarted()) {
            event.setJoinMessage(player.getName() +ChatColor.DARK_AQUA +" a rejoint la partie :) ");
            player.teleport(location);
            playerManager.addPlayer(player.getUniqueId());
            player.setFoodLevel(20);
            player.getInventory().clear();
            player.setGameMode(GameMode.ADVENTURE);

        }

    }




}
