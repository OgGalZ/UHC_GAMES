package me.oggalz.uhc_games.gui;

public class GuiManager {

    private final PvpGui pvpGui;
    private final ScenariosGui scenariosGui;
    private final WorldBorderGui worldBorderGui;
    private final RolesGui racesGui;
    private final MarchandiseGui marchandiseGui;

    public GuiManager(PvpGui pvpGui, ScenariosGui scenariosGui, WorldBorderGui worldBorderGui, RolesGui racesGui, MarchandiseGui marchandiseGui) {
        this.pvpGui = pvpGui;
        this.scenariosGui = scenariosGui;
        this.worldBorderGui = worldBorderGui;
        this.racesGui = racesGui;
        this.marchandiseGui = marchandiseGui;
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

    public RolesGui racesGui() {
        return racesGui;
    }

    public MarchandiseGui getMarchandiseGui() {
        return marchandiseGui;
    }
}
