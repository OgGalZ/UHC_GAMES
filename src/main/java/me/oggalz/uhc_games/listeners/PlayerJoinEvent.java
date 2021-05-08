package me.oggalz.uhc_games.listeners;


import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.teams.TeamManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.lang.management.ManagementFactory;
import java.sql.BatchUpdateException;
import java.util.List;

public class PlayerJoinEvent implements Listener {
    private final Main main;

    public PlayerJoinEvent(Main main) {
        this.main = main;
    }

    @EventHandler
    public void playerJoinEvent(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        FileConfiguration configuration = main.getConfig();
        World world = Bukkit.getWorld("world");
        List<Integer> coordinate = configuration.getIntegerList("coordinate");
        Location location = new Location(world, coordinate.get(0), coordinate.get(1), coordinate.get(2));
        player.teleport(location);

    }

}
