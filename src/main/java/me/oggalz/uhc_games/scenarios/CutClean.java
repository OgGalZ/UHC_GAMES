package me.oggalz.uhc_games.scenarios;


import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.UniversalMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CutClean implements Listener {


    public CutClean() {
    }

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

            Map<UniversalMaterial, UniversalMaterial> food = new HashMap<>();
            food.put(UniversalMaterial.RAW_BEEF, UniversalMaterial.COOKED_BEEF);
            food.put(UniversalMaterial.RAW_PORK, UniversalMaterial.COOKED_PORKCHOP);
            food.put(UniversalMaterial.RAW_CHICKEN, UniversalMaterial.COOKED_CHICKEN);
            food.put(UniversalMaterial.RAW_MUTTON, UniversalMaterial.COOKED_MUTTON);
            food.put(UniversalMaterial.RAW_RABBIT, UniversalMaterial.COOKED_RABBIT);

            if (food.containsKey(material)) {
                UniversalMaterial cookedFood = food.get(material);
                loots.remove(i);
                loots.add(new ItemStack(cookedFood.getType()));
            }


        }

    }

    @Override
    public String toString() {
        return ChatColor.BLACK + "Cut Clean";
    }
}

