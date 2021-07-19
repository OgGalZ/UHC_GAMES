package me.oggalz.uhc_games.roles.races_roles;

import me.oggalz.uhc_games.roles.Roles;
import me.oggalz.uhc_games.roles.RolesManagers;
import me.oggalz.uhc_games.utils.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Chasseur extends Roles {

    private final RolesManagers rolesManagers;
    private final Team team;
    private int i = 1;
    private final Map<Player, Player> huntersTagers;

    public Chasseur(RolesManagers rolesManagers, Team team) {
        this.rolesManagers = rolesManagers;
        this.team = team;
        huntersTagers = new HashMap<>();
    }

    @Override
    public String messages() {
        String message = "Vous êtes chausseur votre objectif est de gagner avec la compagnie de Thorin. (si il est de la race orque, il gagne avec les orques) pour cela vous connaissez le pseudo d’un joueur d’une race adverse si vous arrivez à le tuer vous connaîtrez le pseudo d’un allié que vous ne connaissez pas déjà à noter que vous ne pouvez pas connaître un héro. La personne que vous avez pour cible reçoit 2 cœurs en plus. \n";
        return ChatColor.BLUE + message;
    }

    @Override
    public void powerRoles(Player player) {
        if (team.getTeamThorin().containsKey(player.getUniqueId())) {
            player.sendMessage("Votre cible est " + rolesManagers.getPseudosTargertsAzog().get(i).getName());
            rolesManagers.getPseudosTargertsAzog().get(i).setMaxHealth(24.0);
            huntersTagers.put(player, rolesManagers.getPseudosTargertsAzog().get(i));
            i++;
        } else {
            player.sendMessage("Votre cible est " + rolesManagers.getPseudosTargertsThorin().get(0).getName());
            huntersTagers.put(player, rolesManagers.getPseudosTargertsThorin().get(0));
        }
    }

    public Map<Player, Player> getHuntersTagers() {
        return huntersTagers;
    }
}
