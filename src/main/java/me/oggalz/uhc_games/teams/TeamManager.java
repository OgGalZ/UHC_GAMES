package me.oggalz.uhc_games.teams;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

public class TeamManager {

    private final Logger logger;
    private final int teamSize;

    private final Map<UUID, Team> teams;

    public TeamManager(Logger logger, int teamSize) {
        this.logger = logger;
        this.teamSize = teamSize;

        teams = new HashMap<>();
    }

    public void createTeam(TeamColor color) {
        if (teams.values().stream().noneMatch(team -> team.getColor().equals(color))) {
            UUID uuid;

            do {
                uuid = UUID.randomUUID();
            } while(teams.containsKey(uuid));

            Team team = new Team(uuid, color, teamSize);
            teams.put(uuid, team);

            logger.info("L'équipe " + color + " a été crée");
        } else {
            throw new IllegalArgumentException("Argument invalide : une équipe est déjà associée à cette couleur");
        }
    }

    public Collection<Team> getTeams() {
        return teams.values();
    }
}
