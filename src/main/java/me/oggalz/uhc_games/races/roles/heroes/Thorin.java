package me.oggalz.uhc_games.races.roles.heroes;

import me.oggalz.uhc_games.races.roles.Roles;
import org.bukkit.ChatColor;

public class Thorin extends Roles {

    private String rolesMessage;

    @Override
    public String messages() {
        rolesMessage = "Vous êtes Thorin , votre objectif est de gagner avec la compagnie de Thorin.Pour cela vous disposez de l’effet résistance 1 permanent. Si vous êtes en possession de l’anneau unique, tous les nains gagneront 2 cœurs. Vous connaissez les trois autres héros de la compagnie de thorin. \n";
        return ChatColor.ITALIC + rolesMessage;
    }
}
