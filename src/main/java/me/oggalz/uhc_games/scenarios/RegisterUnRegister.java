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
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.swing.*;
import java.util.Arrays;

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

        if (inventory.contains(Material.DIAMOND_ORE) && inventory.getTitle().equalsIgnoreCase(ChatColor.BLUE + "Scenarios")) {
            if (action == ClickType.LEFT) {
                player.sendMessage(ChatColor.GREEN + "Activé ");
                switch (material) {
                    case WOOD_AXE:
                        Register(new Timber());
                    case DIAMOND_ORE:
                        Register(new DiamondLimite());
                    case APPLE:
                        Register(new VanillaPlus(main));
                    case DIAMOND_PICKAXE:
                        Register(new HastyBoy());
                    case IRON_INGOT:
                        Register(new CutClean());
                }
            } else if (action == ClickType.RIGHT) {
                player.sendMessage(ChatColor.RED + "Désactivé ");
                switch (material) {
                    case WOOD_AXE:
                        UnRegister(new Timber());
                    case DIAMOND_ORE:
                        UnRegister(new DiamondLimite());
                    case APPLE:
                        UnRegister(new VanillaPlus(main));
                    case DIAMOND_PICKAXE:
                        UnRegister(new HastyBoy());
                    case IRON_INGOT:
                        UnRegister(new CutClean());
                }
            }
        }
    }

    public void Register(Listener listener) {
        main.getServer().getPluginManager().registerEvents(listener, main);
    }

    public void UnRegister(Listener listener) {
        HandlerList.unregisterAll(listener);
    }
}




