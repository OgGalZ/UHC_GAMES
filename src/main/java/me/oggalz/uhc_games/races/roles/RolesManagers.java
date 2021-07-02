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
}
