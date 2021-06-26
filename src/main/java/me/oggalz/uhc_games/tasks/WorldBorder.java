package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;


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
        WorldBorderGui.setBorderSize(WorldBorderGui.getBorderSize() - 1);
        worldBorder.setSize(WorldBorderGui.getBorderSize() - 1);
        if (worldBorder.getSize() == 125) {
            cancel();
        }

    }

    public void runBorder() {
        WorldBorder worldBorder = new WorldBorder(main, stateManager);
        int seconds = WorldBorderGui.getTimeBorder() * 60;
        worldBorder.runTaskTimer(main, seconds * 20L, 20);
    }
}