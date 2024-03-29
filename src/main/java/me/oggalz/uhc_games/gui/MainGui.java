package me.oggalz.uhc_games.gui;


import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.tasks.Teleportation;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ItemsId;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class MainGui implements InventoryProvider {


    private final List<ItemStack> item = new ArrayList<>();
    private final SmartInventory pvpGui;
    private final SmartInventory scenarioGui;
    private final SmartInventory worldBorder;
    private boolean enable;
    private final Main main;
    private final Teleportation teleportation;
    private final SmartInventory racesGui;
    private final RolesGui races;

    public MainGui(SmartInventory pvpGui, SmartInventory scenarioGui, SmartInventory worldBorder,  Main main, Teleportation teleportation, SmartInventory racesGui, RolesGui races) {
        this.main = main;
        this.teleportation = teleportation;
        this.racesGui = racesGui;
        this.races = races;
        enable = false;
        this.pvpGui = pvpGui;
        this.scenarioGui = scenarioGui;
        this.worldBorder = worldBorder;
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.DIAMOND, 1, ChatColor.RED + "Scenarios", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.BARRIER, 1, ChatColor.YELLOW + "Bordure", null));
        item.add(Item.getCustomTextureHead(ItemsId.SauronEye.getId(), ChatColor.DARK_AQUA + "PVP" , 1));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.BOOK, 1, ChatColor.GREEN + "Roles", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.EMERALD_BLOCK, 1, ChatColor.GOLD + "Start", null));
        item.add(me.oggalz.uhc_games.utils.Item.createItemstack(Material.CHEST, 1, ChatColor.GRAY + "Inventaire", null));
    }

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.set(1, 3, ClickableItem.of(item.get(0), e -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
                    scenarioGui.open(player);
                }

        ));

        contents.set(1, 4, ClickableItem.of(item.get(1), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            worldBorder.open(player);
        }));


        contents.set(1, 5, ClickableItem.of(item.get(2), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            pvpGui.open(player);
        }));


        contents.set(2, 3, ClickableItem.of(item.get(3), e -> {
                    player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
                    racesGui.open(player);
                }
        ));

        contents.set(2, 4, ClickableItem.of(item.get(4), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if((races.resultsRaces() * 4L) +  (races.resultsRoles() + 4) == (long) Bukkit.getOnlinePlayers().size()  ){
                if (!enable) {
                    enable = true;
                    for(Player x : Bukkit.getOnlinePlayers()){
                        x.getInventory().clear();;
                        x.closeInventory();
                    }
                    teleportation.runTaskTimer(main, 0, 20L);
                }
            }else{
                player.sendMessage(ChatColor.DARK_BLUE + "Le nombre de rôles correspond pas avec le nombre de joueurs .");
            }

        }));


        contents.set(2, 5, ClickableItem.of(item.get(5), e -> {
                    player.sendMessage(ChatColor.DARK_PURPLE + "Vous pouvez créer un inventaire de départ qui sera commun à tous les joueurs lors du lancement de la partie !");
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







