package me.oggalz.uhc_games.utils;


import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Team<T extends Object> {

    private final Map<UUID, T> teamAzog;
    private final Map<UUID, T> teamThorin;
    private final Map<UUID, T> teamNazgul;
    private final Map<UUID, T> teamTavernier;
    private final Map<UUID, T> smaug;


    public Team() {
        teamAzog = new HashMap<>();
        teamThorin = new HashMap<>();
        teamNazgul = new HashMap<>();
        teamTavernier = new HashMap<>();
        smaug = new HashMap<>();
    }

    public Map<UUID, T> getTeamAzog() {
        return teamAzog;
    }
    public Map<UUID, T> getTeamThorin() {
        return teamThorin;
    }

    public Map<UUID, T> getTeamNazgul() {
        return teamNazgul;
    }

    public Map<UUID, T> getTeamTavernier() {
        return teamTavernier;
    }

    public Map<UUID, T> getSmaug() {
        return smaug;
    }

    public void test() {
        Bukkit.broadcastMessage("" + getTeamAzog());
        Bukkit.broadcastMessage("" + getTeamThorin());
        Bukkit.broadcastMessage("" + getTeamNazgul());
        Bukkit.broadcastMessage("" + getTeamTavernier());
    }
}
