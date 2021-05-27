package me.oggalz.uhc_games.utils;

import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ScoreboardCreator {


    public static void createScoreboard(Player player) {
        BPlayerBoard board = Netherboard.instance().createBoard(player, "Mon Serveur");

        int money = 12;
        board.set("Argent : " + money, 3);
        board.set("" , 2);
        board.set(ChatColor.RED + "mc.monserveur.com", 1);

    }
}
