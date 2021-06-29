package me.oggalz.uhc_games.gui;

public class GuiManager {

    private final PvpGui pvpGui;
    private final ScenariosGui scenariosGui;
    private final WorldBorderGui worldBorderGui;

    public GuiManager(PvpGui pvpGui, ScenariosGui scenariosGui, WorldBorderGui worldBorderGui) {
        this.pvpGui = pvpGui;
        this.scenariosGui = scenariosGui;
        this.worldBorderGui = worldBorderGui;
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
}
