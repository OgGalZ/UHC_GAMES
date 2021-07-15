package me.oggalz.uhc_games.state;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.races.RacesManager;
import me.oggalz.uhc_games.roles.RolesManagers;
import me.oggalz.uhc_games.tasks.Pvp;
import me.oggalz.uhc_games.tasks.Scheduler;
import me.oggalz.uhc_games.tasks.WorldBorder;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.UUID;

public class StateManager {

    private State gameState;
    private final Main main;
    private final PvpGui pvpGui;
    private final WorldBorderGui worldBorderGui;
    private final PlayerManager playerManager;
    private final WorldBorder worldBorder;
    private final Pvp pvp;
    private final RacesManager racesManager;
    private final RolesManagers rolesManagers;

    public StateManager(Main main, PvpGui pvpGui, WorldBorderGui worldBorderGui, PlayerManager playerManager, WorldBorder worldBorder, Pvp pvp, RacesManager racesManager, RolesManagers rolesManagers) {
        this.main = main;
        this.pvpGui = pvpGui;
        this.worldBorderGui = worldBorderGui;
        this.playerManager = playerManager;
        this.worldBorder = worldBorder;
        this.pvp = pvp;
        this.racesManager = racesManager;
        this.rolesManagers = rolesManagers;
        gameState = State.WAITING;
    }

    public void startGame() {
        me.oggalz.uhc_games.player.Player playerClass = new me.oggalz.uhc_games.player.Player(UUID.randomUUID());
        gameState = State.STARTING;
        int secondsPvp = pvpGui.getTimePvp() * 60;
        int secondsBorder = worldBorderGui.getTimeBorder() * 60;
        Scheduler scheduler = new Scheduler(pvpGui, worldBorderGui, playerManager, playerClass, racesManager, rolesManagers);
        scheduler.runTaskTimer(main, 0, 20L);
        pvp.runTaskLater(main, secondsPvp * 20L);
        worldBorder.runTaskTimer(main, secondsBorder * 20L, 20);
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.getInventory().addItem(Item.createItemstack(Material.GHAST_TEAR, 1, ChatColor.GOLD + "Power", null));
            player.playSound(player.getLocation(), Sound.GLASS, 99, 99);
        }
        rolesManagers.generateMapPlayersWithoutRaces();
        racesManager.generateMapRaces();

    }

    public boolean hasStarted() {
        return gameState == State.STARTING;
    }

    public boolean hasNotStarted() {
        return gameState == State.WAITING;
    }


}
