package me.oggalz.uhc_games.player;


import java.util.*;

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


    public List<UUID> getKeys(){
        ArrayList<UUID> keyList = new ArrayList<>(players.keySet());
            return keyList;
    }
}

