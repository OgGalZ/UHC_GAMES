package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.oggalz.uhc_games.tasks.Pvp;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ItemsId;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class PvpGui implements InventoryProvider {

    private static int numbersGaps = 0;
    private static int timePvp = 0;
    public static final SmartInventory PvpGui = SmartInventory.builder()
            .id("PvpGui")
            .provider(new PvpGui())
            .size(4, 9)
            .title(ChatColor.GOLD + "PVP/GAP")
            .closeable(true)
            .build();

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.set(1, 2, ClickableItem.of(Item.getCustomTextureHead(ItemsId.ReduceRed.getId(), ChatColor.RED + "Reduce of 10 minutes"), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            this.timePvp -= 10;
        }));
        contents.set(1, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.ReduceOrange.getId(), ChatColor.GOLD + "Reduce of 1 minute "), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            this.timePvp -= 1;


        }));
        contents.set(1, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseGreen.getId(), ChatColor.GREEN + "Increase of 1 minute"), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            this.timePvp += 1;

        }));
        contents.set(1, 6, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseBlue.getId(), ChatColor.BLUE + "Increase of 10 minutes "), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            this.timePvp+= 10;


        }));
        contents.set(2, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.ReduceRed.getId(), ChatColor.RED + "-1 gap"), e -> {
            numbersGaps -= 1;
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);

        }));
        contents.set(2, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseBlue.getId(), ChatColor.BLUE + "+1 gap"), e -> {
            numbersGaps += 1;
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);


        }));
    }

    @Override
    public void update(Player player, InventoryContents contents) {
        if (getNumbersGaps() < 0) {
            player.sendMessage(ChatColor.GOLD + "Vous ne pouvez pas mettre un nombre négatif ");
            numbersGaps = 0;
        }

        if(getTimePvp() < 0){
            player.sendMessage(ChatColor.GOLD + "Vous ne pouvez pas mettre un nombre négatif ");
            this.timePvp = 0;
        }
        contents.set(1, 4, ClickableItem.of(Item.createItemstack(Material.DIAMOND_SWORD, 1, ChatColor.WHITE + "" + getTimePvp() + " "+ "minute(s)", null), e -> player.playSound(player.getLocation(), Sound.CLICK, 99, 2)));
        contents.set(2, 4, ClickableItem.of(Item.createItemstack(Material.GOLDEN_APPLE, getNumbersGaps(), null, null), e -> player.playSound(player.getLocation(), Sound.CLICK, 99, 2)));


    }

    public static int getNumbersGaps() {
        return numbersGaps;
    }

    public  static  int getTimePvp() {
        return timePvp;
    }

    public static void setTimePvp(int value) {
        timePvp = value;
    }

}


