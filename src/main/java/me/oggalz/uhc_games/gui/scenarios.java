package me.oggalz.uhc_games.gui;

import com.sun.java.accessibility.util.GUIInitializedListener;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.omg.PortableInterceptor.ACTIVE;

public class scenarios implements Listener {


    @EventHandler(priority = EventPriority.NORMAL)
    public void onClick(InventoryClickEvent event) {

        ItemStack itemStack = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        InventoryAction action = event.getAction();
        Inventory gui = Bukkit.createInventory(null , 9*4 , ChatColor.DARK_AQUA + "Scenarios");
        gui.setItem(11  , Item.createItemstack(Material.IRON_INGOT , 1 , ChatColor.RED + "Cut Clean" , null));
        gui.setItem(16  ,  Item.createItemstack(Material.APPLE , 1 , ChatColor.BLUE + "Vanilla+" , null));
        gui.setItem(21, Item.createItemstack(Material.DIAMOND_ORE , 1 , ChatColor.DARK_BLUE + "Diamond Limite " , null));
        gui.setItem(23 , Item.createItemstack(Material.DIAMOND_PICKAXE , 1 , ChatColor.GREEN + "Hasty Boy" , null) );
       gui.setItem(30 , Item.createItemstack(Material.POTION , 1 , ChatColor.WHITE + "Final HEal" , null));
        if (itemStack == null) {
        }
        else if(itemStack.getType() == Material.DIAMOND && itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Scenarios")){

            player.openInventory(gui);
            }

        }



    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event) {

        Material material = event.getBlock().getType();
        Block block = event.getBlock();
        Location location = block.getLocation();

        switch (material) {
            case DIAMOND_ORE:
                block.getWorld().dropItem(location, Item.createItemstack(Material.DIAMOND, 1, null, null));
                break;

            case GOLD_ORE:
                block.getWorld().dropItem(location, Item.createItemstack(Material.GOLD_INGOT, 1, null, null));
                break;

            case IRON_ORE:
                block.getWorld().dropItem(location, Item.createItemstack(Material.IRON_INGOT, 1, null, null));
                break;
            case COAL_ORE:
                block.getWorld().dropItem(location, Item.createItemstack(Material.TORCH, 4, null, null));
                break;
        }

    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(EntityDeathEvent event) {
        //setcancelled impossible
        EntityType entityType = event.getEntityType();
        Entity entity = event.getEntity();
        Location location = entity.getLocation();

        switch (entityType) {
            case COW:
                entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_BEEF, 2, null, null));
                break;


            case PIG:
                entity.getWorld().dropItem(location, Item.createItemstack(Material.GRILLED_PORK, 2, null, null));
                break;


            case SHEEP:
                entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_MUTTON, 2, null, null));
                break;

            case CHICKEN:
                entity.getWorld().dropItem(location, Item.createItemstack(Material.COOKED_CHICKEN, 2, null, null));
        }

    }

}
