package me.oggalz.uhc_games.listeners;


import me.oggalz.uhc_games.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.sql.BatchUpdateException;
import java.util.List;

public class PlayerJoinEvent implements Listener {

    private Main main;

    @EventHandler
    public void playerJoinEvent(org.bukkit.event.player.PlayerJoinEvent event){
        Player player = event.getPlayer();
        World world = Bukkit.getWorld("world");
        Location location = new Location(world ,   279.972, 67 ,305.736  );
        player.teleport(location);



    }

}
