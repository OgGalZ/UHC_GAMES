package me.oggalz.uhc_games.roles;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.races.RacesManager;
import me.oggalz.uhc_games.roles.heroes.Azog;
import me.oggalz.uhc_games.roles.heroes.BilbonSacquet;
import me.oggalz.uhc_games.roles.heroes.Legolas;
import me.oggalz.uhc_games.roles.heroes.Thorin;
import me.oggalz.uhc_games.utils.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.logging.Level;

public class RolesManagers {

    private final Team team;
    private final PlayerManager playerManager;
    private final List<Roles> rolesListWithoutRaces;
    private List<UUID> playersUuid;
    private final Map<Player, String> pseudos;
    private final List<Roles> rolesListAvailable;
    private final RacesManager racesManager;
    private final Main main;
    private final Map<UUID, Roles> rolesPlayersWithoutRaces;
    private final Map<UUID, Roles> rolesPLayers;
    private final Map<String, Roles> instancesRoles;

    public RolesManagers(Team team, PlayerManager playerManager, RacesManager racesManager, Main main) {
        this.team = team;
        this.playerManager = playerManager;
        this.racesManager = racesManager;
        this.main = main;
        rolesPlayersWithoutRaces = new HashMap<>();
        rolesListWithoutRaces = new ArrayList<>();
        rolesListWithoutRaces.add(new Azog());
        rolesListWithoutRaces.add(new BilbonSacquet());
        rolesListWithoutRaces.add(new Legolas());
        rolesListWithoutRaces.add(new Thorin());
        pseudos = new HashMap<>();
        rolesPLayers = new HashMap<>();
        rolesListAvailable = new ArrayList<>();
        instancesRoles = new HashMap<>();
        instancesRoles.put("Marchand", new Marchand());
        instancesRoles.put("Sage", new Sage());
        instancesRoles.put("Voyant", new Voyant());
        instancesRoles.put("Chasseur", new Chasseur());
        instancesRoles.put("Garde", new Garde());
        instancesRoles.put("Nazgul", new Nazgul());
        instancesRoles.put("Tavernier", new Tavernier());
    }

    public void generateMapPlayersWithoutRaces() {
        playersUuid = playerManager.getKeys();
        int i = 0;
        for (UUID uuid : playersUuid) {
            if (i == rolesListWithoutRaces.size()) {
                break;
            }
            if (rolesPlayersWithoutRaces.get(i) == rolesListWithoutRaces.get(0)) {
                team.getTeamAzog().put(uuid, rolesListWithoutRaces.get(0));
            } else {
                team.getTeamThorin().put(uuid, rolesListWithoutRaces.get(i));
            }
            rolesPlayersWithoutRaces.put(uuid, rolesListWithoutRaces.get(i));
            i++;
        }
    }

    public void generateMapRolesPLayers() throws NullPointerException {
        int i = 0;
        int y = 0;
        try {
            for (UUID uuid : racesManager.getRacesPlayers().keySet()) {
                if (i == rolesListAvailable.size()) {
                    i = 0;
                    if (y == 4) {
                        break;
                    }
                    y++;
                }
                rolesPLayers.put(uuid, rolesListAvailable.get(i));
                i++;
            }
        } catch (NullPointerException e) {
            main.getLogger().log(Level.WARNING, "Aucun joueur n'aura de race dans cette partie.");
        }
        Bukkit.broadcastMessage("" + rolesPLayers);
    }

    public void generateTeamsNazgulTavernier() {
        for (Map.Entry<UUID, Roles> entry : rolesPLayers.entrySet()) {
            if (entry.getValue() == instancesRoles.get("Nazgul")) {
                team.getTeamNazgul().put(entry.getKey(), entry.getValue());
            } else if (entry.getValue() == instancesRoles.get("Tavernier")) {
                team.getTeamTavernier().put(entry.getKey(), entry.getValue());
            }
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
        }
    }


    public List<Roles> getRolesList() {
        return rolesListAvailable;
    }

    public Map<String, Roles> getInstancesRoles() {
        return instancesRoles;
    }
}
