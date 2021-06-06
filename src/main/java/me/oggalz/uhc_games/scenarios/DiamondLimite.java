package me.oggalz.uhc_games.scenarios;

import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class DiamondLimite extends RegisterUnRegister {

    public DiamondLimite(Main main) {
        super(main);
    }

    @EventHandler
    public void BlockBreak(BlockBreakEvent event) {
        Block block = event.getBlock();
        Location location = block.getLocation();
        Inventory playerInv = event.getPlayer().getInventory();


        for (ItemStack x : playerInv) {
            if (x != null) {
                if (x.getType() == Material.DIAMOND) {
                    int sizeDiamond = x.getAmount();
                    if (block.getType() == Material.DIAMOND_ORE) {
                        if (sizeDiamond >= ScenariosGui.getX()) {
                            block.setType(Material.AIR);
                            block.getWorld().spawn(location, ExperienceOrb.class).setExperience(event.getExpToDrop());
                            block.getWorld().dropItem(location, Item.createItemstack(Material.GOLD_INGOT, 1, null, null));
                        }
                    }
                }
            }
        }
    }
    @EventHandler
    @Override
    public void Register(InventoryClickEvent event, Listener listener) {
        Inventory inventory = event.getInventory();
        ClickType action = event.getClick();


        if (inventory.contains(Material.DIAMOND_ORE) && inventory.getTitle().equalsIgnoreCase(ChatColor.BLUE + "Scenarios")) {
            if (action == ClickType.RIGHT) {
                HandlerList.unregisterAll(this);
            } else if (action == ClickType.LEFT) {
                main.getServer().getPluginManager().registerEvents(this, main);
            }


        }
    }
}