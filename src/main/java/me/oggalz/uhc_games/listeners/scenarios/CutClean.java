package me.oggalz.uhc_games.listeners.scenarios;


import me.oggalz.uhc_games.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class CutClean implements Listener {




    @EventHandler
    private void onBlockBreak(BlockBreakEvent event , boolean enable) {
        enable = false;
        event.setCancelled(enable);
        Material material = event.getBlock().getType();
        Block block = event.getBlock();
        Location location = block.getLocation();
        switch (material) {
            case DIAMOND_ORE:
                block.getWorld().dropItem(location , Item.createItemstack(Material.DIAMOND , 1 , null , null));break;

           case GOLD_ORE:
                block.getWorld().dropItem(location , Item.createItemstack(Material.GOLD_INGOT, 1 , null , null));
                break;

            case IRON_ORE:
                block.getWorld().dropItem(location , Item.createItemstack(Material.IRON_INGOT, 1 , null , null));
                break;
            case  COAL_ORE:
                block.getWorld().dropItem(location , Item.createItemstack(Material.TORCH, 4 , null , null));
                break;
        }
    }

   @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        //setcancelled impossible
        EntityType entityType = event.getEntityType();
        Entity entity = event.getEntity();
        Location location = entity.getLocation();

        switch (entityType){
            case COW:
                entity.getWorld().dropItem(location , Item.createItemstack(Material.COOKED_BEEF , 1, null , null));
                break;


            case PIG:
                entity.getWorld().dropItem(location , Item.createItemstack(Material.GRILLED_PORK, 1, null , null));
                break;


            case SHEEP:
                entity.getWorld().dropItem(location , Item.createItemstack(Material.COOKED_MUTTON, 1, null , null));
                break;

            case CHICKEN:
                entity.getWorld().dropItem(location , Item.createItemstack(Material.COOKED_CHICKEN, 1, null , null));
        }

    }

}