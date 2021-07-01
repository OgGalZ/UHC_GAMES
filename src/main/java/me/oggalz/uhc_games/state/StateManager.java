package me.oggalz.uhc_games.state;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.races.RacesManager;
import me.oggalz.uhc_games.tasks.Pvp;
import me.oggalz.uhc_games.tasks.Scheduler;
import me.oggalz.uhc_games.tasks.WorldBorder;

public class StateManager {

    private State gameState;
    private final Main main;
    private final PvpGui pvpGui;
    private final WorldBorderGui worldBorderGui;
    private final PlayerManager playerManager;
    private final WorldBorder worldBorder;
    private final Pvp pvp;
    private final RacesManager racesManager;

    public StateManager(Main main, PvpGui pvpGui, WorldBorderGui worldBorderGui, PlayerManager playerManager, WorldBorder worldBorder, Pvp pvp, RacesManager racesManager) {
        this.main = main;
        this.pvpGui = pvpGui;
        this.worldBorderGui = worldBorderGui;
        this.playerManager = playerManager;
        this.worldBorder = worldBorder;
        this.pvp = pvp;
        this.racesManager = racesManager;
        gameState = State.WAITING;
    }

    public void startGame() {
        gameState = State.STARTING;
        int secondsPvp = pvpGui.getTimePvp() * 60;
        int secondsBorder = worldBorderGui.getTimeBorder() * 60;
        racesManager.generateMap();
        Scheduler scheduler = new Scheduler(pvpGui, worldBorderGui, playerManager);
        scheduler.runTaskTimer(main, 0, 20L);
        pvp.runTaskLater(main, secondsPvp * 20L);
        worldBorder.runTaskTimer(main, secondsBorder * 20L, 20);
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
