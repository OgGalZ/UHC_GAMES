package me.oggalz.uhc_games.listeners;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

public class UtilsListeners implements Listener {

    private StateManager stateManager;
    private final Main main;
     private final PlayerManager playerManager ;
    public UtilsListeners(StateManager stateManager, Main main, PlayerManager playerManager) {

        this.stateManager = stateManager;
        this.main = main;
        this.playerManager = playerManager;
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
        Player player = event.getEntity();
        World world = player.getWorld();
        playerManager.removePlayer(player.getUniqueId());
        player.setGameMode(GameMode.ADVENTURE);
        player.sendMessage("gg");
        player.sendMessage("MDR");

    }
}