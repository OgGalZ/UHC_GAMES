package me.oggalz.uhc_games.utils;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import me.oggalz.uhc_games.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class ScoreboardCreator {


    private final PlayerManager playerManager;


    public ScoreboardCreator(PlayerManager playerManager) {
        this.playerManager = playerManager;

    }

    public void createScoreboard(Player player) {


        BPlayerBoard board = Netherboard.instance().createBoard(player, ChatColor.DARK_AQUA + "{Hobbit UHC}");
        board.set("          ", 15);
        board.set(ChatColor.AQUA + "Waiting players :)", 14);
        board.set("", 12);
        board.set(ChatColor.BLUE + "players: " + playerManager.getPlayers() + "/20", 11);

    }

    public void refresh() {
        for (Player x : Bukkit.getOnlinePlayers()) {
            BPlayerBoard board = Netherboard.instance().getBoard(x);
            if (board != null) {
                board.set(ChatColor.BLUE + "players: " + playerManager.getPlayers() + "/20", 11);
            }

        }

    }


}
