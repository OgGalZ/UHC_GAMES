package me.oggalz.uhc_games;

import me.oggalz.uhc_games.gui.MainGui;
import me.oggalz.uhc_games.listeners.PlayerJoinEvent;
import me.oggalz.uhc_games.listeners.UtilsListeners;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.InventoryClass;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        PlayerManager playerManager = new PlayerManager();
        StateManager stateManager = new StateManager();
        Item itemstack = new Item(null , 0);
        InventoryClass inventoryClass = new InventoryClass(0, null);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this, playerManager, stateManager, itemstack), this);
        getServer().getPluginManager().registerEvents(new UtilsListeners(stateManager, this, playerManager), this);
        getServer().getPluginManager().registerEvents(new MainGui(inventoryClass , itemstack , stateManager) , this);

    }


    @Override
    public void onDisable() {
        saveDefaultConfig();
    }
}
