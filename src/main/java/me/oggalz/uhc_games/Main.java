package me.oggalz.uhc_games;

import me.oggalz.uhc_games.gui.MainGui;
import me.oggalz.uhc_games.listeners.PlayerJoinEvent;
import me.oggalz.uhc_games.listeners.UtilsListeners;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.InventoryClass;
import me.oggalz.uhc_games.utils.Item;
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
        Item itemstack = new Item(null , 0 , null , null);
        InventoryClass inventoryClass = new InventoryClass(0, null);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this, playerManager, stateManager , scoreboardCreator), this);
        getServer().getPluginManager().registerEvents(new UtilsListeners(stateManager, this, playerManager), this);
       getServer().getPluginManager().registerEvents(new MainGui( stateManager) , this);

    }


    @Override
    public void onDisable() {
        saveDefaultConfig();
    }
}
