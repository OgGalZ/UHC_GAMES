package me.oggalz.uhc_games;

import me.oggalz.uhc_games.listeners.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new PlayerJoinEvent() , this);
    }

    @Override
    public void onDisable() {

    }
}
