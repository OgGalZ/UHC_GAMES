package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Teleportation extends BukkitRunnable {

    private final PlayerManager playerManager;
    private final Main main;
    private int start = 0;

    public Teleportation(PlayerManager playerManager, Main main) {
        this.playerManager = playerManager;
        this.main = main;
    }

    @Override
    public void run() {
        String startMessage = ChatColor.RED + "La partie ce lancera dans ";
        List<Player> uuidPlayers = new ArrayList<>();

        for (Player p : Bukkit.getOnlinePlayers()) {
            Bukkit.broadcastMessage(startMessage + start);
            p.playSound(p.getLocation() , Sound.CHEST_CLOSE , 99 , 1);
            if (playerManager.containsplayers(p.getUniqueId())) {
                uuidPlayers.add(p.getPlayer());
            }
        }
        start += 1;
        if(start == 10 ){
            cancel();
        }
    }

    public void runTp() {
        Teleportation teleportation = new Teleportation(playerManager, main);
        teleportation.runTaskTimer(main, 200, 20);
    }
}