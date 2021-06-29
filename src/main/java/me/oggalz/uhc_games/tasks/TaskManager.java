package me.oggalz.uhc_games.tasks;

public class TaskManager {
    private Pvp pvp;
    private Teleportation teleportation;
    private WorldBorder worldBorder;

    public TaskManager(Pvp pvp, Teleportation teleportation, WorldBorder worldBorder) {
        this.pvp = pvp;
        this.teleportation = teleportation;
        this.worldBorder = worldBorder;
    }

    public Pvp getPvp() {
        return pvp;
    }

    public Teleportation getTeleportation() {
        return teleportation;
    }

    public WorldBorder getWorldBorder() {
        return worldBorder;
    }
}
