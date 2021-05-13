package me.oggalz.uhc_games.listeners;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

public class UtilsListeners implements Listener {

    private StateManager stateManager;
    private final Main main;

    public UtilsListeners(StateManager stateManager , Main main) {

        this.stateManager = stateManager;
        this.main = main;
    }


    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if (stateManager.hasNotStarted()) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);

        }
    }
    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event) {
        FileConfiguration configuration = main.getConfig();
        Player player = event.getEntity();
        World world = player.getWorld();
        List<Integer> coordinate = configuration.getIntegerList("coordinateplayerdies");
        Location location = new Location(world, coordinate.get(0), coordinate.get(1), coordinate.get(2));
        player.sendMessage("vus etes morts");
        player.setGameMode(GameMode.ADVENTURE);

    }
}