package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ItemsId;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;


public class PvpGui implements InventoryProvider {

    private  int numbersGaps = 0;
    private  int timePvp = 1;



    @Override
    public void init(Player player, InventoryContents contents) {
        contents.set(1, 2, ClickableItem.of(Item.getCustomTextureHead(ItemsId.ReduceRed.getId(), ChatColor.RED + "Reduce of 10 minutes" , 1), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            timePvp -= 10;
            if(getTimePvp() <= 1){
                timePvp = 1;
            }
        }));
        contents.set(1, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.ReduceOrange.getId(), ChatColor.GOLD + "Reduce of 1 minute " , 1), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            timePvp -= 1;
            if(getTimePvp() <= 1){
                timePvp = 1;
            }
        }));
        contents.set(1, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseGreen.getId(), ChatColor.GREEN + "Increase of 1 minute", 1), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            timePvp += 1;
        }));
        contents.set(1, 6, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseBlue.getId(), ChatColor.BLUE + "Increase of 10 minutes " , 1), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            timePvp+= 10;
        }));
        contents.set(2, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.ReduceRed.getId(), ChatColor.RED + "-1 gap" , 1), e -> {
            numbersGaps -= 1;

            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);

        }));
        contents.set(2, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.IncreaseBlue.getId(), ChatColor.BLUE + "+1 gap" , 1), e -> {
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


        contents.set(1, 4, ClickableItem.of(Item.createItemstack(Material.DIAMOND_SWORD, 1, ChatColor.WHITE + "" + getTimePvp() + " "+ "minute(s)", null), e -> player.playSound(player.getLocation(), Sound.CLICK, 99, 2)));
        contents.set(2, 4, ClickableItem.of(Item.createItemstack(Material.GOLDEN_APPLE, getNumbersGaps(), null, null), e -> player.playSound(player.getLocation(), Sound.CLICK, 99, 2)));


    }

    public  int getNumbersGaps() {
        return numbersGaps;
    }

    public   int getTimePvp() {
        return timePvp;
    }

}


