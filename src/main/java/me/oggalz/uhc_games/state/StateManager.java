package me.oggalz.uhc_games.state;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.tasks.Pvp;
import me.oggalz.uhc_games.tasks.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.World;

public class StateManager {

    private State gameState;
    private final Main main;


    public StateManager( Main main) {
        gameState = State.WAITING;
        this.main = main;
    }

    public void startGame() {
        WorldBorder worldBorder = new WorldBorder(main , this);
        Pvp pvp = new Pvp(main);
        gameState = State.STARTING;
        worldBorder.runBorder();
        pvp.runPvp();
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
