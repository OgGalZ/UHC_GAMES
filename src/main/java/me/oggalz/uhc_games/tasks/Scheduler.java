package me.oggalz.uhc_games.tasks;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.player.Player;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.races.RacesManager;
import me.oggalz.uhc_games.roles.RolesManagers;
import me.oggalz.uhc_games.utils.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Scheduler extends BukkitRunnable {

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
    private int episo = 20;
    private final Player player;
    private final RacesManager racesManager;
    private final RolesManagers rolesManagers;
    private boolean racesMessage = true;
    private final Team team;

    public Scheduler(PvpGui pvpGui, WorldBorderGui worldBorderGui, PlayerManager playerManager, Player player, RacesManager racesManager, RolesManagers rolesManagers, Team team) {
        this.pvpGui = pvpGui;
        this.worldBorderGui = worldBorderGui;
        timePvp = pvpGui.getTimePvp();
        timeBorder = worldBorderGui.getTimeBorder();
        this.playerManager = playerManager;
        this.player = player;
        this.racesManager = racesManager;
        this.rolesManagers = rolesManagers;
        this.team = team;
    }

    @Override
    public void run() {

        seconds++;
        if (seconds == 60) {
            if (racesMessage) {
                rolesManagers.messageAnnouncement();
                racesManager.messageAnnouncement();
                racesMessage = false;
            }

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
            if (episo == 60) {
                episo = 0;
            }
            if (minutes == episo && seconds == 0) {
                episode++;
                episo += 20;
                for(Player player :  playerManager.getValues()){
                    player.setEnable(true);
                    player.setSpyCamp(true);
                    player.setSpyPowerVoyant(true);

                }
            }
        }
        if(seconds == 50 && minutes == episo -1){
            Bukkit.broadcastMessage(ChatColor.GOLD + "Faite de la place dans votre inventaire, l'annonce des rôles aura lieu dans 10 secondes ");
        }


        BPlayerBoard playerBoard = null;
        for (org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) {
            if (playerManager.containsplayers(player.getUniqueId())) {
                playerBoard = Netherboard.instance().getBoard(player);
                if (!sendTitle) {
                    sendTitle = true;
                }
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
            if (playerBoard != null) {
                playerBoard.set(ChatColor.WHITE + "Episode : " + episode, 10);
            }

        }


    }


}