package me.oggalz.uhc_games;

import me.oggalz.uhc_games.commands.Finish;
import me.oggalz.uhc_games.listeners.PlayerJoinEvent;
import me.oggalz.uhc_games.listeners.SecondaryListeners;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.scenarios.*;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.tasks.Pvp;
import me.oggalz.uhc_games.tasks.Teleportation;
import me.oggalz.uhc_games.tasks.WorldBorder;
import me.oggalz.uhc_games.utils.NmsUtils;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        StateManager stateManager = new StateManager();
        Pvp pvp = new Pvp(this);
        WorldBorder worldBorderClass = new WorldBorder(this, stateManager);
        getLogger().log(Level.INFO, "Le plugin s'est bien lancé");
        if (stateManager.hasStarted()) {
            worldBorderClass.runBorder();
            pvp.runPvp();
        }
        registersEvents();
        registersCommands();
        saveDefaultConfig();
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
        NmsUtils nmsUtils = new NmsUtils();
        VanillaPlus vanillaPlus = new VanillaPlus(this.getConfig());
        getServer().getPluginManager().registerEvents(new Teleportation(this, nmsUtils , stateManager), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this, playerManager, stateManager, scoreboardCreator, nmsUtils), this);
        getServer().getPluginManager().registerEvents(new SecondaryListeners(stateManager), this);
        getServer().getPluginManager().registerEvents(new RegisterUnRegister(this, cutClean, diamondLimite, hastyBoy, timber, vanillaPlus), this);

    }

    public void registersCommands() {
        getCommand("finish").setExecutor(new Finish());

    }

}
