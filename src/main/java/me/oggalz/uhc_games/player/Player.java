package me.oggalz.uhc_games.player;
import java.util.UUID;

public class Player {

    private final UUID uuid;

    public Player(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUniqueId() {
        return uuid;
    }

}
