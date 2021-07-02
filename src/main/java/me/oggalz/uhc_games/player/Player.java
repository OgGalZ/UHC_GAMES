package me.oggalz.uhc_games.player;



import java.util.UUID;

public class Player {

    private int kill = 0;
    private final UUID uuid;
    private boolean enable =  true;

    public Player(UUID uuid) {
        this.uuid = uuid;
    }

    public int getKill() {
        return kill;
    }

    public void  addKill(int kill) {
        this.kill += kill;
    }


    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }
}
