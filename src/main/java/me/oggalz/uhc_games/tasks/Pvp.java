package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.PvpGui;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Pvp extends BukkitRunnable {
    private final Main main;
    private int i = 0;
    private int test = 0;
    private static boolean enablePvp = false;

    public Pvp(Main main) {
        this.main = main;
    }

    @Override
    public void run() {
        int timePvp = PvpGui.getTimePvp();
        if (timePvp == 0) {
            cancel();
            enablePvp = true;
        }

        if (this.i == 60) {
            this.i = 0;
            this.test -= 1;
            int result = timePvp + this.test;
            if (result == 0) {
                enablePvp = true;
            }
        }
        this.i += 1;
    }

    public void runPvp() {
        Pvp pvp = new Pvp(main);
        pvp.runTaskTimer(main, 200, 20);
    }

    public static boolean isEnablePvp() {
        return enablePvp;
    }
}
