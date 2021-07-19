package me.oggalz.uhc_games.roles.races_roles;

import me.oggalz.uhc_games.roles.Roles;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Sage extends Roles {
    @Override
    public String messages() {
        String message = "Vous êtes le sage , votre objectif est de gagner avec la compagnie de Thorin. (si il est de la race orque, il gagne avec les orques) pour cela vous disposez de d’une commande  /hob souvenir [pseudo du joueur] qui permet de connaître le rôle du joueur cible mais pas sa race. Néanmoins vous perdrez 1.5 coeurs peux importante le camp adverse mais s'il le joueur fait partie du camp adverse alors il gagnera 1 coeurs permanent.\n";
    return ChatColor.BLUE + message;
    }

    @Override
    public void powerRoles(Player player) {

    }

    @Override
    public String toString() {
        return "Sage";
    }
}
