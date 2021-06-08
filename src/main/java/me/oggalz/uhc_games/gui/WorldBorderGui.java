package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ItemsId;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WorldBorderGui implements InventoryProvider {


    public static final SmartInventory bordure = SmartInventory.builder()
            .id("Bordure")
            .provider(new WorldBorderGui())
            .size(4, 9)
            .title(ChatColor.YELLOW + "Bordure")
            .closeable(true)
            .build();

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.set(1, 2, ClickableItem.of(Item.getCustomTextureHead((ItemsId.ReduceRed.getId()), ChatColor.RED + "Reduce of 200 blocks"), e -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 99, 2);

                }

        ));


        contents.set(1, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.ReduceOrange.getId(), ChatColor.GOLD + " Reduce of 50 blocks"), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);

        }));

        contents.set(1, 4, ClickableItem.of(Item.getCustomTextureHead(ItemsId.InitialBorder.getId(), ChatColor.GREEN + ""), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);


        }));
        contents.set(1, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseGreen.getId(), ChatColor.GREEN + "Increase of 50 blocks"), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);


        }));


        contents.set(1, 6, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseBlue.getId(), ChatColor.BLUE + "Increase of 200 blocks"), e -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 99, 2);


                }

        ));
        contents.set(2, 2, ClickableItem.of(Item.getCustomTextureHead((ItemsId.ReduceRed.getId()), ChatColor.RED + "Reduce of 200 blocks"), e -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 99, 2);

                }

        ));


        contents.set(2, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.ReduceOrange.getId(), ChatColor.GOLD + " Reduce of 50 blocks"), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);

        }));

        contents.set(2, 4, ClickableItem.of(Item.getCustomTextureHead(ItemsId.TimeBorderReduction.getId(), ChatColor.GREEN + ""), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);


        }));
        contents.set(2, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseGreen.getId(), ChatColor.GREEN + "Increase of 50 blocks"), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);


        }));


        contents.set(2, 6, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseBlue.getId(), ChatColor.BLUE + "Increase of 200 blocks"), e -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 99, 2);


                }

        ));

    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
