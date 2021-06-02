package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import fr.minuskube.inv.content.SlotIterator;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class GCutClean implements InventoryProvider {

    private final List<ItemStack> itemStacks = new ArrayList<>();

    public GCutClean() {
        itemStacks.add(Item.createItemstack(Material.WOOD_AXE, 1, ChatColor.GREEN + "Timber", null));
        itemStacks.add(Item.createItemstack(Material.DIAMOND_ORE, 1, ChatColor.BLUE + "Diamond limite", null));
        itemStacks.add(Item.createItemstack(Material.APPLE, 1, ChatColor.GOLD + "Vanilla+", null));
        itemStacks.add(Item.createItemstack(Material.DIAMOND_PICKAXE, 1, ChatColor.GRAY + "Hasty Boy", null));
        itemStacks.add(Item.createItemstack(Material.POTION, 1, ChatColor.RED + "Final Heal", null));
        itemStacks.add(Item.createItemstack(Material.IRON_INGOT, 1, ChatColor.DARK_AQUA + "Cut Clean", null));
    }

    public static final SmartInventory scenarios = SmartInventory.builder()
            .id("scenarios")
            .provider(new GCutClean())
            .size(4, 9)
            .title(ChatColor.BLUE + "Scenarios")
            .closeable(true)
            .build();


    @Override
    public void init(Player player, InventoryContents contents) {

        contents.set(1, 3, ClickableItem.empty(itemStacks.get(0)));
        contents.set(1, 4, ClickableItem.empty(itemStacks.get(1)));
        contents.set(1, 5, ClickableItem.empty(itemStacks.get(2)));
        contents.set(2, 3, ClickableItem.empty(itemStacks.get(3)));
        contents.set(2, 4, ClickableItem.empty(itemStacks.get(4)));
        contents.set(2, 5, ClickableItem.empty(itemStacks.get(5)));


    }

    @Override
    public void update(Player player, InventoryContents contents) {


    }
}
