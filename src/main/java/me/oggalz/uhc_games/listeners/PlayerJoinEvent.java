package me.oggalz.uhc_games.listeners;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.State;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.*;
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
    private PlayerManager playerManager;
    private StateManager stateManager;

    public PlayerJoinEvent(Main main , PlayerManager playerManager , StateManager stateManager) {
        this.main = main;
        this.playerManager = playerManager;
        this.stateManager = stateManager;
    }

    @EventHandler
    public void playerJoinEvent(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage("lol");
        FileConfiguration configuration = main.getConfig();
        World world = Bukkit.getWorld("world");
        List<Integer> coordinate = configuration.getIntegerList("coordinate");
        Location location = new Location(world, coordinate.get(0), coordinate.get(1), coordinate.get(2));
        player.teleport(location);

        if(stateManager.hasStarted()){
            ItemStack itemStack = new ItemStack(Material.ACACIA_DOOR );
            player.getInventory().setItem(0 , itemStack);
            player.setGameMode(GameMode.SPECTATOR);

        }else if (stateManager.hasNotStarted()) {
            ItemStack itemStack1 = new ItemStack(Material.IRON_SWORD);
            player.getInventory().setItem(0 , itemStack1);


        }

    }

}
