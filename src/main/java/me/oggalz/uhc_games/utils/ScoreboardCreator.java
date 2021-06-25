package me.oggalz.uhc_games.utils;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.tasks.Pvp;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class ScoreboardCreator extends BukkitRunnable {

    private final Main main;
    private final PlayerManager playerManager;
    private int numbersSeconds = 0;
    private int seconds = 0;
    private int numbersMinutes = 0;
    private int minutes = 0;
    private int hours = 0;

    public ScoreboardCreator(Main main, PlayerManager playerManager) {
        this.main = main;
        this.playerManager = playerManager;
    }

    @Override
    public void run() {
        refreshGame();

        numbersSeconds++;
        if (seconds == 6) {
            numbersMinutes++;
            this.numbersSeconds = 0;
            this.seconds = 0;
            if (numbersMinutes == 10) {
                minutes++;
                this.numbersMinutes = 0;
            }
        }
        if (numbersSeconds == 10) {
            this.numbersSeconds = 0;
            seconds++;
        }


    }

    public void createScoreboardLobby(Player player) {
        BPlayerBoard board = Netherboard.instance().createBoard(player, ChatColor.DARK_AQUA + "{Hobbit UHC}");
        board.set("          ", 15);
        board.set(ChatColor.AQUA + "Waiting players :)", 14);
        board.set("", 12);
        board.set(ChatColor.BLUE + "players: " + playerManager.getPlayers() + "/" + playerManager.getPlayers(), 11);

    }

    public void refreshLobby() {
        for (Player x : Bukkit.getOnlinePlayers()) {
            BPlayerBoard board = Netherboard.instance().getBoard(x);
            if (board != null) {
                board.set(ChatColor.BLUE + "players: " + playerManager.getPlayers() + "/" + playerManager.getPlayers(), 11);
            }

        }

    }

    public void createScoreboardGame(Player player) {
        BPlayerBoard board = Netherboard.instance().createBoard(player, ChatColor.DARK_AQUA + "{Hobbit UHC}");
        board.set("     ", 15);
        board.set(ChatColor.DARK_RED + "{INFORMATIONS} ", 14);
        board.set("", 13);
        board.set(ChatColor.DARK_GRAY + "Role : ", 12);
        board.set(ChatColor.RED+ "Kill : ", 11);
        board.set(ChatColor.WHITE + "Episode : ", 10);
        board.set(ChatColor.BLUE + "Joueurs : ", 9);
        board.set("", 8);
        board.set(ChatColor.DARK_RED + "{TIMERS} ", 7);
        board.set(ChatColor.BLACK + "Time : ", 6);
        board.set(ChatColor.RED + "PVP : " , 5);
        board.set(ChatColor.RED + "Border" , 4);
        board.set(ChatColor.WHITE + "Centre ", 3);

    }

    public void deleteScoreboard(BPlayerBoard board) {
        board.delete();
    }

    public void refreshGame() {
        Pvp pvp = new Pvp(main);
        for (Player x : Bukkit.getOnlinePlayers()) {
            BPlayerBoard board = Netherboard.instance().getBoard(x);
            if (board != null) {
                board.set(ChatColor.BLACK + "Time :  " + hours + ":" + minutes + numbersMinutes + ":" + seconds + numbersSeconds, 6);
                board.set(ChatColor.RED + "PVP : "  + pvp.getTimePvpGet() , 5);
            }
        }

    }


    public void runScoreboardGame() {
        ScoreboardCreator scoreboardCreator = new ScoreboardCreator(main, playerManager);
        scoreboardCreator.runTaskTimer(main, 0, 20);
    }
}
