package me.oggalz.uhc_games.tasks;

import fr.minuskube.netherboard.Netherboard;

import me.oggalz.uhc_games.commands.Finish;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
import org.bukkit.*;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Teleportation extends BukkitRunnable {

    private int start = 30;
    private final StateManager stateManager;
    private final Finish finish;
    private final ScoreboardCreator scoreboardCreator;
    private final Random random;
    private final WorldBorderGui worldBorderGui;


    public Teleportation(StateManager stateManager, Finish finish, ScoreboardCreator scoreboardCreator, WorldBorderGui worldBorderGui) {
        this.stateManager = stateManager;
        this.finish = finish;
        this.scoreboardCreator = scoreboardCreator;
        this.worldBorderGui = worldBorderGui;
        random = new Random();
    }

    @Override
    public void run() {
        if (start <= 0) {
            teleportation();
            cancel();
        }
        if (start == 30) {
            Bukkit.broadcastMessage(ChatColor.RED + "La partie se lancera dans " + ChatColor.BLUE + start);
            loadChunks();
        }
        if (start == 20) {
            Bukkit.broadcastMessage(ChatColor.RED + "La partie se lancera dans " + ChatColor.BLUE + start);
        }
        if (start == 10) {
            Bukkit.broadcastMessage(ChatColor.RED + "La partie se lancera dans " + ChatColor.BLUE + start);
        }
        if (start <= 5) {
            Bukkit.broadcastMessage(ChatColor.RED + "La partie se lancera dans " + ChatColor.BLUE + start);
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.playSound(player.getLocation(), Sound.PORTAL, 99, 2);
            }
        }

        start--;
    }


    public void loadChunks() {
        World world = Bukkit.getWorld("world");
        for (int x = worldBorderGui.getBorderSize() / 2; x > -worldBorderGui.getBorderSize() / 2 + 5; x--) {
            for (int z = worldBorderGui.getBorderSize() / 2; z > -worldBorderGui.getBorderSize() / 2 + 5; z--) {
                Location location = new Location(world, x, 60, z);
                if (!(location.getChunk().isLoaded())) {
                    location.getChunk().load();
                }
            }
        }
    }


    public void teleportation() {
        World world = Bukkit.getWorld("world");
        int randomTP = generate(-worldBorderGui.getBorderSize() / 2, worldBorderGui.getBorderSize() / 2);
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location location = new Location(world, randomTP, 90, randomTP);
            player.teleport(location);
            player.setGameMode(GameMode.SURVIVAL);
            WorldBorder worldBorder = world.getWorldBorder();
            worldBorder.setCenter(0, 0);
            worldBorder.setDamageAmount(2);
            worldBorder.setDamageBuffer(0);
            worldBorder.setSize(worldBorderGui.getBorderSize());
            player.teleport(location);
            Item.clearArmor(player);
            player.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
          player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100, 999999999));
           player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 999999999));
          player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 9));
            player.getInventory().clear();
            scoreboardCreator.deleteScoreboard(Netherboard.instance().getBoard(player));
            scoreboardCreator.createScoreboardGame(player);
            stateManager.startGame();
            if (finish.getItemStacks() != null) {
                Arrays.stream(finish.getItemStacks()).filter(Objects::nonNull).forEach(i -> player.getInventory().addItem(i));
            }
            if (finish.getArmor() != null) {
                Arrays.stream(finish.getArmor()).filter(Objects::nonNull).forEach(i -> player.getInventory().setArmorContents(finish.getArmor()));
            }

        }
    }


    public int generate(int borneInf, int borneSup) {
        int nb;
        nb = borneInf + this.random.nextInt(borneSup - borneInf);
        return nb;

    }
}