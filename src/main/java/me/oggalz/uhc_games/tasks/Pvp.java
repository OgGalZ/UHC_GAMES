package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.ScenariosGui;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
        }

        if (this.i == 59) {
            this.i = 0;
            PvpGui.setTimePvp(PvpGui.getTimePvp() - 1);
            if (PvpGui.getTimePvp() == 0) {
                enablePvp = true;
                cancel();
            }
        }
        if (PvpGui.getTimePvp() == 0) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.FIREWORK_LARGE_BLAST, 99, 12);
                if (ScenariosGui.isFinalHeal()) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 555));
                }
            }
        }
        i++;
    }

    public void runPvp() {
        Pvp pvp = new Pvp(main);
        pvp.runTaskTimer(main, 0, 20);
    }

    public static boolean isEnablePvp() {
        return enablePvp;
    }

}
