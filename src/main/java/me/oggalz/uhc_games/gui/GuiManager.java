package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.SmartInventory;

public class GuiManager {

    private final PvpGui pvpGui;
    private final ScenariosGui scenariosGui;
    private final WorldBorderGui worldBorderGui;
    private final RacesGui racesGui;

    public GuiManager(PvpGui pvpGui, ScenariosGui scenariosGui, WorldBorderGui worldBorderGui, RacesGui racesGui) {
        this.pvpGui = pvpGui;
        this.scenariosGui = scenariosGui;
        this.worldBorderGui = worldBorderGui;
        this.racesGui = racesGui;
    }

    public PvpGui getPvpGui() {
        return pvpGui;
    }

    public ScenariosGui getScenariosGui() {
        return scenariosGui;
    }

    public WorldBorderGui getWorldBorderGui() {
        return worldBorderGui;
    }

    public RacesGui racesGui() {
        return racesGui;
    }
}
