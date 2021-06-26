package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ItemsId;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class WorldBorderGui implements InventoryProvider {

    private static int borderSize = 200;
    private static int timeBorder = 0;


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

                    borderSize = borderSize - 200;
                }

        ));


        contents.set(1, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.ReduceOrange.getId(), ChatColor.GOLD + " Reduce of 50 blocks"), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (getBorderSize() < 0) {
                return;
            }
            borderSize = borderSize - 50;

        }));


        contents.set(1, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseGreen.getId(), ChatColor.GREEN + "Increase of 50 blocks"), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            borderSize += 50;


        }));


        contents.set(1, 6, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseBlue.getId(), ChatColor.BLUE + "Increase of 200 blocks"), e -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
                    borderSize += 200;

                }

        ));
        contents.set(2, 2, ClickableItem.of(Item.getCustomTextureHead((ItemsId.ReduceRed.getId()), ChatColor.RED + "Reduce of 10 minutes"), e -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
                    timeBorder -= 10;
                }

        ));


        contents.set(2, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.ReduceOrange.getId(), ChatColor.GOLD + " Reduce of 1 minute"), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            timeBorder -= 1;

        }));


        contents.set(2, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseGreen.getId(), ChatColor.GREEN + "Increase of 1 minute"), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            timeBorder += 1;


        }));


        contents.set(2, 6, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseBlue.getId(), ChatColor.BLUE + "Increase of 10 minutes"), e -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
                    timeBorder += 10;

                }

        ));

    }

    @Override
    public void update(Player player, InventoryContents contents) {
        if (getBorderSize() <= 125) {
            player.sendMessage(ChatColor.DARK_AQUA + "Vous ne pouvez pas mettre la taille de la bordure inférieur à celle initial !");
            borderSize = 200;
        }
        if (getTimeBorder() <0) {
            player.sendMessage(ChatColor.DARK_AQUA + "Vous ne pouvez pas mettre un temps négatif!");
            timeBorder = 0;
        }
        contents.set(1, 4, ClickableItem.of(Item.getCustomTextureHead(ItemsId.InitialBorder.getId(), ChatColor.GREEN + "Initial Border" + ChatColor.WHITE + " : " + getBorderSize()), e -> player.playSound(player.getLocation(), Sound.CLICK, 99, 2)));
        contents.set(2, 4, ClickableItem.of(Item.getCustomTextureHead(ItemsId.TimeBorderReduction.getId(), ChatColor.DARK_GRAY + "TimeReduction" + " : " + getTimeBorder() + " minute(s)"), e -> player.playSound(player.getLocation(), Sound.CLICK, 99, 2)));

    }

    public static int getBorderSize() {
        return borderSize;
    }

    public static int getTimeBorder() {
        return timeBorder;
    }

    public static void setBorderSize(int borderSize) {
        WorldBorderGui.borderSize = borderSize;
    }

    public static void setTimeBorder(int timeBorder) {
        WorldBorderGui.timeBorder = timeBorder;
    }
}
