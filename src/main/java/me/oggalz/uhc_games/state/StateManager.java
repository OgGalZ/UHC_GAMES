package me.oggalz.uhc_games.state;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.tasks.Pvp;
import me.oggalz.uhc_games.tasks.Scheduler;
import me.oggalz.uhc_games.tasks.TaskFactory;
import me.oggalz.uhc_games.tasks.WorldBorder;
import org.bukkit.Bukkit;

public class StateManager {

    private State gameState;
    private final Main main;
    private final PvpGui pvpGui;
    private final WorldBorderGui worldBorderGui;
    private final TaskFactory factory;

    public StateManager(Main main, PvpGui pvpGui, WorldBorderGui worldBorderGui, TaskFactory factory) {
        this.main = main;
        this.pvpGui = pvpGui;
        this.worldBorderGui = worldBorderGui;
        this.factory = factory;
        gameState = State.WAITING;
    }

    public  void startGame() {
        gameState = State.STARTING;
        int secondsPvp = pvpGui.getTimePvp() * 60;
        int secondsBorder = worldBorderGui.getTimeBorder() * 60;
        Bukkit.getScheduler().runTaskTimerAsynchronously(main, (Runnable) factory.worldBorder(), secondsBorder * 20L, 20);
        Bukkit.getScheduler().runTaskLater(this.main, (Runnable) factory.createPvpTask(), secondsPvp * 20L);
        Bukkit.getScheduler().runTaskTimer(this.main, factory.createScheduler(), 0, 20L);

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
