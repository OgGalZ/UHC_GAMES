package me.oggalz.uhc_games.player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerManager {

    private final Map<UUID, Player> players;

    public PlayerManager() {
        players = new HashMap<>();
    }

    public int getPlayers() {
        return players.size();
    }

    public void addPlayer(UUID uuid) {
        Player Player = new Player(uuid);
        players.put(uuid, Player);
    }

    public Player getPlayer(UUID uuid) {
        return players.get(uuid);
    }

}
