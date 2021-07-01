package me.oggalz.uhc_games.races;

import me.oggalz.uhc_games.gui.RolesGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.races.roles.RolesManagers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class RacesManager {

    private final PlayerManager playerManager;
    private Map<UUID, Races> racesPlayers;
    private List<Races> racesList;
    private final RolesGui rolesGui;
    private final RolesManagers rolesManagers;


    public RacesManager(PlayerManager playerManager, RolesGui rolesGui, RolesManagers rolesManagers) {
        this.playerManager = playerManager;
        this.rolesGui = rolesGui;
        this.rolesManagers = rolesManagers;
        racesPlayers = new HashMap<>();
        racesList = new ArrayList<>();
        racesList.add(new Elfes());
        racesList.add(new Hobbits());
        racesList.add(new Nains());
        racesList.add(new Orques());
    }

    public void generateMapRaces() {
        for (int i = 0; i < rolesGui.resultsRaces() * 4; i++) {
            for (UUID uuid : rolesManagers.getPlayersUuid()) {
                if (i > racesList.size()) {
                    i = 0;
                }
                racesPlayers.put(uuid, racesList.get(i));
            }

        }
    }


    public void messageAnnouncement() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (racesPlayers.containsKey(player.getUniqueId())) {
                player.sendMessage(racesPlayers.get(player.getUniqueId()).messages());
            }
        }
    }
}


