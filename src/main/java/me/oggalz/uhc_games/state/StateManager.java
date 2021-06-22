package me.oggalz.uhc_games.state;

import me.oggalz.uhc_games.gui.WorldBorderGui;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

public class StateManager {

    private State gameState;

    public StateManager() {
        gameState = State.WAITING;

    }

    public void startGame() {
        gameState = State.STARTING;
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
