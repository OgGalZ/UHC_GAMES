package me.oggalz.uhc_games.utils;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class ScoreboardCreator extends BukkitRunnable {

    private  final Main main;
    private final PlayerManager playerManager;
    private int seconds = 0;
    private int minutes = 0;
    private int hours = 0;

    public ScoreboardCreator(Main main, PlayerManager playerManager) {
        this.main = main;
        this.playerManager = playerManager;
    }

    @Override
    public void run() {
        refreshGame();
        seconds++;
        if (this.seconds == 60) {
            seconds = 0;
            minutes++;
            if (minutes == 60) {
                hours++;
            }
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
        board.set("", 15);
        board.set(ChatColor.DARK_RED + "Temps de jeu : " + hours + minutes + seconds, 14);

    }

    public void deleteScoreboard(BPlayerBoard board){
        board.delete();
    }
    public void refreshGame() {
        for (Player x : Bukkit.getOnlinePlayers()) {
            BPlayerBoard board = Netherboard.instance().getBoard(x);
            if(board != null){
                board.set(ChatColor.DARK_RED + "Temps de jeu : " + hours + minutes + seconds, 14);
            }

        }

    }
    public void runScoreboardGame(){
        ScoreboardCreator scoreboardCreator = new ScoreboardCreator(main , playerManager);
        scoreboardCreator.runTaskTimer(main , 0 , 20);
    }
}
