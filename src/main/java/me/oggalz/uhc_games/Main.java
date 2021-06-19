package me.oggalz.uhc_games;

import me.oggalz.uhc_games.commands.Finish;
import me.oggalz.uhc_games.gui.MainGui;
import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
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
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        StateManager stateManager = new StateManager();
        WorldBorder worldBorderClass = new WorldBorder(this, stateManager);
        getLogger().log(Level.INFO, "Le plugin s'est bien lancé");
        registersEvents();
        registersCommands();
        Pvp pvp = new Pvp(this);
        if (stateManager.hasStarted()) {
            worldBorderClass.runBorder();
            pvp.runPvp();
        }
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
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this, playerManager, stateManager, scoreboardCreator , nmsUtils), this);
        getServer().getPluginManager().registerEvents(new SecondaryListeners(stateManager), this);
        getServer().getPluginManager().registerEvents(new RegisterUnRegister(this, cutClean, diamondLimite, hastyBoy, timber, vanillaPlus), this);

    }

    public void registersCommands() {
        getCommand("finish").setExecutor(new Finish());

    }

}
