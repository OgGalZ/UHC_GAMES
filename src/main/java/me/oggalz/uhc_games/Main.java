package me.oggalz.uhc_games;

import me.oggalz.uhc_games.gui.MainGui;
import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.listeners.PlayerJoinEvent;
import me.oggalz.uhc_games.listeners.SecondaryListeners;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.scenarios.*;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.tasks.WorldBorder;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        StateManager stateManager = new StateManager();

        getLogger().log(Level.INFO, "Le plugin s'est bien lancé");
        registersEvents();
        saveDefaultConfig();
        WorldBorder worldBorder = new WorldBorder(this, stateManager);
        worldBorder.runtask();
    }


    @Override
    public void onDisable() {
        saveDefaultConfig();
    }

    public void registersEvents() {
        PlayerManager playerManager = new PlayerManager();
        StateManager stateManager = new StateManager();
        ScoreboardCreator scoreboardCreator = new ScoreboardCreator(playerManager);
        CutClean cutClean = new CutClean();
        DiamondLimite diamondLimite = new DiamondLimite();
        HastyBoy hastyBoy = new HastyBoy();
        Timber timber = new Timber();
        VanillaPlus vanillaPlus = new VanillaPlus(this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this, playerManager, stateManager, scoreboardCreator), this);
        getServer().getPluginManager().registerEvents(new SecondaryListeners(stateManager), this);
        getServer().getPluginManager().registerEvents(new RegisterUnRegister(this, cutClean, diamondLimite, hastyBoy, timber, vanillaPlus), this);

    }

}
