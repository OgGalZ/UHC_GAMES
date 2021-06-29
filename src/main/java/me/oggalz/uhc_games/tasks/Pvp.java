package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Pvp implements Runnable {


    private final PlayerManager playerManager;
    private final ScenariosGui scenariosGui;
    private boolean enablePvp;


    public Pvp(PlayerManager playerManager, ScenariosGui scenariosGui) {
        this.playerManager = playerManager;
        this.scenariosGui = scenariosGui;

    }

    public boolean isEnablePvp() {
        return enablePvp;
    }

    @Override
    public void run() {
        enablePvp = true;
        Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Le pvp est activé !! ");
        if (scenariosGui.isFinalHeal()) {
            Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Activation du scénario FinalHeal ! ");
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (playerManager.containsplayers(player.getUniqueId())) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 99999999));
                }
            }
        }



    }


}

