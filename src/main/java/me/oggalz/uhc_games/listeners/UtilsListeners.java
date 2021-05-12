package me.oggalz.uhc_games.listeners;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class UtilsListeners implements Listener {

    private  StateManager stateManager;

    public UtilsListeners(StateManager stateManager) {

        this.stateManager = stateManager;
    }


    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        if(stateManager.hasNotStarted()){
            event.setCancelled(true);
        }else{
            event.setCancelled(false);

        }
    }

}
