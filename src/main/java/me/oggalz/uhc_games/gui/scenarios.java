package me.oggalz.uhc_games.gui;

import me.oggalz.uhc_games.utils.InventoryClass;
import me.oggalz.uhc_games.utils.Item;
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
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class scenarios implements Listener {

    private boolean enable;

    public scenarios() {
        this.enable = false;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClick(InventoryClickEvent event) {

        ItemStack itemStack = event.getCurrentItem();
        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();

        if (itemStack == null) {

        } else if (itemStack.getType() == Material.DIAMOND && player.isOp() && itemStack.hasItemMeta()) {

            Inventory gui = InventoryClass.createdInventory(9 * 6, ChatColor.RED + "Scenarios");
            gui.setItem(21, Item.createItemstack(Material.WOOD, 1, "Timber", null));
            player.openInventory(gui);

            if(itemStack.getType() == Material.WOOD && player.isOp() && itemStack.hasItemMeta()){
              this.enable = true;
              player.sendMessage(ChatColor.DARK_PURPLE + "Vous venez d'activer le scénario Cut Clean");

            }

        }
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onBlockBreak(BlockBreakEvent event ) {

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

        if(!enable){
            event.setCancelled(false);
        }else{
            event.setCancelled(true);
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
