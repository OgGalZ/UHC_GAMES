package me.oggalz.uhc_games.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Pvp extends BukkitRunnable {

    private boolean enablePvp = false;


    @Override
    public void run() {

        enablePvp = true;
        cancel();
    }

    public boolean isEnablePvp() {
        return enablePvp;
    }
}
