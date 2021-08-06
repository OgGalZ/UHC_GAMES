package me.oggalz.uhc_games.races;


import me.oggalz.uhc_games.roles.RolesManagers;
import me.oggalz.uhc_games.utils.Team;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class RacesManager {

    private final Team team;
    private final Map<UUID, Races> racesPlayers;
    private final List<Races> racesList;
    private final RolesManagers rolesManagers;
    private final List<String> orques;
    private final List<String> elfes;
    private final List<String> nains;
    private final List<String> hobbits;
    private final List<String> pseudosTeamAzog;
    private final List<String> pseudosTeamThorin;

    public RacesManager(Team team, RolesManagers rolesManagers) {
        this.team = team;
        this.rolesManagers = rolesManagers;
        me.oggalz.uhc_games.player.Player player = new me.oggalz.uhc_games.player.Player(UUID.randomUUID());
        racesList = new ArrayList<>();
        racesList.add(new Elfes(player, this));
        racesList.add(new Hobbits(player, this));
        racesList.add(new Nains(player, this));
        racesList.add(new Orques(player, this, rolesManagers));
        orques = new ArrayList<>();
        elfes = new ArrayList<>();
        nains = new ArrayList<>();
        hobbits = new ArrayList<>();
        racesPlayers = new HashMap<>();
        pseudosTeamAzog = new ArrayList<>();
        pseudosTeamThorin = new ArrayList<>();
    }

    public void generateMapRaces() {
        int i = 0;
        for (UUID uuid : rolesManagers.getPlayersUuid()) {
            if (i + 1  > racesList.size()) {
                i = 0;
            }
            if (i + 1 == racesList.size()) {
                team.getTeamAzog().put(uuid, racesList.get(3));
            } else {
                team.getTeamThorin().put(uuid, racesList.get(i));
            }
            racesPlayers.put(uuid, racesList.get(i));
            i++;
        }
    }


    public void messageAnnouncement() {

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (racesPlayers.containsKey(player.getUniqueId())) {
                racesPlayers.get(player.getUniqueId()).messages(player);
            }
        }
    }

    public void messageTeamMate() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (racesPlayers.containsKey(player.getUniqueId())) {
                if (racesPlayers.get(player.getUniqueId()) == racesList.get(0)) {
                    elfes.add(player.getDisplayName());
                }
                if (racesPlayers.get(player.getUniqueId()) == racesList.get(1)) {
                    hobbits.add(player.getDisplayName());
                }
                if (racesPlayers.get(player.getUniqueId()) == racesList.get(2)) {
                    nains.add(player.getDisplayName());
                }
                if (racesPlayers.get(player.getUniqueId()) == racesList.get(3)) {
                    orques.add(player.getDisplayName());
                }
            }

        }

    }

    public List<String> getPseudosTeamAzog() {
        pseudosTeamAzog.addAll(orques);
        return pseudosTeamAzog;
    }

    public List<String> getPseudosTeamThorin() {
        pseudosTeamThorin.addAll(elfes);
        pseudosTeamThorin.addAll(nains);
        pseudosTeamThorin.addAll(hobbits);
        return pseudosTeamThorin;
    }

    public boolean containsUuid(UUID uuid) {
        return racesPlayers.containsKey(uuid);
    }

    public Races getRaces(UUID uuid) {
        return racesPlayers.get(uuid);
    }

    public List<String> getOrques() {
        return orques;
    }

    public List<String> getElfes() {
        return elfes;
    }

    public List<String> getNains() {
        return nains;
    }

    public List<String> getHobbits() {
        return hobbits;
    }


    public List<Races> getRacesList() {
        return racesList;
    }
}


