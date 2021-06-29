package me.oggalz.uhc_games.player;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.HashMap;
import java.util.List;
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

    public void removePlayer(UUID uuid) {
        players.remove(uuid);
    }

    public boolean containsplayers(UUID uuid){
        return players.containsKey(uuid);
    }

}

