package me.oggalz.uhc_games.utils;


import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.roles.Roles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Team {

    private Map<UUID, Object> teamAzog;
    private Map<UUID, Object> teamThorin;

    private Map<UUID, Roles> teamNazgul;
    private Map<UUID, Roles> teamTavernier;
    private Map<UUID, Roles> smaug;
    private Map<Player, String> tavernier1;
    private Map<Player, String> tavernier2;
    private Map<UUID, Object> teamAzogWithoutNazgulTavernier;
    private Map<UUID, Object> teamThorinWithoutNazgulTavernier;
    private final PlayerManager playerManager;

    public Team(PlayerManager playerManager) {
        this.playerManager = playerManager;
        teamAzog = new HashMap<>();
        teamThorin = new HashMap<>();
        teamNazgul = new HashMap<>();
        teamTavernier = new HashMap<>();
        smaug = new HashMap<>();
        tavernier1 = new HashMap<>();
        tavernier2 = new HashMap<>();
        teamAzogWithoutNazgulTavernier = new HashMap<>();
        teamThorinWithoutNazgulTavernier = new HashMap<>();

    }

   

    public Map<UUID, Object> getTeamAzog() {
        return teamAzog;
    }

    public Map<UUID, Object> getTeamThorin() {
        return teamThorin;
    }

    public Map<UUID, Roles> getTeamNazgul() {
        return teamNazgul;
    }

    public Map<UUID, Roles> getTeamTavernier() {
        return teamTavernier;
    }

    public Map<Player, String> getTavernier1() {
        return tavernier1;
    }

    public Map<Player, String> getTavernier2() {
        return tavernier2;
    }

    public Map<UUID, Object> getTeamAzogWithoutNazgulTavernier() {
        teamAzogWithoutNazgulTavernier = teamAzog;
        return teamAzogWithoutNazgulTavernier;
    }

    public Map<UUID, Object> getTeamThorinWithoutNazgulTavernier() {
        teamThorinWithoutNazgulTavernier = teamThorin;
        return teamThorinWithoutNazgulTavernier;
    }


    public void setTeamTavernier() {
        int i = 0;
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (teamTavernier.containsKey(player.getUniqueId())) {
                if (i < 2) {
                    tavernier1.put(player, player.getName());
                } else {
                    tavernier2.put(player, player.getName());
                }
                i++;
            }
        }
        Bukkit.broadcastMessage("tavernier1" + tavernier1);
        Bukkit.broadcastMessage("tavernier2" + tavernier2);

    }



    public boolean sameTeam(List<Player> players) {
        int teamA = 0;
        for (UUID uuid : players.stream().map(Player::getUniqueId).collect(Collectors.toList())) {
            if (teamAzog.containsKey(uuid)) {
                teamA++:
            } else if (tea) {

            }
        }
    }

    public void deletePlayerLists(Player player) {
        UUID uuid = player.getUniqueId();
        playerManager.removePlayer(uuid);
        if (getTeamAzog().containsKey(uuid)) {
            getTeamAzog().remove(uuid);
        } else if (getTeamThorin().containsKey(uuid)) {
            getTeamThorin().remove(uuid);
        } else if (getTeamNazgul().containsKey(uuid)) {
            getTeamNazgul().remove(uuid);
        } else if (getTeamTavernier().containsKey(uuid)) {
            getTeamTavernier().remove(uuid);
            if (getTavernier1().containsKey(player)) {
                getTavernier1().remove(player);
            } else {
                getTavernier2().remove(player);
            }
        }
    }

}
