package me.oggalz.uhc_games.races;

import me.oggalz.uhc_games.gui.RolesGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.races.roles.RolesManagers;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class RacesManager {

    private final PlayerManager playerManager;
    private final Map<UUID, Races> racesPlayers;
    private final List<Races> racesList;
    private final RolesGui rolesGui;
    private final RolesManagers rolesManagers;


    public RacesManager(PlayerManager playerManager, RolesGui rolesGui, RolesManagers rolesManagers) {
        this.playerManager = playerManager;
        this.rolesGui = rolesGui;
        this.rolesManagers = rolesManagers;
        me.oggalz.uhc_games.player.Player player = new me.oggalz.uhc_games.player.Player(UUID.randomUUID());
        racesPlayers = new HashMap<>();
        racesList = new ArrayList<>();
        racesList.add(new Elfes(player));
        racesList.add(new Hobbits(player));
        racesList.add(new Nains(player));
        racesList.add(new Orques(player));
    }

    public void generateMapRaces() {
        if (rolesGui.resultsRaces() != 0) {
            int i = 0;
            for (UUID uuid : rolesManagers.getPlayersUuid()) {
                if (i > racesList.size()) {
                    i = 0;
                }
                racesPlayers.put(uuid, racesList.get(i));
                i++;
            }
        }
        Bukkit.broadcastMessage("lsdopdfjdfskjsjkgfsdkjk" + racesPlayers.values());
    }


    public void messageAnnouncement() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (racesPlayers.containsKey(player.getUniqueId())) {
                player.sendMessage(racesPlayers.get(player.getUniqueId()).messages());
            }
        }
    }

    public boolean containsUuid(UUID uuid) {
        return racesPlayers.containsKey(uuid);
    }

    public Races getRaces(UUID uuid) {
        return racesPlayers.get(uuid);
    }
}


