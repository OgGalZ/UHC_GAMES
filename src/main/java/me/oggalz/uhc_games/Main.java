package me.oggalz.uhc_games;

import me.oggalz.uhc_games.commands.Finish;
import me.oggalz.uhc_games.listeners.PlayerJoinEvent;
import me.oggalz.uhc_games.listeners.SecondaryListeners;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.scenarios.*;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.tasks.Teleportation;
import me.oggalz.uhc_games.utils.NmsUtils;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Level;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        List<Integer> respawn = this.getConfig().getIntegerList("respawn");
        Bukkit.getWorld("world").setSpawnLocation(respawn.get(0), respawn.get(1), respawn.get(2));
        getLogger().log(Level.INFO, "Le plugin s'est bien lancé");
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
        StateManager stateManager = new StateManager(this);
        ScoreboardCreator scoreboardCreator = new ScoreboardCreator( this, playerManager );
        CutClean cutClean = new CutClean();
        DiamondLimite diamondLimite = new DiamondLimite();
        HastyBoy hastyBoy = new HastyBoy();
        Timber timber = new Timber();
        NmsUtils nmsUtils = new NmsUtils();
        Finish finish = new Finish();
        VanillaPlus vanillaPlus = new VanillaPlus(this.getConfig());
        getServer().getPluginManager().registerEvents(new Teleportation(this, nmsUtils , stateManager , finish , scoreboardCreator), this);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this, playerManager, stateManager, scoreboardCreator, nmsUtils), this);
        getServer().getPluginManager().registerEvents(new SecondaryListeners(stateManager), this);
        getServer().getPluginManager().registerEvents(new RegisterUnRegister(this, cutClean, diamondLimite, hastyBoy, timber, vanillaPlus), this);

    }

    public void registersCommands() {
        getCommand("finish").setExecutor(new Finish());

    }

}
