package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.PvpGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Pvp extends BukkitRunnable {
    private final Main main;
    private int i = 0;
    private static boolean enablePvp = false;
    public Pvp(Main main) {
        this.main = main;
    }


    @Override
    public void run() {
        if (PvpGui.getTimePvp() == 0) {
            cancel();
            enablePvp = true;
            for(Player player : Bukkit.getOnlinePlayers()){
                player.playSound(player.getLocation() , Sound.FIREWORK_LARGE_BLAST,  99 , 12);
            }
            Bukkit.broadcastMessage(ChatColor.DARK_RED + "Le PVP est maintenant activé !!");
        }

        if (this.i == 60) {
            this.i = 0;
                PvpGui.setTimePvp(PvpGui.getTimePvp() -1);
            if (PvpGui.getTimePvp() == 0) {
                enablePvp = true;
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "Le PVP est maintenant activé !!");
                cancel();
            }
        }
        i++;
    }

    public void runPvp() {
        Pvp pvp = new Pvp(main);
        pvp.runTaskTimer(main, 200, 20);
    }

    public static boolean isEnablePvp() {
        return enablePvp;
    }

}
