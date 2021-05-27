package me.oggalz.uhc_games.listeners;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

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
        ScoreboardCreator.createScoreboard(player);
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
            if (player.isOp()) {
                player.getInventory().clear();
              ItemStack itemStack = me.oggalz.uhc_games.utils.Item.createItemstack(Material.COMPASS , 1 , ChatColor.BLUE + "Config" , null);
                player.getInventory().setItem(4 , itemStack);
            }
        } else {
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.DARK_AQUA + "La partie a déjà commencé :/");
        }

    }

}
