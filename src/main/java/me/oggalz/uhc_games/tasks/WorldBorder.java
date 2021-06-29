package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;


public class WorldBorder extends BukkitRunnable{
    private boolean mess = false;

    private final WorldBorderGui worldBorderGui;

    public WorldBorder(  WorldBorderGui worldBorderGui) {
        this.worldBorderGui = worldBorderGui;
    }

    @Override
    public void run() {
        if(!mess){
            Bukkit.broadcastMessage(ChatColor.RED  + "La bordure commence à ce rétrécir !  ");
        }
        mess = true;
        World world = Bukkit.getWorld("world");
        org.bukkit.WorldBorder worldBorder = world.getWorldBorder();
        worldBorderGui.setBorderSize(worldBorderGui.getBorderSize() - 1);
        worldBorder.setSize(worldBorderGui.getBorderSize());
        if (worldBorder.getSize() <=125) {
        cancel();
        }

    }

}