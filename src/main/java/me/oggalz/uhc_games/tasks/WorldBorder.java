package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;


public class WorldBorder extends BukkitRunnable {
    private final Main main;
    private boolean enable = false;
    private final WorldBorderGui worldBorderGui;

    public WorldBorder(Main main,  WorldBorderGui worldBorderGui) {
        this.main = main;
        this.worldBorderGui = worldBorderGui;
    }

    @Override
    public void run() {
        enable = true;
        World world = Bukkit.getWorld("world");
        org.bukkit.WorldBorder worldBorder = world.getWorldBorder();
        worldBorderGui.setBorderSize(worldBorderGui.getBorderSize() - 1);
        worldBorder.setSize(worldBorderGui.getBorderSize());
        if (worldBorder.getSize() <=125) {
            cancel();
        }

    }

}