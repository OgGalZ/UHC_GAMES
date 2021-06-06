package me.oggalz.uhc_games.gui;


import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.scenarios.VanillaPlus;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class MainGui implements InventoryProvider {

    private final List<ItemStack> item = new ArrayList<>();


    public MainGui() {
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.DIAMOND, 1, ChatColor.RED + "Scenarios", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.BARRIER, 1, ChatColor.YELLOW + "Bordure", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.ARROW, 1, ChatColor.DARK_BLUE + "PVP", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.BOOK, 1, ChatColor.GREEN + "Roles", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.EMERALD_BLOCK, 1, ChatColor.GOLD + "Start", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.CHEST, 1, ChatColor.GRAY + "Inventaire", null));

    }


    public static final SmartInventory MainGUi = SmartInventory.builder()
            .id("MainGui")
            .provider(new MainGui())
            .size(4, 9)
            .title(ChatColor.RED + "Configuration")
            .closeable(true)
            .build();


    @Override
    public void init(Player player, InventoryContents contents) {

        contents.set(1, 3, ClickableItem.of(item.get(0),
                e -> ScenariosGui.scenarios.open(player)));

        contents.set(1, 4, ClickableItem.of(item.get(1),
                e -> player.sendMessage(ChatColor.GOLD + "You clicked on a potato.")));


        contents.set(1, 5, ClickableItem.of(item.get(2),
                e -> player.sendMessage(ChatColor.GOLD + "You clicked on a potato.")));


        contents.set(2, 3, ClickableItem.of(item.get(3),
                e -> player.sendMessage(ChatColor.GOLD + "You clicked on a potato.")));

        contents.set(2, 4, ClickableItem.of(item.get(4),
                e -> player.sendMessage(ChatColor.GOLD + "You clicked on a potato.")));


        contents.set(2, 5, ClickableItem.of(item.get(5),
                e -> player.sendMessage(ChatColor.GOLD + "You clicked on a potato.")));

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }


}





