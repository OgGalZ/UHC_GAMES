package me.oggalz.uhc_games.races.roles.heroes;

import me.oggalz.uhc_games.races.roles.Roles;
import org.bukkit.ChatColor;

public class Legolas extends Roles {

    private String rolesMessage;

    @Override
    public String messages() {
        rolesMessage = "Vous êtes Legolas , votre objectif est de gagner avec la compagnie de Thorin. Pour cela vous disposez de l’effet speed 1 permanent. Si vous êtes en possession de l’anneau unique, tous les elfes gagneront 2 cœurs. Vous connaissez les trois autres héros de la compagnie de thorin. \n";
        return ChatColor.ITALIC + rolesMessage;
    }
}
