package me.oggalz.uhc_games.scenarios;

import me.oggalz.uhc_games.utils.UniversalMaterial;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class HastyBoy implements Listener {

    private final List<Material> materials = new ArrayList<>();

    public HastyBoy() {
        materials.add(UniversalMaterial.WOODEN_AXE.getType());
        materials.add(UniversalMaterial.WOODEN_PICKAXE.getType());
        materials.add(UniversalMaterial.WOODEN_SHOVEL.getType());
        materials.add(UniversalMaterial.GOLDEN_AXE.getType());
        materials.add(UniversalMaterial.GOLDEN_PICKAXE.getType());
        materials.add(UniversalMaterial.GOLDEN_SHOVEL.getType());
        materials.add(Material.STONE_AXE);
        materials.add(Material.STONE_PICKAXE);
        materials.add(UniversalMaterial.STONE_SHOVEL.getType());
        materials.add(Material.IRON_AXE);
        materials.add(Material.IRON_PICKAXE);
        materials.add(UniversalMaterial.IRON_SHOVEL.getType());
        materials.add(Material.DIAMOND_AXE);
        materials.add(Material.DIAMOND_PICKAXE);
        materials.add(UniversalMaterial.DIAMOND_SHOVEL.getType());

    }

    @EventHandler
    public void playerCraftEvent(PrepareItemCraftEvent event) {

        if (event.getInventory().getResult() == null) return;

        Material itemType = event.getInventory().getResult().getType();
        if (materials.contains(itemType)) {
            ItemStack item = new ItemStack(itemType);
            item.addUnsafeEnchantment(Enchantment.DIG_SPEED, 3);
            item.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
            event.getInventory().setResult(item);
        }

    }

    @Override
    public String toString() {
        return ChatColor.WHITE + "Hasty Boy";
    }
}
