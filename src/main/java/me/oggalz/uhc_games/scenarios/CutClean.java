package me.oggalz.uhc_games.scenarios;

import me.oggalz.uhc_games.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
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
import java.io.IOException;
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
        try {
            switch (entityType) {
                case COW:
                    int sizeBeef = loots.stream().filter(itemStack -> itemStack.getType() == Material.RAW_BEEF).collect(Collectors.toList()).get(0).getAmount();
                    List<ItemStack> beef = loots.stream().filter(itemStack -> itemStack.getType() == Material.RAW_BEEF).collect(Collectors.toList());
                    for (ItemStack e : beef) {
                        loots.remove(e);
                    }
                    entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_BEEF, sizeBeef, null, null));
                    break;

                case PIG:

                    int sizePig = loots.stream().filter(itemStack -> itemStack.getType() == Material.PORK).collect(Collectors.toList()).get(0).getAmount();
                    List<ItemStack> pig = loots.stream().filter(itemStack -> itemStack.getType() == Material.PORK).collect(Collectors.toList());
                    for (ItemStack e : pig) {
                        loots.remove(e);
                    }
                    entity.getWorld().dropItem(location, Item.createItemstack(Material.GRILLED_PORK, sizePig, null, null));
                    break;


                case CHICKEN:
                    int sizeChicken = loots.stream().filter(itemStack -> itemStack.getType() == Material.RAW_CHICKEN).collect(Collectors.toList()).get(0).getAmount();
                    List<ItemStack> chicken = loots.stream().filter(itemStack -> itemStack.getType() == Material.RAW_CHICKEN).collect(Collectors.toList());
                    for (ItemStack e : chicken) {
                        loots.remove(e);
                    }
                    entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_CHICKEN, sizeChicken, null, null));
                    break;

                case SHEEP:

                    int sizeSheep = loots.stream().filter(itemStack -> itemStack.getType() == Material.MUTTON).collect(Collectors.toList()).get(0).getAmount();
                    List<ItemStack> sheep = loots.stream().filter(itemStack -> itemStack.getType() == Material.MUTTON).collect(Collectors.toList());
                    for (ItemStack e : sheep) {
                        loots.remove(e);
                    }
                    entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_MUTTON, sizeSheep, null, null));

                    break;

                case RABBIT:
                    int sizeRabbit = loots.stream().filter(itemStack -> itemStack.getType() == Material.RABBIT).collect(Collectors.toList()).get(0).getAmount();
                    List<ItemStack> rabbit = loots.stream().filter(itemStack -> itemStack.getType() == Material.RABBIT).collect(Collectors.toList());
                    for (ItemStack e : rabbit) {
                        loots.remove(e);
                    }
                    entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_RABBIT, sizeRabbit, null, null));
                    break;

                default:
                    break;


            }
        } catch (IndexOutOfBoundsException e) {

        }
    }


}



