package me.oggalz.uhc_games.scenarios;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.UniversalMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.ClickType;
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

public class CutClean  implements  Listener {




    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {

        Material material = event.getBlock().getType();
        Block block = event.getBlock();
        Location location = block.getLocation();
        Player player = event.getPlayer();
        Material currentItemType = player.getInventory().getItemInHand().getType();
        if (!currentItemType.equals(Material.DIAMOND_PICKAXE) && !currentItemType.equals(Material.IRON_PICKAXE) && !currentItemType.equals(Material.STONE_PICKAXE)) {
            return;
        } else {
            switch (material) {

                case GOLD_ORE:

                    block.setType(Material.AIR);
                    block.getWorld().spawn(location, ExperienceOrb.class).setExperience(event.getExpToDrop());
                    block.getWorld().dropItem(location, Item.createItemstack(Material.GOLD_INGOT, 1, null, null));
                    break;

                case IRON_ORE:

                    block.setType(Material.AIR);
                    block.getWorld().spawn(location, ExperienceOrb.class).setExperience(event.getExpToDrop());
                    block.getWorld().dropItem(location, Item.createItemstack(Material.IRON_INGOT, 1, null, null));
                    break;

                default:
                    break;
            }
        }


    }

    @EventHandler

    public void onEntityDeath(EntityDeathEvent event) {

        List<ItemStack> loots = event.getDrops();

        for (int i = loots.size() - 1; i >= 0; --i) {
            ItemStack is = loots.get(i);
            if (is == null) {
                return;
            }
            UniversalMaterial material = UniversalMaterial.ofType(is.getType());
            if (material == null) return;

            switch (material) {
                case RAW_BEEF:
                    loots.remove(i);
                    loots.add(new ItemStack(UniversalMaterial.COOKED_BEEF.getType()));
                    break;

                case RAW_PORK:
                    loots.remove(i);
                    loots.add(new ItemStack(UniversalMaterial.COOKED_PORKCHOP.getType()));
                    break;

                case RAW_CHICKEN:
                    loots.remove(i);
                    loots.add(new ItemStack(UniversalMaterial.COOKED_CHICKEN.getType()));
                    break;

                case RAW_MUTTON:
                    loots.remove(i);
                    loots.add(new ItemStack(UniversalMaterial.COOKED_MUTTON.getType()));
                    break;

                case RAW_RABBIT:
                    loots.remove(i);
                    loots.add(new ItemStack(UniversalMaterial.COOKED_RABBIT.getType()));
                    break;
                default:


            }

        }

    }


}

