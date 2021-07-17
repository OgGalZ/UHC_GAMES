package me.oggalz.uhc_games.utils;

import me.oggalz.uhc_games.races.RacesManager;
import me.oggalz.uhc_games.roles.Roles;
import me.oggalz.uhc_games.roles.RolesManagers;
import me.oggalz.uhc_games.roles.heroes.Azog;

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
}
