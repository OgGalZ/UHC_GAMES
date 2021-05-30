package me.oggalz.uhc_games.gui.scenarios;

import com.sun.java.accessibility.util.GUIInitializedListener;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.UniversalMaterial;
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
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.omg.PortableInterceptor.ACTIVE;

import java.util.List;

public class CutClean implements Listener {


    @EventHandler(priority = EventPriority.NORMAL)
    public void onClick(InventoryClickEvent event) {

        ItemStack itemStack = event.getCurrentItem();
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        InventoryAction action = event.getAction();
        Inventory gui = Bukkit.createInventory(null, 9 * 4, ChatColor.DARK_AQUA + "Scenarios");
        gui.setItem(11, Item.createItemstack(Material.IRON_INGOT, 1, ChatColor.RED + "Cut Clean", null));
        gui.setItem(15, Item.createItemstack(Material.APPLE, 1, ChatColor.BLUE + "Vanilla+", null));
        gui.setItem(21, Item.createItemstack(Material.DIAMOND_ORE, 1, ChatColor.DARK_BLUE + "Diamond Limite ", null));
        gui.setItem(23, Item.createItemstack(Material.DIAMOND_PICKAXE, 1, ChatColor.GREEN + "Hasty Boy", null));
        gui.setItem(31, Item.createItemstack(Material.POTION, 1, ChatColor.WHITE + "Final HEal", null));
        if (itemStack == null) {
        } else if (itemStack.getType() == Material.DIAMOND && itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.RED + "Scenarios")) {

            player.openInventory(gui);
        }
    }

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

