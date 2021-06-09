package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class ScenariosGui implements InventoryProvider {


    private static int x = 1;
    private final List<ItemStack> itemStacks = new ArrayList<>();

    public ScenariosGui() {
        itemStacks.add(Item.createItemstack(Material.WOOD_AXE, 1, ChatColor.GREEN + "Timber", null));
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

    }

    @Override
    public void update(Player player, InventoryContents contents) {
        ItemStack item = Item.createItemstack(Material.DIAMOND_ORE, getX(), ChatColor.BLUE + "Diamond limite", null);

        contents.set(1, 3, ClickableItem.of(itemStacks.get(0), e -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
                    if (e.isLeftClick()) {

                        itemStacks.get(0).addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);

                    } else if (e.isRightClick()) {
                        itemStacks.get(0).removeEnchantment(Enchantment.DAMAGE_UNDEAD);
                    }
                }
        ));
        contents.set(1, 4, ClickableItem.of(item, e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                x += 1;

            } else if (e.isRightClick()) {

                if (!(x <= 0)) {
                    x -= 1;
                }
            }
        }));


        contents.set(1, 5, ClickableItem.of(itemStacks.get(1), e -> {
            if (e.isLeftClick()) {
                itemStacks.get(1).addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);

            } else if (e.isRightClick()) {
                itemStacks.get(1).removeEnchantment(Enchantment.DAMAGE_UNDEAD);

            }
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);

        }));


        contents.set(2, 3, ClickableItem.of(itemStacks.get(2), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                itemStacks.get(2).addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);

            } else if (e.isRightClick()) {
                itemStacks.get(2).removeEnchantment(Enchantment.DAMAGE_UNDEAD);

            }
        }));


        contents.set(2, 4, ClickableItem.of(itemStacks.get(3), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);

            if (e.isLeftClick()) {
                itemStacks.get(3).addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);

            } else if (e.isRightClick()) {
                itemStacks.get(3).removeEnchantment(Enchantment.DAMAGE_UNDEAD);

            }
        }));


        contents.set(2, 5, ClickableItem.of(itemStacks.get(4), e -> {
            if (e.isLeftClick()) {
                itemStacks.get(4).addUnsafeEnchantment(Enchantment.DAMAGE_UNDEAD, 1);

            } else if (e.isRightClick()) {
                itemStacks.get(4).removeEnchantment(Enchantment.DAMAGE_UNDEAD);

            }
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);

        }));

    }

    public static int getX() {
        return x;
    }


}
