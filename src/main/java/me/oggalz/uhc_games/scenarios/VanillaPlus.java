package me.oggalz.uhc_games.scenarios;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class VanillaPlus implements Listener {
    private final FileConfiguration fileConfiguration;

    public VanillaPlus(FileConfiguration fileConfiguration) {
        this.fileConfiguration = fileConfiguration;
    }

    @EventHandler
    private void onGravelBreak(BlockBreakEvent event) {

        List<Integer> rateFlints = fileConfiguration.getIntegerList("rateFlints");
        Block block = event.getBlock();
        Location loc = new Location(block.getWorld(),
                block.getLocation().getBlockX() + 0.5,
                block.getLocation().getBlockY() + 0.5,
                block.getLocation().getBlockZ() + 0.5);

        if (block.getType().equals(Material.GRAVEL)) {
            block.setType(Material.AIR);
            if (Math.random() * 100 < rateFlints.get(0)) {
                block.getWorld().dropItem(loc, new ItemStack(Material.FLINT, 1));
            } else
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),
                        new ItemStack(Material.GRAVEL));
        }
    }

    @EventHandler
    public void onLeaveDecay(LeavesDecayEvent event) {
        List<Integer> rateApples = fileConfiguration.getIntegerList("rateApples");

        event.getBlock().setType(Material.AIR);
        if (Math.random() * 100 < rateApples.get(0)) {
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(),
                    new ItemStack(Material.APPLE));
        }
    }

    @Override
    public String toString() {
        return ChatColor.GREEN + "Vanilla +";
    }
}



