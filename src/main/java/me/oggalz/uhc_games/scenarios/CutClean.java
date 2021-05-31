package me.oggalz.uhc_games.scenarios;

import me.oggalz.uhc_games.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.persistence.GeneratedValue;
import java.util.List;

public class CutClean implements Listener {


    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {

        Material material = event.getBlock().getType();
        Block block = event.getBlock();
        Location location = block.getLocation();
        Player player = event.getPlayer();
        Material currentItemType = player.getInventory().getItemInHand().getType();

        switch (material) {


            case GOLD_ORE:

                if (!currentItemType.equals(Material.DIAMOND_PICKAXE) && !currentItemType.equals(Material.IRON_PICKAXE) && !currentItemType.equals(Material.STONE_PICKAXE)) {
                    return;
                }
                block.setType(Material.AIR);
                block.getWorld().spawn(location, ExperienceOrb.class).setExperience(event.getExpToDrop());
                block.getWorld().dropItem(location, Item.createItemstack(Material.GOLD_INGOT, 1, null, null));
                break;

            case IRON_ORE:

                if (!currentItemType.equals(Material.DIAMOND_PICKAXE) && !currentItemType.equals(Material.IRON_PICKAXE) && !currentItemType.equals(Material.STONE_PICKAXE)) {
                    return;
                }
                block.setType(Material.AIR);
                block.getWorld().spawn(location, ExperienceOrb.class).setExperience(event.getExpToDrop());
                block.getWorld().dropItem(location, Item.createItemstack(Material.IRON_INGOT, 1, null, null));
                break;

            case COAL_ORE:
                if (!currentItemType.equals(Material.DIAMOND_PICKAXE) && !currentItemType.equals(Material.IRON_PICKAXE) && !currentItemType.equals(Material.STONE_PICKAXE)) {
                    return;
                }
                block.setType(Material.AIR);
                block.getWorld().spawn(location, ExperienceOrb.class).setExperience(event.getExpToDrop());
                block.getWorld().dropItem(location, Item.createItemstack(Material.TORCH, 4, null, null));
                break;
            default:
                break;
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)

    public void onEntityDeath(EntityDeathEvent event) {

        List<ItemStack> loots = event.getDrops();
        EntityType entityType = event.getEntityType();
        Entity entity = event.getEntity();
        Location location = entity.getLocation();
        int random = 1 + (int) (Math.random() * ((3 - 1) + 1));
        int ramdomutils = 0 + (int) (Math.random() * ((3 - 0)) + 0);


        for (int i = loots.size() - 1; i >= 0; --i) {
            ItemStack is = loots.get(i);
            if (is == null) {
                return;
            }

            try {
                switch (entityType) {
                    case COW:

                        loots.remove(i);
                        entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_BEEF, random, null, null));
                        entity.getWorld().dropItem(location, Item.createItemstack(Material.LEATHER, ramdomutils, null, null));

                        break;

                    case PIG:
                        loots.remove(i);
                        entity.getWorld().dropItem(location, Item.createItemstack(Material.GRILLED_PORK, random, null, null));
                        break;

                    case CHICKEN:
                        loots.remove(i);
                        entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_CHICKEN, random, null, null));
                        entity.getWorld().dropItem(location, Item.createItemstack(Material.FEATHER, ramdomutils, null, null));
                        break;

                    case SHEEP:
                        loots.remove(i);
                        entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_MUTTON, random, null, null));
                        break;

                    case RABBIT:
                        loots.remove(i);
                        entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_RABBIT, random, null, null));
                        break;
                    default:


                }
            } catch (IllegalArgumentException e) {
                System.out.println("");
            }
        }

    }

}

