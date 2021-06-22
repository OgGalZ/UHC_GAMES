package me.oggalz.uhc_games.gui;


import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;

import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ItemsId;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class MainGui implements InventoryProvider {

    private final List<ItemStack> item = new ArrayList<>();
    public MainGui() {
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.DIAMOND, 1, ChatColor.RED + "Scenarios", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.BARRIER, 1, ChatColor.YELLOW + "Bordure", null));
        item.add(Item.getCustomTextureHead(ItemsId.SauronEye.getId(), ChatColor.DARK_AQUA + "PVP"));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.BOOK, 1, ChatColor.GREEN + "Roles", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.EMERALD_BLOCK, 1, ChatColor.GOLD + "Start", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.CHEST, 1, ChatColor.GRAY + "Inventaire", null));

    }


    public  static final SmartInventory MainGUi = SmartInventory.builder()
            .id("MainGui")
            .provider(new MainGui())
            .size(4, 9)
            .title(ChatColor.RED + "Configuration")
            .closeable(true)
            .build();


    @Override
    public void init(Player player, InventoryContents contents) {

        contents.set(1, 3, ClickableItem.of(item.get(0), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
                ScenariosGui.scenarios.open(player);
                }

        ));

        contents.set(1, 4, ClickableItem.of(item.get(1), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            WorldBorderGui.bordure.open(player);
        }));


        contents.set(1, 5, ClickableItem.of(item.get(2), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            PvpGui.PvpGui.open(player);
        }));


        contents.set(2, 3, ClickableItem.of(item.get(3), e -> {

                }

        ));

        contents.set(2, 4, ClickableItem.of(item.get(4), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);

        }));


        contents.set(2, 5, ClickableItem.of(item.get(5), e -> {
                    player.sendMessage(ChatColor.DARK_PURPLE + "Vous pouvez créer un inventaire de départ qui sera commun à tous les joueurs lors du lancement de la partie !" );
                    player.sendMessage(ChatColor.GOLD + "/finish quand l'inventaire est complet :) ");
                    player.setGameMode(GameMode.CREATIVE);
                    player.getInventory().clear();
                }
        ));


    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }


}







