package me.oggalz.uhc_games.races;

import org.bukkit.ChatColor;

public class Hobbits extends Races {

    private String message;
    @Override
    public String messages() {
        message = "Félicitations, vous êtes un Hobbit, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de régénération pendant 30 secondes.\n" +
                "effets grâce au colorant vert foncé:\n" +
                " régénération 1 (1 hp par 3s )(30s)\n";

        return ChatColor.GOLD + message;
    }

    public String getMessage() {
        return message;
    }
}
