package me.oggalz.uhc_games.state;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.tasks.Pvp;
import me.oggalz.uhc_games.tasks.TaskManager;
import me.oggalz.uhc_games.tasks.WorldBorder;

public class StateManager {

    private State gameState;
    private final Main main;
    private final Pvp pvp;
    private final WorldBorder worldBorder;

    public StateManager(Main main,  Pvp pvp, WorldBorder worldBorder) {
        this.pvp = pvp;
        this.worldBorder = worldBorder;
        gameState = State.WAITING;
        this.main = main;


    }

    public void startGame() {
        gameState = State.STARTING;
        worldBorder.runTaskTimer(main, 0, 20);
        pvp.runTaskTimer(main , 0 , 20);
    }

    public boolean hasStarted() {
        return gameState == State.STARTING;
    }

    public boolean hasNotStarted(){
        return gameState == State.WAITING;
    }


    public State getState() {
        return gameState;
    }
}
