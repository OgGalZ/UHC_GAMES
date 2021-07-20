package me.oggalz.uhc_games.roles.races_roles;

import me.oggalz.uhc_games.roles.Roles;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Voyant extends Roles {
    @Override
    public String messages() {
        String mess = ChatColor.BLUE + "Vous êtes voyant votre objectif est de gagner avec la compagnie de Thorin. (si il est de la race orque, il gagne avec les orques) pour cela vous disposez de deux commande /hob camp [joueur] qui est utilisable 1 fois par épisode et permet de savoir si un joueur appartient à sa race ou non mais en contre partie vous perdez ½ coeurs permanent.Vous disposez également de la commande /hob race [pseudo] utilisé 2 fois dans la partie qui permet de savoir la race exact du joueur cible a noter que si le rôle est hors race alors il s'affiche comme appartement a votre race.\n";
        return mess;
    }

    @Override
    public void powerRoles(Player player) {
    }

    @Override
    public String toString() {
        return "Voyant";
    }
}
