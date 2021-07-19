package me.oggalz.uhc_games.roles.races_roles;

import me.oggalz.uhc_games.roles.Roles;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;


public class Nazgul extends Roles {
    @Override
    public String messages() {
        String message = "Vous êtes nazgûl votre objectif est de gagner avec Sauron pour cela à chaque kill que vous ferez Sauron gagnera  ½  slots de coeur de plus, si sauron est en possession de l’anneau vous aurez plus 2 slots de coeurs.Néanmoins si sauron meurt vous aurez l’effet Weakness 1 permanent. Quand il est en possession de l’anneau, Sauron a également la possibilité de téléporter  instantanément un nazgûl à lui ce nazgûl  est choisie aléatoirement.\n";
        return ChatColor.BLUE + message;
    }

    @Override
    public void powerRoles(Player player) {
    }

    @Override
    public String toString() {
        return "Nazgul";
    }
}
