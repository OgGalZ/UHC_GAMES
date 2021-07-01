package me.oggalz.uhc_games.races.roles.heroes;

import me.oggalz.uhc_games.races.roles.Roles;
import org.bukkit.ChatColor;

public class BilbonSacquet extends Roles {

    private String rolesMessage;

    @Override
    public String messages() {
        rolesMessage = "Vous êtes Bilbon Sacquet , votre objectif est de gagner avec la compagnie de Thorin. Pour cela vous disposez de l’effet régénération 1 permanent la régénération regen 1hp toutes les 8 secondes. Si vous êtes en possession de l’anneau unique, tous les hobbits gagneront 2 cœurs.  Vous connaissez les trois autres héros de la compagnie de thorin. \n";

        return ChatColor.ITALIC + rolesMessage;
    }
}
