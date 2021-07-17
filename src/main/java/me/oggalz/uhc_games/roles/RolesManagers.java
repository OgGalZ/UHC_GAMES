package me.oggalz.uhc_games.roles;

import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.roles.heroes.Azog;
import me.oggalz.uhc_games.roles.heroes.BilbonSacquet;
import me.oggalz.uhc_games.roles.heroes.Legolas;
import me.oggalz.uhc_games.roles.heroes.Thorin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class RolesManagers {
    private final PlayerManager playerManager;
    private final Map<UUID, Roles> rolesPlayersWithoutRaces;
    private final List<Roles> rolesListWithoutRaces;
    private List<UUID> playersUuid;
    private final  Map<Player, String> pseudos;

    public RolesManagers(PlayerManager playerManager) {
        this.playerManager = playerManager;
        rolesPlayersWithoutRaces = new HashMap<>();
        rolesListWithoutRaces = new ArrayList<>();
        rolesListWithoutRaces.add(new Azog());
        rolesListWithoutRaces.add(new BilbonSacquet());
        rolesListWithoutRaces.add(new Legolas());
        rolesListWithoutRaces.add(new Thorin());
        pseudos = new HashMap<>();
    }

    public void generateMapPlayersWithoutRaces() {
        playersUuid = playerManager.getKeys();
        int i = 0;
        for (UUID uuid : playersUuid) {
            if (i == rolesListWithoutRaces.size()) {
                break;
            }
            rolesPlayersWithoutRaces.put(uuid, rolesListWithoutRaces.get(i));
            i++;
        }
    }

    public List<UUID> getPlayersUuid() {
        for (UUID uuid : rolesPlayersWithoutRaces.keySet()) {
            playersUuid.remove(uuid);
        }
        return playersUuid;
    }

    public void messageAnnouncement() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (rolesPlayersWithoutRaces.containsKey(player.getUniqueId())) {
                player.sendMessage(ChatColor.MAGIC + "deffffffdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddqssjipsdfiojsiohjhjiodohjdhjodg");
            }
        }
    }

    public void powerMessageRoles(Player player) {
        if (rolesPlayersWithoutRaces.containsKey(player.getUniqueId())) {
            Object object = rolesPlayersWithoutRaces.get(player.getUniqueId());
            for (Object o : rolesPlayersWithoutRaces.values()) {
                if (o == object) {
                    rolesPlayersWithoutRaces.get(player.getUniqueId()).powerRoles(player);
                    player.sendMessage(rolesPlayersWithoutRaces.get(player.getUniqueId()).messages());
                    if (!o.equals(rolesListWithoutRaces.get(0))) {
                        pseudos.put(player, player.getName());
                    }
                }
            }
        }
    }

    public void teamMateHeroes() {
        for (Player player : pseudos.keySet()) {
            player.sendMessage("La compagnie de Thorin est composée de " + pseudos.values());
            for(String s : pseudos.values()){
                player.sendMessage("pseudos" + s);
            }
        }
    }

}
