package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.gui.WorldBorderGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class WorldBorder extends BukkitRunnable {

    private final WorldBorderGui worldBorderGui;
    private boolean messBorder = true;

    public WorldBorder(WorldBorderGui worldBorderGui) {
        this.worldBorderGui = worldBorderGui;

    }

    @Override
    public void run() {
        if (messBorder) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "La bordure commence à se rétrécir ! ");
            messBorder = false;
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.playSound(player.getLocation(), Sound.LAVA, 99, 99);
        }
        World world = Bukkit.getWorld("world");
        org.bukkit.WorldBorder worldBorder = world.getWorldBorder();
        worldBorderGui.setBorderSize(worldBorderGui.getBorderSize() - 1);
        worldBorder.setSize(worldBorderGui.getBorderSize());
        if (worldBorder.getSize() <= 125) {
            cancel();
        }

    }

}