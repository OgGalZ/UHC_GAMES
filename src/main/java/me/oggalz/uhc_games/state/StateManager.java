package me.oggalz.uhc_games.state;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.tasks.Pvp;
import me.oggalz.uhc_games.tasks.WorldBorder;

public class StateManager {

    private State gameState;
    private final Main main;
    private final Pvp pvp;
    private final WorldBorder worldBorder;
    private final PvpGui pvpGui;
    private final WorldBorderGui worldBorderGui;

    public StateManager(Main main, Pvp pvp, WorldBorder worldBorder, PvpGui pvpGui, WorldBorderGui worldBorderGui) {
        this.main = main;
        this.pvp = pvp;
        this.worldBorder = worldBorder;
        this.pvpGui = pvpGui;
        this.worldBorderGui = worldBorderGui;
        gameState = State.WAITING;
    }

    public void startGame() {
        gameState = State.STARTING;
        int secondsPvp = pvpGui.getTimePvp() * 60;
        int secondsBorder = worldBorderGui.getTimeBorder() * 60 ;
        worldBorder.runTaskTimer(main , secondsBorder * 20L, 20);
        pvp.runTaskLater(main, secondsPvp * 20L);
    }

    public boolean hasStarted() {
        return gameState == State.STARTING;
    }

    public boolean hasNotStarted() {
        return gameState == State.WAITING;
    }


    public State getState() {
        return gameState;
    }
}
