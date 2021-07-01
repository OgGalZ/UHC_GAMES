package me.oggalz.uhc_games.races;

import org.bukkit.ChatColor;

public class Orques extends Races {

    private String message;

    @Override
    public String messages() {
        message = "Félicitations, vous êtes un orque, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de force pendant 2 minutes.\n" +
                "effets grâce au colorant vert foncé :\n" +
                "- Strength 1 (20%) (pendant 2 minute)";
        return ChatColor.RED + message;
    }

    public String getMessage() {
        return message;
    }
}
