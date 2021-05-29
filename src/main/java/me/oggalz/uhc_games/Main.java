package me.oggalz.uhc_games;

import me.oggalz.uhc_games.gui.MainGui;
import me.oggalz.uhc_games.gui.scenarios;
import me.oggalz.uhc_games.listeners.PlayerJoinEvent;
import me.oggalz.uhc_games.listeners.UtilsListeners;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().log(Level.INFO, "Le plugin s'est bien lancé");
        PlayerManager playerManager = new PlayerManager();
        StateManager stateManager = new StateManager();
        ScoreboardCreator scoreboardCreator = new ScoreboardCreator(playerManager);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this, playerManager, stateManager , scoreboardCreator), this);
        getServer().getPluginManager().registerEvents(new UtilsListeners(stateManager, this, playerManager), this);
        getServer().getPluginManager().registerEvents(new MainGui( stateManager) , this);
        getServer().getPluginManager().registerEvents(new scenarios(), this);
        saveDefaultConfig();


    }


    @Override
    public void onDisable() {
        saveDefaultConfig();
    }
}
