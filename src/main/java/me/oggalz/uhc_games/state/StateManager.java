package me.oggalz.uhc_games.state;

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

    public State getState() {
        return gameState;
    }
}
