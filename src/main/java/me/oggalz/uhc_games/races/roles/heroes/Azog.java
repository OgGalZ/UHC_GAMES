package me.oggalz.uhc_games.races.roles.heroes;

import me.oggalz.uhc_games.races.roles.Roles;
import org.bukkit.ChatColor;

public class Azog extends Roles {

    private String rolesMessage;

    @Override
    public String messages() {
        rolesMessage = "Vous êtes Azog , votre objectif est de gagner avec les orques. Pour cela vous disposez de l’effet force 1 permanent. Si vous êtes en possession de l’anneau unique, tous les orques gagneront 2 cœurs. Vous êtes connue de tous les orques, mais vous ne connaissez personne.\n";
        return ChatColor.ITALIC + rolesMessage;
    }
}
