package me.oggalz.uhc_games.races;

import org.bukkit.ChatColor;

public class Elfes extends Races {


    private String message;

    @Override
    public String messages() {
        message = "Félicitations, vous êtes un elfe, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de speed pendant 1 minute 30 secondes.\n" +
                "effets grâce au colorant vert foncé:\n" +
                "Speed 1 ( 1min 30)\n";
        return ChatColor.BLUE + message;
    }


    public String getMessage() {
        return message;
    }
}
