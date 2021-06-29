package me.oggalz.uhc_games.tasks;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Pvp extends BukkitRunnable {

    private int i;
    private boolean enablePvp = false;

    public Pvp(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage("PVP : seconde " + i);
        if (this.i == 0) {
            cancel();
            enablePvp = true;
        }
        i--;
    }

    public boolean isEnablePvp() {
        return enablePvp;
    }
}
