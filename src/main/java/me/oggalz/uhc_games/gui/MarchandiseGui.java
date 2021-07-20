package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class MarchandiseGui implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.set(0, 2, ClickableItem.of(Item.createItemstack(Material.IRON_INGOT, 1, ChatColor.GOLD + "{15 fer pour 2 or} ", null), e -> {
            if (player.getInventory().contains(Material.IRON_INGOT, 15)) {
                delete(Material.IRON_INGOT, player, 15);
                player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 99, 99);
                player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, 2));
                player.sendMessage(ChatColor.DARK_BLUE + "Voilà votre marchandise !! ");
            }
        }));
        contents.set(1, 6, ClickableItem.of(Item.createItemstack(Material.GOLD_INGOT, 1, ChatColor.GOLD + "{10 or pour 2 diams} ", null), e -> {
                    if (player.getInventory().contains(Material.GOLD_INGOT, 10)) {

                        delete(Material.GOLD_INGOT, player, 10);
                        player.playSound(player.getLocation(), Sound.ANVIL_BREAK, 99, 99);
                        player.getInventory().addItem(new ItemStack(Material.DIAMOND, 2));
                        player.sendMessage(ChatColor.DARK_BLUE + "Voilà votre marchandise !! ");
                    }
                }
        ));
    }


    @Override
    public void update(Player player, InventoryContents contents) {

    }

    public void delete(Material material, Player player, int numberDeleted ) {
        int i = 0;
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (itemStack != null && itemStack.getType() == material) {
                i += itemStack.getAmount();
                player.getInventory().remove(itemStack);
            }
        }
        if(material == Material.IRON_INGOT){
            player.getInventory().addItem(new ItemStack(Material.IRON_INGOT, i - numberDeleted));

        }else{
            player.getInventory().addItem(new ItemStack(Material.GOLD_INGOT, i - numberDeleted));
        }

    }
}
