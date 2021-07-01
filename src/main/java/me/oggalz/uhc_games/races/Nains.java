package me.oggalz.uhc_games.races;

import org.bukkit.ChatColor;


public class Nains extends Races {

    private String message;

    @Override
    public String messages() {
        message = "Félicitations, vous êtes un Nain, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de résistance pendant 1 minute.\n" +
                "effets grâce au colorant vert foncé:\n" +
                "Résistance 1 (1 minute)\n";
        return ChatColor.BLACK + message;
    }
    public String getMessage() {
        return message;
    }
}
