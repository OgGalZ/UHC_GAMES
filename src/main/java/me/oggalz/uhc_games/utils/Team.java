package me.oggalz.uhc_games.utils;


import me.oggalz.uhc_games.roles.Roles;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Team {

    private final Map<UUID, Object> teamAzog;
    private final Map<UUID, Object> teamThorin;

    private final Map<UUID, Roles> teamNazgul;
    private final Map<UUID, Roles> teamTavernier;
    private final Map<UUID, Roles> smaug;
    private final Map<Player, String> tavernier1;
    private final Map<Player, String> tavernier2;


    public Team() {
        teamAzog = new HashMap<>();
        teamThorin = new HashMap<>();
        teamNazgul = new HashMap<>();
        teamTavernier = new HashMap<>();
        smaug = new HashMap<>();
        tavernier1 = new HashMap<>();
        tavernier2 = new HashMap<>();

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

    public Map<UUID, Roles> getSmaug() {
        return smaug;
    }

    public Map<Player, String> getTavernier1() {
        return tavernier1;
    }

    public Map<Player, String> getTavernier2() {
        return tavernier2;
    }

    public void setTeamTavernier() {
        if (teamTavernier.size() == 4) {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (teamTavernier.containsKey(player.getUniqueId())) {
                    if (tavernier1.size() == 2 || tavernier1.size() == 1) {
                        tavernier1.put(player, player.getName());
                    } else {
                        tavernier2.put(player, player.getName());
                    }
                }
            }
        }
    }

    public void test() {
        Bukkit.broadcastMessage("" + getTeamAzog());
        Bukkit.broadcastMessage("" + getTeamThorin());
        Bukkit.broadcastMessage("" + getTeamNazgul());
        Bukkit.broadcastMessage("" + getTeamTavernier());
    }
}
