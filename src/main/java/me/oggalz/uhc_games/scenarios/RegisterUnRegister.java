package me.oggalz.uhc_games.scenarios;

import fr.minuskube.inv.SmartInventory;
import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.ScenariosGui;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;

import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegisterUnRegister implements Listener {

    private final Main main;

    public RegisterUnRegister(Main main) {
        this.main = main;
    }

    @EventHandler
    public void Onclick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        Material material = event.getCurrentItem().getType();
        ClickType action = event.getClick();
        DiamondLimite diamondLimite = new DiamondLimite();
        Timber timber = new Timber();
        VanillaPlus vanillaPlus = new VanillaPlus(main);
        HastyBoy hastyBoy = new HastyBoy();
        CutClean cutClean = new CutClean();

        if (inventory.contains(Material.DIAMOND_ORE) && inventory.getTitle().equalsIgnoreCase(ChatColor.BLUE + "Scenarios")) {
            if (action == ClickType.LEFT) {
                player.sendMessage(ChatColor.GREEN + "Activé ");
                switch (material) {
                    case WOOD_AXE:
                        main.getServer().getPluginManager().registerEvents(timber, main);
                    case DIAMOND_ORE:
                        main.getServer().getPluginManager().registerEvents(diamondLimite, main);
                    case APPLE:
                        main.getServer().getPluginManager().registerEvents(vanillaPlus, main);
                    case DIAMOND_PICKAXE:
                        main.getServer().getPluginManager().registerEvents(hastyBoy, main);
                    case IRON_INGOT:
                        main.getServer().getPluginManager().registerEvents(cutClean, main);
                }
            }/* else if (action == ClickType.RIGHT  ) {
                player.sendMessage(ChatColor.RED + "Désactivé ");
                switch (material) {
                    case WOOD_AXE:
                    case DIAMOND_ORE:
                      //  DiamondLimite.unregisterAll();
                    case APPLE:
                      //  VanillaPlus.unregisterAll();
                    case DIAMOND_PICKAXE:
                 //       HastyBoy.unregisterAll();
                    case IRON_INGOT:
                 //       CutClean.unregisterAll();
                }
            }
        }
    }*/


        }


    }

}