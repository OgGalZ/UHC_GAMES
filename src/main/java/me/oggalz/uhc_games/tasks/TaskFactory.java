package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.commands.Finish;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.ScoreboardCreator;

public class TaskFactory {

    private final int id;
    private final PlayerManager playerManager;
    private final ScenariosGui scenariosGui;
    private final PvpGui pvpGui;
    private final WorldBorderGui worldBorderGui;
    private final StateManager stateManager;
    private final Finish finish;
    private final ScoreboardCreator scoreboardCreator;

    public TaskFactory(int id, PlayerManager playerManager, ScenariosGui scenariosGui, PvpGui pvpGui, WorldBorderGui worldBorderGui, StateManager stateManager, Finish finish, ScoreboardCreator scoreboardCreator) {
        this.id = id;
        this.playerManager = playerManager;
        this.scenariosGui = scenariosGui;
        this.pvpGui = pvpGui;
        this.worldBorderGui = worldBorderGui;
        this.stateManager = stateManager;
        this.finish = finish;
        this.scoreboardCreator = scoreboardCreator;
    }

    public Pvp createPvpTask() {
        return new Pvp(playerManager, scenariosGui);
    }

    public Scheduler createScheduler() {
        return new Scheduler(pvpGui, worldBorderGui, playerManager);
    }

    public Teleportation teleportation() {
        return new Teleportation(stateManager, finish, scoreboardCreator, worldBorderGui);
    }

    public WorldBorder worldBorder() {
        return new WorldBorder(worldBorderGui);
    }


}