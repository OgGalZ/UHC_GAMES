package me.oggalz.uhc_games.player;

import me.oggalz.uhc_games.races.Races;
import me.oggalz.uhc_games.races.RacesManager;

import java.util.UUID;

public class Player {

    private int kill = 0;
    private final UUID uuid;

    public Player(UUID uuid) {
        this.uuid = uuid;
    }

    public int getKill() {
        return kill;
    }

    public void  addKill(int kill) {
        this.kill += kill;
    }

}
