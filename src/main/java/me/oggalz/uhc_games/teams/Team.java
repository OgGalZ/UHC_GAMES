package me.oggalz.uhc_games.teams;

import me.oggalz.uhc_games.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Team {
    private final UUID uuid;
    private final TeamColor color;
    private final int teamSize;

    private final Map<UUID, Player> members;

    public Team(UUID uuid, TeamColor color, int teamSize) {
        this.uuid = uuid;
        this.color = color;
        this.teamSize = teamSize;

        members = new HashMap<>();
    }

    public boolean isFull() {
        return members.size() < teamSize;
    }

    public void addPlayer(Player player) {
        members.put(player.getUniqueId(), player);
    }

    public void removePlayer(Player player) {
        members.remove(player.getUniqueId());
    }

    public TeamColor getColor() {
        return color;
    }
}
