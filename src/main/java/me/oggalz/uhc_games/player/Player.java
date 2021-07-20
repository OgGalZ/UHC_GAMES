package me.oggalz.uhc_games.player;



import java.util.UUID;

public class Player {

    private int kill = 0;
    private final UUID uuid;
    private boolean power =  true;
    private boolean spyPowerSage  = true;
    private boolean spyPowerVoyant = true;

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
        return power ;
    }

    public void setEnable(boolean enable) {
        this.power = enable;
    }

    public boolean isSpyCamp() {
        return spyPowerSage;
    }

    public void setSpyCamp(boolean spyCamp) {
        this.spyPowerSage = spyCamp;
    }

    public boolean isSpyPowerVoyant() {
        return spyPowerVoyant;
    }

    public void setSpyPowerVoyant(boolean spyPowerVoyant) {
        this.spyPowerVoyant = spyPowerVoyant;
    }
}
