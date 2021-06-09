package me.oggalz.uhc_games.tasks;

import javafx.concurrent.Task;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.TimerTask;

public class WorldBorder extends BukkitRunnable {
    private final Main main;
    private final StateManager stateManager;

    public WorldBorder(Main main, StateManager stateManager) {
        this.main = main;
        this.stateManager = stateManager;
    }

    @Override
    public void run() {

        World world = Bukkit.getWorld("world");
        org.bukkit.WorldBorder worldBorder = world.getWorldBorder();
        worldBorder.setCenter(0, 0);
        if (WorldBorderGui.getBorderSize() == 0) {
            worldBorder.setSize(1000);
        } else if (stateManager.hasStarted()) {
            worldBorder.setSize(WorldBorderGui.getBorderSize());

        }

    }

    public void runtask() {
        WorldBorder worldBorder = new WorldBorder(main, stateManager);

        worldBorder.runTaskTimer(main, 20, 20);
    }
}