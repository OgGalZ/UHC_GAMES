package me.oggalz.uhc_games.scenarios;


import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class DiamondLimite  implements Listener {

    private ScenariosGui scenariosGui ;

    public DiamondLimite(ScenariosGui scenariosGui) {
        this.scenariosGui = scenariosGui;
    }

    @EventHandler
    public void BlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Location location = block.getLocation();
        Inventory playerInv = event.getPlayer().getInventory();

        for (ItemStack x : playerInv) {
            if (x != null) {
                if (x.getType() == Material.DIAMOND) {
                    int sizeDiamond = x.getAmount()  ;
                    if (block.getType() == Material.DIAMOND_ORE) {
                        if (sizeDiamond > scenariosGui.getX()   && scenariosGui.getX() != 0 ) {
                            block.setType(Material.AIR);
                            block.getWorld().spawn(location, ExperienceOrb.class).setExperience(event.getExpToDrop());
                            block.getWorld().dropItem(location, Item.createItemstack(Material.GOLD_INGOT, 1, null, null));
                        }
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        return ChatColor.BLUE + "Diamond Limite";
    }
}