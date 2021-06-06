package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.oggalz.uhc_games.scenarios.RegisterUnRegister;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ScenariosGui implements InventoryProvider {


    private static int x = 1;
    private final List<ItemStack> itemStacks = new ArrayList<>();


    public ScenariosGui() {
        itemStacks.add(Item.createItemstack(Material.WOOD_AXE, 1, ChatColor.GREEN + "Timber", null));
        itemStacks.add(Item.createItemstack(Material.DIAMOND_ORE, 1, ChatColor.BLUE + "Diamond limite", null));
        itemStacks.add(Item.createItemstack(Material.APPLE, 1, ChatColor.GOLD + "Vanilla+", null));
        itemStacks.add(Item.createItemstack(Material.DIAMOND_PICKAXE, 1, ChatColor.GRAY + "Hasty Boy", null));
        itemStacks.add(Item.createItemstack(Material.POTION, 1, ChatColor.RED + "Final Heal", null));
        itemStacks.add(Item.createItemstack(Material.IRON_INGOT, 1, ChatColor.DARK_AQUA + "Cut Clean", null));


    }

    public static final SmartInventory scenarios = SmartInventory.builder()
            .id("scenarios")
            .provider(new ScenariosGui())
            .size(4, 9)
            .title(ChatColor.BLUE + "Scenarios")
            .closeable(true)
            .build();


    @Override
    public void init(Player player, InventoryContents contents) {


        contents.set(1, 3, ClickableItem.of(itemStacks.get(0), e -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
                    if(e.isLeftClick() ){

                    }

                }
        ));


        contents.set(1, 5, ClickableItem.of(itemStacks.get(2), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);

        }));


        contents.set(2, 3, ClickableItem.of(itemStacks.get(3),
                e -> player.playSound(player.getLocation(), Sound.CLICK, 99, 2)));


        contents.set(2, 4, ClickableItem.of(itemStacks.get(4),
                e -> player.playSound(player.getLocation(), Sound.CLICK, 99, 2)));


        contents.set(2, 5, ClickableItem.of(itemStacks.get(5),
                e -> player.playSound(player.getLocation(), Sound.CLICK, 99, 2)));


    }

    @Override
    public void update(Player player, InventoryContents contents) {


        contents.set(1, 4, ClickableItem.of(itemStacks.get(1), e -> {

            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isRightClick()) {
                x += 1;
                if (x >= 0) {
                    contents.set(1, 4, ClickableItem.empty(Item.createItemstack(Material.DIAMOND_ORE, x, null, null)));
                }
            } else {
                this.x -= 1;

                if (x >= 0) {
                    contents.set(1, 4, ClickableItem.empty(Item.createItemstack(Material.DIAMOND_ORE, x, null, null)));
                }
            }
        }));

    }

    public static int getX() {
        return x;
    }


    public void test() {

    }
}
