package me.oggalz.uhc_games.tasks;

/*import me.oggalz.uhc_games.commands.Finish;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.ScoreboardCreator;

import java.util.Random;

public class TaskFactory {

    private final StateManager stateManager;
    private final Finish finish;
    private final ScoreboardCreator scoreboardCreator;
    private final Random random;
    private final WorldBorderGui worldBorderGui;
    private final PvpGui pvpGui;
    private final PlayerManager playerManager;
    private final Pvp pvp;
    private final ScenariosGui scenariosGui;
    public TaskFactory(StateManager stateManager, Finish finish, ScoreboardCreator scoreboardCreator, WorldBorderGui worldBorderGui, PvpGui pvpGui, PlayerManager playerManager, Pvp pvp, ScenariosGui scenariosGui) {
        this.stateManager = stateManager;
        this.finish = finish;
        this.scoreboardCreator = scoreboardCreator;
        this.worldBorderGui = worldBorderGui;
        this.pvpGui = pvpGui;
        this.playerManager = playerManager;
        this.pvp = pvp;
        this.scenariosGui = scenariosGui;
        this.random = new Random();
    }
    public Teleportation teleportation(){
        return  new Teleportation(stateManager , finish , scoreboardCreator , worldBorderGui);
    }
    public Scheduler scheduler(){
        return  new Scheduler(pvpGui , worldBorderGui , playerManager );
    }
    public Pvp pvp(){
        return  new Pvp(playerManager , scenariosGui );
    }
    public WorldBorder worldBorder(){
        return  new WorldBorder(worldBorderGui);
    }
}*/