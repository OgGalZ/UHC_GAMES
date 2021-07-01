package me.oggalz.uhc_games.races.roles;

import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.races.roles.heroes.Azog;
import me.oggalz.uhc_games.races.roles.heroes.BilbonSacquet;
import me.oggalz.uhc_games.races.roles.heroes.Legolas;
import me.oggalz.uhc_games.races.roles.heroes.Thorin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.sql.BatchUpdateException;
import java.util.*;
import java.util.stream.Collectors;

public class RolesManagers {
    private final PlayerManager playerManager;
    private Map<UUID, Roles> rolesPlayersWithoutRaces;
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
        playersUuid = playerManager.getKeys();
    }

    public void generateMapHeroes() {
        for (int i = 0; i < rolesListWithoutRaces.size(); i++) {
            for (UUID uuid : playersUuid) {
                {
                    rolesPlayersWithoutRaces.put(uuid, rolesListWithoutRaces.get(i));
                    playersUuid.remove(i);
                }
            }
        }
    }


    public List<UUID> getPlayersUuid() {
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
