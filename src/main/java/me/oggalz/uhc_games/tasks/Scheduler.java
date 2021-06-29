package me.oggalz.uhc_games.tasks;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Scheduler extends BukkitRunnable {

    private int seconds = 0;
    private int numbersSeconds = 0;
    private int minutes = 0;
    private int numbersMinutes = 0;
    private int hours;
    private int episode = 0;
    private BPlayerBoard playerBoard;
    private final PvpGui pvpGui;
    private final WorldBorderGui worldBorderGui;
    private int timePvp;
    private int timeBorder;

    public Scheduler(PvpGui pvpGui, WorldBorderGui worldBorderGui) {
        this.pvpGui = pvpGui;
        this.worldBorderGui = worldBorderGui;
        for (Player player : Bukkit.getOnlinePlayers()) {
            playerBoard = Netherboard.instance().getBoard(player);
        }
        timeBorder = worldBorderGui.getTimeBorder();
        timePvp = pvpGui.getTimePvp();
    }

    @Override
    public void run() {
        playerBoard.set(ChatColor.RED + "PVP : " + timePvp + "min", 5);
        playerBoard.set(ChatColor.DARK_BLUE + "Border" + timeBorder, 4);
        if (timePvp == 0) {
            playerBoard.set(ChatColor.RED + "PVP : " + " Activé", 5);
        }
        if (timeBorder == 0) {
            playerBoard.set(ChatColor.DARK_BLUE + "Border" + " Reduction", 4);
        }
        Bukkit.broadcastMessage(timePvp + "");
        playerBoard.set(ChatColor.BLACK + "Time : " + hours + "-" + minutes + numbersMinutes + "-" + seconds + numbersMinutes, 6);
        numbersSeconds++;
        if (numbersSeconds == 10) {
            seconds++;
            numbersSeconds = 0;
        }
        if (numbersSeconds == 9 && seconds == 5) {
            numbersSeconds = 0;
            seconds = 0;
            numbersMinutes++;
            if (timePvp != 0) {
                timePvp--;
                playerBoard.set(ChatColor.RED + "PVP : " + timePvp + "min", 5);
            }
            if(timeBorder != 0){
                timeBorder--;
                playerBoard.set(ChatColor.DARK_BLUE + "Border" + timeBorder, 4);
            }
            Bukkit.broadcastMessage(timePvp + "");
            Bukkit.broadcastMessage(timeBorder + "");
        }
        if (numbersMinutes == 10) {
            numbersMinutes = 0;
            minutes++;
            if (timePvp != 0) {
                timePvp--;
                playerBoard.set(ChatColor.RED + "PVP : " + timePvp + "min", 5);
            }
            if(timeBorder != 0){
                timeBorder--;
                playerBoard.set(ChatColor.DARK_BLUE + "Border" + timeBorder, 4);
            }
            Bukkit.broadcastMessage(timePvp + "");
            Bukkit.broadcastMessage(timeBorder + "");
        }
        if (minutes == 2 && numbersMinutes == 0) {
            episode++;
            playerBoard.set(ChatColor.WHITE + "Episode : " + episode, 10);
        }
        if (minutes == 6 && numbersMinutes == 0) {
            minutes = 0;
            numbersMinutes = 0;
            hours++;
        }

    }
}
