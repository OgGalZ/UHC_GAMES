package me.oggalz.uhc_games.tasks;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Scheduler extends BukkitRunnable{

    private boolean sendTitle = false;
    private int seconds = 0;
    private int minutes = 0;
    private int hours;
    private int episode = 1;
    private final PvpGui pvpGui;
    private final WorldBorderGui worldBorderGui;
    private int timePvp;
    private int timeBorder;
    private final PlayerManager playerManager;
    private int episo = 20 ;

    public Scheduler(PvpGui pvpGui, WorldBorderGui worldBorderGui, PlayerManager playerManager) {
        this.pvpGui = pvpGui;
        this.worldBorderGui = worldBorderGui;
        timePvp = pvpGui.getTimePvp();
        timeBorder = worldBorderGui.getTimeBorder();
        this.playerManager = playerManager;

    }

    @Override
    public void run() {
        seconds++;
        if (seconds == 60) {
            seconds = 0;
            minutes++;
            if (timePvp > 0) {
                timePvp--;
            }
            if (timeBorder > 0) {
                timeBorder--;
            }
            if (minutes == 59) {
                minutes = 0;
                hours++;
            }
            if(episo == 60){
                episo = 0;
            }
            if (minutes == episo && seconds == 0) {
                episode++;
                episo += 20;
            }
        }


        BPlayerBoard playerBoard = null;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (playerManager.containsplayers(player.getUniqueId())) {
                playerBoard = Netherboard.instance().getBoard(player);
                if(!sendTitle){
                    player.sendTitle(ChatColor.GOLD + "Annonce des " , ChatColor.GOLD + "Races ");
                    sendTitle = true;                }
            }
            if (hours == 0) {
                if (playerBoard != null) {
                    playerBoard.set(ChatColor.BLACK + "Time : " + ChatColor.WHITE + minutes + "-" + seconds, 6);
                }

            } else {
                if (playerBoard != null) {
                    playerBoard.set(ChatColor.BLACK + "Time : " + ChatColor.WHITE + hours + "-" + minutes + "-" + seconds, 6);
                }
            }

            if (timeBorder > 0) {
                if (playerBoard != null) {
                    playerBoard.set(ChatColor.DARK_BLUE + "Border :  " + timeBorder + " minute(s)", 4);
                }
            } else {
                if (playerBoard != null) {
                    playerBoard.set(ChatColor.DARK_BLUE + "Border : " + " Réduction ", 4);
                }
            }
            if (timePvp > 0) {
                if (playerBoard != null) {
                    playerBoard.set(ChatColor.RED + "PVP : " + timePvp + " minute(s)", 5);
                }
            } else {
                if (playerBoard != null) {
                    playerBoard.set(ChatColor.RED + "PVP : " + " Activé", 5);
                }
            }
            if(playerBoard != null){
                playerBoard.set(ChatColor.WHITE + "Episode : " + episode, 10);
            }

        }

    }

}