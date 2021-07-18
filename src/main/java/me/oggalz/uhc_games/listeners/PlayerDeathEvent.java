package me.oggalz.uhc_games.listeners;

import fr.minuskube.netherboard.Netherboard;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.Team;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PlayerDeathEvent implements Listener {


    private final StateManager stateManager;
    private final PvpGui pvpGui;
    private final PlayerManager playerManager;
    private final Team team;

    public PlayerDeathEvent(StateManager stateManager, PvpGui pvpGui, PlayerManager playerManager, Team team) {
        this.stateManager = stateManager;
        this.pvpGui = pvpGui;
        this.playerManager = playerManager;
        this.team = team;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerDeath(org.bukkit.event.entity.PlayerDeathEvent event) {
        Location location = event.getEntity().getLocation();
        Player player = event.getEntity().getKiller();
        Player playerDeath = event.getEntity();
        World world = event.getEntity().getWorld();
        if (stateManager.hasNotStarted()) {
            event.getDrops().clear();
        }
        if (pvpGui.getNumbersGaps() != 0 && stateManager.hasStarted()) {
            world.dropItem(location, Item.createItemstack(Material.GOLDEN_APPLE, pvpGui.getNumbersGaps(), null, null));
        }
        if (player != null && playerDeath != null) {
            playerManager.getPlayer(player.getUniqueId()).addKill(1);
            Netherboard.instance().getBoard(player).set(ChatColor.RED + "Kill(s) : " + playerManager.getPlayer(player.getUniqueId()).getKill(), 11);
        }
        if (playerDeath != null) {
            if (team.getTeamTavernier().containsKey(playerDeath.getUniqueId())) {
                if (team.getTavernier1().containsKey(playerDeath)) {
                    team.getTavernier1().remove(playerDeath);
                    for (Player playerAlive : team.getTavernier1().keySet()) {
                        playerAlive.removePotionEffect((PotionEffectType) playerAlive.getActivePotionEffects());
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 0));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 0));
                        player.setHealth(16);
                    }
                } else {
                    team.getTavernier2().remove(playerDeath);
                    for (Player playerAlive : team.getTavernier2().keySet()) {
                        playerAlive.removePotionEffect((PotionEffectType) playerAlive.getActivePotionEffects());
                        assert player != null;
                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 0));
                        player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 0));
                        player.setHealth(16);

                    }
                }
            }
        }

    }
}
