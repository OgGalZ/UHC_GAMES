package me.oggalz.uhc_games;

import me.oggalz.uhc_games.gui.MainGui;
import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.listeners.PlayerJoinEvent;
import me.oggalz.uhc_games.listeners.SecondaryListeners;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.scenarios.*;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Level;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {

        getLogger().log(Level.INFO, "Le plugin s'est bien lancé");
        PlayerManager playerManager = new PlayerManager();
        StateManager stateManager = new StateManager();
        RegisterUnRegister registerUnRegister = new RegisterUnRegister(this);
        ScoreboardCreator scoreboardCreator = new ScoreboardCreator(playerManager);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this, playerManager, stateManager, scoreboardCreator , registerUnRegister), this);
        getServer().getPluginManager().registerEvents(new SecondaryListeners(stateManager), this);
        getServer().getPluginManager().registerEvents(new RegisterUnRegister(this) , this);
        saveDefaultConfig();
    }


    @Override
    public void onDisable() {
        saveDefaultConfig();
    }



}
