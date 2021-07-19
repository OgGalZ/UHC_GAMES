package me.oggalz.uhc_games.roles;

import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.roles.heroes.Azog;
import me.oggalz.uhc_games.roles.heroes.BilbonSacquet;
import me.oggalz.uhc_games.roles.heroes.Legolas;
import me.oggalz.uhc_games.roles.heroes.Thorin;
import me.oggalz.uhc_games.roles.races.*;
import me.oggalz.uhc_games.utils.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.*;

public class RolesManagers {

    private final Team team;
    private final PlayerManager playerManager;
    private final List<Roles> rolesListWithoutRaces;
    private List<UUID> playersUuid;
    private final Map<Player, String> pseudos;
    private static final List<Roles> rolesListAvailable = new ArrayList<>();
    private final Map<UUID, Roles> rolesPlayersWithoutRaces;
    private final Map<UUID, Roles> rolesPLayers;
    private final Map<String, Roles> instancesRoles;
    private final List<Player> pseudosTargertsThorin;
    private final List<Player> pseudosTargertsAzog;

    public RolesManagers(Team team, PlayerManager playerManager) {
        this.team = team;
        this.playerManager = playerManager;
        rolesPlayersWithoutRaces = new HashMap<>();
        rolesListWithoutRaces = new ArrayList<>();
        rolesListWithoutRaces.add(new Azog());
        rolesListWithoutRaces.add(new BilbonSacquet());
        rolesListWithoutRaces.add(new Legolas());
        rolesListWithoutRaces.add(new Thorin());
        pseudos = new HashMap<>();
        rolesPLayers = new HashMap<>();
        instancesRoles = new HashMap<>();
        instancesRoles.put("Marchand", new Marchand());
        instancesRoles.put("Sage", new Sage());
        instancesRoles.put("Voyant", new Voyant());
        instancesRoles.put("Chasseur", new Chasseur(this, team));
        instancesRoles.put("Garde", new Garde());
        instancesRoles.put("Nazgul", new Nazgul());
        instancesRoles.put("Tavernier", new Tavernier(team));
        pseudosTargertsThorin = new ArrayList<>();
        pseudosTargertsAzog = new ArrayList<>();
    }


    public void generatePseudosTargertsHunter() {
        int i = 0;
        int y = 0;

        if (rolesListAvailable.contains(instancesRoles.get("Chasseur"))) {
            Bukkit.broadcastMessage("Chasseur");
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (team.getTeamAzog().containsKey(player.getUniqueId())) {
                    if (i < 4) {
                        pseudosTargertsAzog.add(player);
                    }
                    i++;
                } else if (team.getTeamThorin().containsKey(player.getUniqueId())) {
                    if (y == 0) {
                        pseudosTargertsThorin.add(player);
                    }
                    y++;
                }
            }
        }
    }

    public void generateMapPlayersWithoutRaces() {
        playersUuid = playerManager.getKeys();
        int i = 0;
        for (UUID uuid : playersUuid) {
            if (i == rolesListWithoutRaces.size()) {
                break;
            }
            if (i == 0) {
                team.getTeamAzog().put(uuid, rolesListWithoutRaces.get(0));
            } else {
                team.getTeamThorin().put(uuid, rolesListWithoutRaces.get(i));
            }
            rolesPlayersWithoutRaces.put(uuid, rolesListWithoutRaces.get(i));
            i++;
        }
    }

    public void generateMapRolesPLayers() {
        int i = 0;
        int y = 0;
        for (UUID uuid : getPlayersUuid()) {
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
    }

    public void generateTeamsNazgulTavernier() {
        for (Map.Entry<UUID, Roles> entry : rolesPLayers.entrySet()) {
            if (entry.getValue() == instancesRoles.get("Nazgul")) {
                team.getTeamNazgul().put(entry.getKey(), entry.getValue());
            } else if (entry.getValue() == instancesRoles.get("Tavernier")) {
                team.getTeamTavernier().put(entry.getKey(), entry.getValue());
            }
        }
        Bukkit.broadcastMessage("teamNazgul" + team.getTeamNazgul());
        Bukkit.broadcastMessage("teamTavernier" + team.getTeamTavernier());


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

    public void powerMessageRolesWithoutRaces(Player player) {
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

    public void powerMessagesRoles(Player player) {
        if (rolesPLayers.containsKey(player.getUniqueId())) {
            player.sendMessage(rolesPLayers.get(player.getUniqueId()).messages());
            rolesPLayers.get(player.getUniqueId()).powerRoles(player);

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

    public List<Player> getPseudosTargertsThorin() {
        return pseudosTargertsThorin;
    }

    public List<Player> getPseudosTargertsAzog() {
        return pseudosTargertsAzog;
    }
}
