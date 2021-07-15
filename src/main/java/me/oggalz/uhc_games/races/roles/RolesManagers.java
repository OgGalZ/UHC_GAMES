package me.oggalz.uhc_games.races.roles;

import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.races.roles.heroes.Azog;
import me.oggalz.uhc_games.races.roles.heroes.BilbonSacquet;
import me.oggalz.uhc_games.races.roles.heroes.Legolas;
import me.oggalz.uhc_games.races.roles.heroes.Thorin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class RolesManagers {
    private final PlayerManager playerManager;
    private final Map<UUID, Roles> rolesPlayersWithoutRaces;
    private final List<Roles> rolesListWithoutRaces;
    private List<UUID> playersUuid;

    public RolesManagers(PlayerManager playerManager) {
        this.playerManager = playerManager;
        rolesPlayersWithoutRaces = new HashMap<>();
        rolesListWithoutRaces = new ArrayList<>();
        rolesListWithoutRaces.add(new Azog());
        rolesListWithoutRaces.add(new BilbonSacquet());
        rolesListWithoutRaces.add(new Legolas());
        rolesListWithoutRaces.add(new Thorin());
    }

    public void generateMapPlayersWithoutRaces() {
        playersUuid = playerManager.getKeys();
        Bukkit.broadcastMessage("size" + playersUuid.size());
        int i = 0;
        for (UUID uuid : playersUuid) {
            if (i == rolesListWithoutRaces.size()) {
                break;
            }
            rolesPlayersWithoutRaces.put(uuid, rolesListWithoutRaces.get(i));
            i++;
        }
        Bukkit.broadcastMessage("dd" + rolesPlayersWithoutRaces.values());
    }

    public List<UUID> getPlayersUuid() {
        for (UUID uuid : rolesPlayersWithoutRaces.keySet()) {
            playersUuid.remove(uuid);
        }
        Bukkit.broadcastMessage("sizeafter treatment" + playersUuid.size());
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
                }
            }
        }
    }
}
