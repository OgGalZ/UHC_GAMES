package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.commands.Finish;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.ScoreboardCreator;

/*public class TaskFactory {

    private final PlayerManager playerManager;
    private final ScenariosGui scenariosGui;
    private final PvpGui pvpGui;
    private final WorldBorderGui worldBorderGui;
    private final Finish finish;
    private final ScoreboardCreator scoreboardCreator;
    private final Main main;
    public TaskFactory( PlayerManager playerManager, ScenariosGui scenariosGui, PvpGui pvpGui, WorldBorderGui worldBorderGui, Finish finish, ScoreboardCreator scoreboardCreator, Main main) {
        this.playerManager = playerManager;
        this.scenariosGui = scenariosGui;
        this.pvpGui = pvpGui;
        this.worldBorderGui = worldBorderGui;
        this.finish = finish;
        this.scoreboardCreator = scoreboardCreator;
        this.main = main;
    }*/
/*
    public Pvp createPvpTask() {
        return new Pvp(playerManager, scenariosGui);
    }

    public Scheduler createScheduler() {
        return new Scheduler(pvpGui, worldBorderGui, playerManager);
    }

    public Teleportation teleportation() {
        StateManager stateManager = new StateManager(main, pvpGui , worldBorderGui , this, playerManager, scenariosGui);
        return new Teleportation(stateManager, finish, scoreboardCreator, worldBorderGui);
    }

    public WorldBorder worldBorder() {
        return new WorldBorder(worldBorderGui);
    }*/


//}