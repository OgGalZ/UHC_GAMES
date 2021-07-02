package me.oggalz.uhc_games.listeners;


import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.ActionBarUtils;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;


public class PlayerJoinEvent implements Listener {
    private final Main main;
    private final PlayerManager playerManager;
    private final StateManager stateManager;
    private final ActionBarUtils actionBarUtils;

    public PlayerJoinEvent(Main main, PlayerManager playerManager, StateManager stateManager, ActionBarUtils actionBarUtils) {
        this.main = main;
        this.playerManager = playerManager;
        this.stateManager = stateManager;
        this.actionBarUtils = actionBarUtils;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerJoinEvent(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getActivePotionEffects() != null) {
            for (PotionEffect potionEffectType : player.getActivePotionEffects()) {
                player.removePotionEffect(potionEffectType.getType());
            }
        }
        FileConfiguration configuration = main.getConfig();
        World world = Bukkit.getWorld("world");
        List<Integer> coordinate = configuration.getIntegerList("coordinatespawn");
        Location location = new Location(world, coordinate.get(0), coordinate.get(1), coordinate.get(2), 1, 1);
        if (stateManager.hasNotStarted()) {
            event.setJoinMessage(player.getName() + ChatColor.DARK_AQUA + " a rejoint la partie :) ");
            player.teleport(location);
            playerManager.addPlayer(player.getUniqueId());
            player.setFoodLevel(20);
            player.setHealth(20);
            player.getInventory().clear();
            Item.clearArmor(player);
            player.setGameMode(GameMode.ADVENTURE);
            actionBarUtils.sendActionBarToAllPlayers(ChatColor.BLUE + player.getDisplayName() + ChatColor.WHITE + " a rejoint la partie ! ", 100);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 9));
            if (player.isOp()) {
                ItemStack itemStack = me.oggalz.uhc_games.utils.Item.createItemstack(Material.COMPASS, 1, ChatColor.BLUE + "Config", null);
                player.getInventory().setItem(4, itemStack);
            }
        } else if (stateManager.hasStarted()) {
            if (!playerManager.containsplayers(player.getUniqueId())) {
                event.setJoinMessage("");
                player.setGameMode(GameMode.SPECTATOR);
                player.sendMessage(ChatColor.DARK_AQUA + "La partie a déjà commencé :/");
            }
        }

    }

    @EventHandler(priority = EventPriority.HIGH)

    public void playerQuitEvent(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        playerManager.removePlayer(player.getUniqueId());
        BPlayerBoard board = Netherboard.instance().getBoard(player);
        if (board != null) {
            board.delete();
            for (Player x : Bukkit.getOnlinePlayers()) {
                if (playerManager.containsplayers(x.getUniqueId())) {
                    Netherboard.instance().getBoard(x).set(ChatColor.BLUE + "Joueurs : " + ChatColor.WHITE + playerManager.getPlayers(), 9);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        if (itemStack.hasItemMeta() && itemStack.getItemMeta().getDisplayName() != null && itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BLUE + "Config")) {
            event.setCancelled(true);

        } else if (itemStack.hasItemMeta() && itemStack.getItemMeta().getDisplayName() != null && itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Power")) {
            event.setCancelled(true);
        }
    }
}
