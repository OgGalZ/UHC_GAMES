package me.oggalz.uhc_games;

import me.oggalz.uhc_games.listeners.PlayerJoinEvent;
import me.oggalz.uhc_games.listeners.UtilsListeners;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        PlayerManager playerManager = new PlayerManager();
        StateManager stateManager = new StateManager();
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this , playerManager , stateManager) , this);
        getServer().getPluginManager().registerEvents(new UtilsListeners(stateManager , this, playerManager) , this);
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
    }
}
