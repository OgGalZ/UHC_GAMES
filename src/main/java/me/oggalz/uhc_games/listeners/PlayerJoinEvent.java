package me.oggalz.uhc_games.listeners;


import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.NmsUtils;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
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
    private final ScoreboardCreator scoreboardCreator;
    private final NmsUtils nmsUtils;

    public PlayerJoinEvent(Main main, PlayerManager playerManager, StateManager stateManager, ScoreboardCreator scoreboardCreator, NmsUtils nmsUtils) {
        this.main = main;
        this.playerManager = playerManager;
        this.stateManager = stateManager;
        this.scoreboardCreator = scoreboardCreator;
        this.nmsUtils = nmsUtils;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void playerJoinEvent(org.bukkit.event.player.PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(player.getActivePotionEffects() !=  null){
            for(PotionEffect potionEffectType : player.getActivePotionEffects()){
                player.removePotionEffect(potionEffectType.getType());
            }
        }
        nmsUtils.sendActionBar(player, player.getName() + ChatColor.DARK_AQUA + " a rejoint la partie :) ");
        FileConfiguration configuration = main.getConfig();
        World world = Bukkit.getWorld("world");
        List<Integer> coordinate = configuration.getIntegerList("coordinatespawn");
        Location location = new Location(world, coordinate.get(0), coordinate.get(1), coordinate.get(2), 1, 1);
        if (stateManager.hasNotStarted()) {
            event.setJoinMessage(player.getName() + ChatColor.DARK_AQUA + " a rejoint la partie :) ");
            player.teleport(location);
            playerManager.addPlayer(player.getUniqueId());
            scoreboardCreator.createScoreboardLobby(player);
            scoreboardCreator.refreshLobby();
            player.setFoodLevel(20);
            player.setHealth(20);
            player.getInventory().clear();
            Item.clearArmor(player);
            player.setGameMode(GameMode.ADVENTURE);
            player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 9));
            if (player.isOp()) {
                ItemStack itemStack = me.oggalz.uhc_games.utils.Item.createItemstack(Material.COMPASS, 1, ChatColor.BLUE + "Config", null);
                player.getInventory().setItem(4, itemStack);
            }
        } else {
            event.setJoinMessage("");
            player.setGameMode(GameMode.SPECTATOR);
            player.sendMessage(ChatColor.DARK_AQUA + "La partie a déjà commencé :/");
        }

    }

    @EventHandler(priority = EventPriority.HIGH)

    public void playerQuitEvent(PlayerQuitEvent event) {

        Player player = event.getPlayer();
        playerManager.removePlayer(player.getUniqueId());
        scoreboardCreator.refreshLobby();

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        if (stateManager.hasNotStarted() && itemStack.hasItemMeta() && itemStack.getType() == Material.COMPASS) {
            event.setCancelled(true);
        }

    }



}

