package me.oggalz.uhc_games.scenarios;

import me.oggalz.uhc_games.Main;
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

public abstract class RegisterUnRegister implements Listener {

    protected final Main main;

    public RegisterUnRegister(Main main) {
        this.main = main;
    }

    @EventHandler
    public void Register(InventoryClickEvent event, Listener listener) {
        Inventory inventory = event.getInventory();
        ClickType action = event.getClick();
        main.getServer().getPluginManager().registerEvents(this, main);


        if (inventory.contains(Material.DIAMOND_ORE) && inventory.getTitle().equalsIgnoreCase(ChatColor.BLUE + "Scenarios")) {
            if (action == ClickType.RIGHT) {
                HandlerList.unregisterAll(listener);
            } else if (action == ClickType.LEFT) {
                main.getServer().getPluginManager().registerEvents(listener, main);
            }


        }

    }



}
