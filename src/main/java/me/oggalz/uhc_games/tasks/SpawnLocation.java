package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;


public class SpawnLocation extends BukkitRunnable {

    private int time = 0;
    private final PlayerManager playerManager;

    public SpawnLocation(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @Override
    public void run() {
        Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Lancement de la partie dans : " + time);
        time++;
        if (time == 11) {
            Bukkit.getScheduler().cancelTask(getTaskId());
        }

        for(Player player : Bukkit.getOnlinePlayers()){



        }
    }


}




