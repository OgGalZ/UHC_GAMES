package me.oggalz.uhc_games.utils;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.tasks.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class ScoreboardCreator {

    private final Main main;
    private final PlayerManager playerManager;

    public ScoreboardCreator(Main main, PlayerManager playerManager) {
        this.main = main;
        this.playerManager = playerManager;
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
        board.set(ChatColor.RED + "Kill(s) : ", 11);
        board.set(ChatColor.WHITE + "Episode : ", 10);
        board.set(ChatColor.BLUE + "Joueurs : " + ChatColor.WHITE + playerManager.getPlayers(), 9);
        board.set("", 8);
        board.set(ChatColor.DARK_RED + "{TIMERS} ", 7);
        board.set(ChatColor.BLACK + "Time : ", 6);
        board.set(ChatColor.RED + "PVP : ", 5);
        board.set(ChatColor.DARK_BLUE + "Border", 4);
        board.set(ChatColor.WHITE + "Centre ", 3);

    }

    public void deleteScoreboard(BPlayerBoard board) {
        if (board != null) {
            board.delete();
        }


    }


}
