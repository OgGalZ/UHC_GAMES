package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Pvp extends BukkitRunnable {


    private final PlayerManager playerManager;
    private final ScenariosGui scenariosGui;
    private static boolean enablePvp = false;


    public Pvp(PlayerManager playerManager, ScenariosGui scenariosGui) {
        this.playerManager = playerManager;
        this.scenariosGui = scenariosGui;

    }


    @Override
    public void run() {
        enablePvp = true;
        if (scenariosGui.isFinalHeal()) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (playerManager.containsplayers(player.getUniqueId())) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 99999999));
                    Bukkit.broadcastMessage(ChatColor.RED + "Activation du scénario Final Heal! ! ");
                }
            }
        }
        Bukkit.broadcastMessage(ChatColor.RED + "Le PVP est activé ! ");
        cancel();

    }
    public static boolean isEnablePvp() {
        return enablePvp;
    }


}

