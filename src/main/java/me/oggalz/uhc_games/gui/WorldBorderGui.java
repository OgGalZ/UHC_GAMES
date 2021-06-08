package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class WorldBorderGui implements InventoryProvider {


    public static final SmartInventory scenarios = SmartInventory.builder()
            .id("Bordure")
            .provider(new WorldBorderGui())
            .size(4, 9)
            .title(ChatColor.YELLOW + "Bordure")
            .closeable(true)
            .build();
    @Override
    public void init(Player player, InventoryContents contents) {

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
