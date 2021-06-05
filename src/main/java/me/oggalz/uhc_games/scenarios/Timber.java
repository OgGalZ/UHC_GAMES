package me.oggalz.uhc_games.scenarios;

import me.oggalz.uhc_games.Main;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;


public class Timber implements Listener {


    @EventHandler
    public static void onBlockBreak(BlockBreakEvent e) {
            breakBlock(e.getBlock());
    }

    static void breakBlock(Block b) {
        if (b.getType() != Material.LOG && b.getType() != Material.LOG_2)
            return;

        b.getWorld().playSound(b.getLocation(), Sound.DIG_WOOD, 20, 1);

        b.breakNaturally();

        breakBlock(b.getLocation().add(0, 1, 0).getBlock());
        breakBlock(b.getLocation().add(1, 0, 0).getBlock());
        breakBlock(b.getLocation().add(0, 0, 1).getBlock());

        breakBlock(b.getLocation().subtract(0, 1, 0).getBlock());
        breakBlock(b.getLocation().subtract(1, 0, 0).getBlock());
        breakBlock(b.getLocation().subtract(0, 0, 1).getBlock());
    }
}





