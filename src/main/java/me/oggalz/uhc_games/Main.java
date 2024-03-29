package me.oggalz.uhc_games;

import fr.minuskube.inv.SmartInventory;
import me.oggalz.uhc_games.commands.Finish;
import me.oggalz.uhc_games.commands.RolesCommands;
import me.oggalz.uhc_games.gui.*;
import me.oggalz.uhc_games.listeners.PlayerDeathEvent;
import me.oggalz.uhc_games.listeners.PlayerJoinEvent;
import me.oggalz.uhc_games.listeners.SecondaryListeners;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.races.RacesManager;
import me.oggalz.uhc_games.roles.RolesManagers;
import me.oggalz.uhc_games.roles.races_roles.Chasseur;
import me.oggalz.uhc_games.scenarios.*;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.tasks.*;
import me.oggalz.uhc_games.utils.ActionBarUtils;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
import me.oggalz.uhc_games.utils.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.logging.Level;

public class Main extends JavaPlugin {

    private PlayerManager playerManager;
    private ScoreboardCreator scoreboardCreator;
    private GuiManager guiManager;
    private Finish finish;
    private StateManager stateManager;
    private SmartInventory mainGui;
    private RacesManager racesManager;
    private RolesManagers rolesManagers;
    private RolesCommands rolesCommands;
    private SmartInventory marchandise;

    @Override
    public void onEnable() {

        World world = Bukkit.getWorld("world");
        world.getWorldBorder().setSize(5000000);
        this.playerManager = new PlayerManager();
        Team team = new Team(playerManager);

        rolesManagers = new RolesManagers(team, playerManager);
        PvpGui pvpGui = new PvpGui();
        ScenariosGui scenariosGui = new ScenariosGui();
        WorldBorderGui worldBorderGui = new WorldBorderGui();
        RolesGui racesGui = new RolesGui(rolesManagers);
        MarchandiseGui marchandiseGui = new MarchandiseGui();
        guiManager = new GuiManager(pvpGui, scenariosGui, worldBorderGui, racesGui, marchandiseGui);
        racesManager = new RacesManager(team, rolesManagers);
        finish = new Finish();
        scoreboardCreator = new ScoreboardCreator(this, playerManager);
        WorldBorder worldBorder = new WorldBorder(worldBorderGui);
        Pvp pvp = new Pvp(playerManager, guiManager.getScenariosGui(), rolesManagers);
        stateManager = new StateManager(this, guiManager.getPvpGui(), guiManager.getWorldBorderGui(), playerManager, worldBorder, pvp, racesManager, rolesManagers, team);
        Teleportation teleportation = new Teleportation(stateManager, finish, scoreboardCreator, guiManager.getWorldBorderGui());
        rolesCommands = new RolesCommands(rolesManagers, playerManager, racesManager, team);

        marchandise = SmartInventory.builder()
                .id("marchandise")
                .provider(marchandiseGui)
                .size(2, 9)
                .title(ChatColor.DARK_AQUA + "Marchandise")
                .closeable(true)
                .build();

        SmartInventory races = SmartInventory.builder()
                .id("races ")
                .provider(racesGui)
                .size(4, 9)
                .title(ChatColor.WHITE + "Races")
                .closeable(true)
                .build();

        SmartInventory pvpInventory = SmartInventory.builder()
                .id("PvpGui")
                .provider(pvpGui)
                .size(4, 9)
                .title(ChatColor.GOLD + "PVP/GAP")
                .closeable(true)
                .build();

        SmartInventory scenarios = SmartInventory.builder()
                .id("scenarios")
                .provider(scenariosGui)
                .size(4, 9)
                .title(ChatColor.BLUE + "Scenarios")
                .closeable(true)
                .build();


        SmartInventory bordure = SmartInventory.builder()
                .id("Bordure")
                .provider(worldBorderGui)
                .size(4, 9)
                .title(ChatColor.YELLOW + "Bordure")
                .closeable(true)
                .build();

        this.mainGui = SmartInventory.builder()
                .id("MainGui")
                .provider(new MainGui(pvpInventory, scenarios, bordure, this, teleportation, races, guiManager.racesGui()))
                .size(4, 9)
                .title(ChatColor.RED + "Configuration")
                .closeable(true)
                .build();

        List<Integer> respawn = this.getConfig().getIntegerList("respawn");
        Bukkit.getWorld("world").setSpawnLocation(respawn.get(0), respawn.get(1), respawn.get(2));
        getLogger().log(Level.INFO, "Le plugin s'est bien lancé");
        registersCommands();
        saveDefaultConfig();
        registersEvents();
        world.setBiome(0, 0, Biome.ROOFED_FOREST);
        world.setTime(0);
    }

    @Override
    public void onDisable() {
        saveDefaultConfig();
    }

    public void registersEvents() {
        CutClean cutClean = new CutClean();
        DiamondLimite diamondLimite = new DiamondLimite(guiManager.getScenariosGui());
        HastyBoy hastyBoy = new HastyBoy();
        Timber timber = new Timber();
        Team team = new Team(playerManager);
        ActionBarUtils actionBarUtils = new ActionBarUtils(this);
        VanillaPlus vanillaPlus = new VanillaPlus(this.getConfig());
        Chasseur chasseur = new Chasseur(rolesManagers, team);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this, playerManager, stateManager, actionBarUtils, team), this);
        getServer().getPluginManager().registerEvents(new SecondaryListeners(mainGui, stateManager, playerManager, racesManager, marchandise), this);
        getServer().getPluginManager().registerEvents(new RegisterUnRegister(this, cutClean, diamondLimite, hastyBoy, timber, vanillaPlus), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathEvent(stateManager, guiManager.getPvpGui(), playerManager, team, chasseur, racesManager), this);
    }

    public void registersCommands() {
        getCommand("finish").setExecutor(finish);
        getCommand("hob").setExecutor(rolesCommands);
    }
}
