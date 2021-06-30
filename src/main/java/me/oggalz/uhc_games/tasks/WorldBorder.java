package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.gui.WorldBorderGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;


public class WorldBorder extends BukkitRunnable{

    private final WorldBorderGui worldBorderGui;

    public WorldBorder(  WorldBorderGui worldBorderGui) {
        this.worldBorderGui = worldBorderGui;
    }

    @Override
    public void run() {

        World world = Bukkit.getWorld("world");
        org.bukkit.WorldBorder worldBorder = world.getWorldBorder();
        worldBorderGui.setBorderSize(worldBorderGui.getBorderSize() - 1);
        worldBorder.setSize(worldBorderGui.getBorderSize());
        if (worldBorder.getSize() <=125) {
            cancel();
        }

    }

}