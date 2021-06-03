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
import org.bukkit.plugin.java.JavaPlugin;

import javax.persistence.GeneratedValue;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
        switch (entityType) {

            case COW:
                int sizeBeef = loots.stream().filter(itemStack -> itemStack.getType() == Material.RAW_BEEF).collect(Collectors.toList()).get(0).getAmount();
                entity.getWorld().dropItemNaturally(location, Item.createItemstack(Material.COOKED_BEEF, sizeBeef, null, null));
                int sizeLeather = loots.stream().filter(itemStack -> itemStack.getType() == Material.LEATHER).collect(Collectors.toList()).get(0).getAmount();
                entity.getWorld().dropItemNaturally(location, Item.createItemstack(Material.LEATHER, sizeLeather, null, null));
                break;

            case PIG:
                int sizePig = loots.stream().filter(itemStack -> itemStack.getType() == Material.PORK).collect(Collectors.toList()).get(0).getAmount();
                entity.getWorld().dropItem(location, Item.createItemstack(Material.GRILLED_PORK, sizePig, null, null));
                break;


            case CHICKEN:
                int sizeChicken = loots.stream().filter(itemStack -> itemStack.getType() == Material.RAW_CHICKEN).collect(Collectors.toList()).get(0).getAmount();
                entity.getWorld().dropItemNaturally(location, Item.createItemstack(Material.COOKED_CHICKEN, sizeChicken, null, null));
                int sizeFeather = loots.stream().filter(itemStack -> itemStack.getType() == Material.FEATHER).collect(Collectors.toList()).get(0).getAmount();
                break;

            case SHEEP:

                int sizeSheep = loots.stream().filter(itemStack -> itemStack.getType() == Material.MUTTON).collect(Collectors.toList()).get(0).getAmount();
                entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_MUTTON, sizeSheep, null, null));
                break;

            case RABBIT:
                int sizeRabbit = loots.stream().filter(itemStack -> itemStack.getType() == Material.RABBIT).collect(Collectors.toList()).get(0).getAmount();
                entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_RABBIT, sizeRabbit, null, null));

        }

        loots.clear();

    }

}








