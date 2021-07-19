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


    public boolean isSameTeam(List<Player> players) {
        int teamA = 0;
        int teamT = 0;
        int teamN = 0;
        int teamT1 = 0;
        int teamT2 = 0;
        boolean check = false;
        int i = 0;
        for (Player p : players) {
            if (teamAzogWithoutNazgulTavernier.containsKey(p.getUniqueId())) {
                teamA++;
            } else if (teamThorinWithoutNazgulTavernier.containsKey(p.getUniqueId())) {
                teamT++;
            } else if (teamNazgul.containsKey(p.getUniqueId())) {
                teamN++;
            } else if (tavernier1.containsKey(players)) {
                teamT1++;
            } else if (tavernier2.containsKey(players)) {
                teamT2++;
            }
        }
        if (teamA != 0) {
            i++;
        } else if (teamT != 0) {
            i++;
        } else if (teamN != 0) {
            i++;
        } else if (teamT1 != 0) {
            i++;
        } else if (teamT2 != 0) {
            i++;
        }
        if (i == 1) {
            check = true;
        }
        return check;
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
