package me.oggalz.uhc_games;

import me.oggalz.uhc_games.gui.MainGui;
import me.oggalz.uhc_games.listeners.PlayerJoinEvent;
import me.oggalz.uhc_games.listeners.UtilsListeners;
import me.oggalz.uhc_games.listeners.scenarios.CutClean;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.InventoryClass;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.entity.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.*;
import org.bukkit.map.MapView;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.util.Vector;

import java.net.InetSocketAddress;
import java.util.*;
import java.util.logging.Level;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {

        getLogger().log(Level.INFO, "Le plugin s'est bien lancé");
        PlayerManager playerManager = new PlayerManager();
        StateManager stateManager = new StateManager();
        ScoreboardCreator scoreboardCreator = new ScoreboardCreator(playerManager);
        InventoryClass inventoryClass = new InventoryClass(0, null);
        getServer().getPluginManager().registerEvents(new PlayerJoinEvent(this, playerManager, stateManager , scoreboardCreator), this);
        getServer().getPluginManager().registerEvents(new UtilsListeners(stateManager, this, playerManager), this);
        getServer().getPluginManager().registerEvents(new MainGui( stateManager) , this);
        getServer().getPluginManager().registerEvents(new CutClean(), this);

    }


    @Override
    public void onDisable() {
        saveDefaultConfig();
    }
}
