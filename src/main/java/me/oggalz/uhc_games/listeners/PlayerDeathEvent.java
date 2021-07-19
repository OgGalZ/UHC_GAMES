package me.oggalz.uhc_games.listeners;

import fr.minuskube.netherboard.Netherboard;
import me.oggalz.uhc_games.gui.PvpGui;
import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.roles.races_roles.Chasseur;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.Team;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.stream.Collectors;


public class PlayerDeathEvent implements Listener {


    private final StateManager stateManager;
    private final PvpGui pvpGui;
    private final PlayerManager playerManager;
    private final Team team;
    private final Chasseur chasseur;


    public PlayerDeathEvent(StateManager stateManager, PvpGui pvpGui, PlayerManager playerManager, Team team, Chasseur chasseur) {
        this.stateManager = stateManager;
        this.pvpGui = pvpGui;
        this.playerManager = playerManager;
        this.team = team;
        this.chasseur = chasseur;

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void playerDeath(org.bukkit.event.entity.PlayerDeathEvent event) {
        Location location = event.getEntity().getLocation();
        Player playerKiller = event.getEntity().getKiller();
        Player playerDeath = null;
        if (event.getEntity() != null) {
            playerDeath = event.getEntity().getPlayer();
        }
        World world = event.getEntity().getWorld();
        if (stateManager.hasNotStarted()) {
            event.getDrops().clear();
        } else {
            List<ItemStack> deleted = event.getDrops().stream().filter(itemStack -> itemStack.getItemMeta() != null && itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GOLD + "Power")).collect(Collectors.toList());
            deleted.clear();
        }
        if (pvpGui.getNumbersGaps() != 0 && stateManager.hasStarted()) {
            world.dropItem(location, Item.createItemstack(Material.GOLDEN_APPLE, pvpGui.getNumbersGaps(), null, null));
        }
        if (playerKiller != null && playerDeath != null) {
            playerManager.getPlayer(playerKiller.getUniqueId()).addKill(1);
            Netherboard.instance().getBoard(playerKiller).set(ChatColor.RED + "Kill(s) : " + playerManager.getPlayer(playerKiller.getUniqueId()).getKill(), 11);
        }

        if (team.getTeamTavernier().containsKey(playerDeath.getUniqueId())) {
            if (team.getTavernier1().containsKey(playerDeath)) {
                team.getTavernier1().remove(playerDeath);
                for (Player playerAlive : team.getTavernier1().keySet()) {
                    playerAlive.removePotionEffect((PotionEffectType) playerAlive.getActivePotionEffects());
                    assert playerKiller != null;
                    playerKiller.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 0));
                    playerKiller.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 0));
                    playerKiller.setHealth(16);
                }
            } else {
                team.getTavernier2().remove(playerDeath);
                for (Player playerAlive : team.getTavernier2().keySet()) {
                    playerAlive.removePotionEffect((PotionEffectType) playerAlive.getActivePotionEffects());
                    assert playerKiller != null;
                    playerKiller.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 0));
                    playerKiller.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 0));
                    playerKiller.setHealth(16);
                }
            }
        }
        if (chasseur.getHuntersTagers().containsKey(playerKiller)) {
            if (chasseur.getHuntersTagers().get(playerKiller) == playerDeath) {
                playerKiller.sendMessage(ChatColor.BLUE + "Bravo , vous avez tué votre cible ");
            }
        }


    }
}
