package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.roles.RolesManagers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Pvp extends BukkitRunnable {


    private final PlayerManager playerManager;
    private final ScenariosGui scenariosGui;
    private final RolesManagers rolesManagers;
    private static boolean enablePvp = false;


    public Pvp(PlayerManager playerManager, ScenariosGui scenariosGui, RolesManagers rolesManagers) {
        this.playerManager = playerManager;
        this.scenariosGui = scenariosGui;

        this.rolesManagers = rolesManagers;
    }


    @Override
    public void run() {
        enablePvp = true;
        Bukkit.broadcastMessage(ChatColor.GOLD + "Le PVP est maintenant activé ! ");
        for (Player player : Bukkit.getOnlinePlayers()) {
            rolesManagers.powerMessageRolesWithoutRaces(player);
            rolesManagers.powerMessagesRoles(player);
        }
        rolesManagers.teamMateHeroes();
        if (scenariosGui.isFinalHeal()) {
            Bukkit.broadcastMessage(ChatColor.RED + "Activation du scénario Final Heal ! ");
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (playerManager.containsplayers(player.getUniqueId())) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 100, 99999999));
                    player.playSound(player.getLocation(), Sound.GHAST_FIREBALL, 2, 99);
                }
            }
        }
        cancel();

    }

    public static boolean isEnablePvp() {
        return enablePvp;
    }


}

