package me.oggalz.uhc_games.scenarios;

import me.oggalz.uhc_games.Main;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class RegisterUnRegister implements Listener {
    private final Main main;
    private final List<Object> instances = new ArrayList<>();

    public RegisterUnRegister(Main main, CutClean cutClean, DiamondLimite diamondLimite, HastyBoy hastyBoy, Timber timber, VanillaPlus vanillaPlus) {
        this.main = main;
        instances.add(timber);
        instances.add(diamondLimite);
        instances.add(vanillaPlus);
        instances.add(hastyBoy);
        instances.add(cutClean);


    }

    @EventHandler
    public void Onclick(InventoryClickEvent event) {
        try {
            Inventory inventory = event.getInventory();
            Player player = (Player) event.getWhoClicked();
            Material material = event.getCurrentItem().getType();
            ClickType action = event.getClick();
            String enable = ChatColor.GREEN + "Vous venez d'activer ";
            String disable = ChatColor.RED + "Vous venez de désactiver";


            if (inventory.contains(Material.DIAMOND_ORE) && inventory.getTitle().equalsIgnoreCase(ChatColor.BLUE + "Scenarios")) {
                if (action == ClickType.LEFT && material != null) {

                    switch (material) {
                        case WOOD_AXE:
                            register((Listener) instances.get(0));
                            player.sendMessage(enable + "Timber");
                            break;
                        case DIAMOND_ORE:
                            register((Listener) instances.get(1));
                            player.sendMessage(enable + "Diamond Limite");
                            break;
                        case APPLE:
                            register((Listener) instances.get(2));
                            player.sendMessage(enable + "Vanilla");
                            break;
                        case DIAMOND_PICKAXE:
                            register((Listener) instances.get(3));
                            player.sendMessage(enable + "Hasty Boy");
                            break;
                        case IRON_INGOT:
                            register((Listener) instances.get(4));
                            player.sendMessage(enable + "Cut Clean");
                            break;

                        default:
                            break;
                    }

                }
                if (action == ClickType.RIGHT && material != null) {
                    switch (material) {
                        case WOOD_AXE:
                            HandlerList.unregisterAll((Listener) instances.get(0));
                            player.sendMessage(disable + " Timber");
                            break;
                        case DIAMOND_ORE:
                            HandlerList.unregisterAll((Listener) instances.get(1));
                            player.sendMessage(disable + " Diamond Limite");

                            break;
                        case APPLE:
                            HandlerList.unregisterAll((Listener) instances.get(2));
                            player.sendMessage(disable + " Vanilla");

                            break;
                        case DIAMOND_PICKAXE:
                            HandlerList.unregisterAll((Listener) instances.get(3));
                            player.sendMessage(disable + " Hasty Boy");
                            break;
                        case IRON_INGOT:
                            HandlerList.unregisterAll((Listener) instances.get(4));
                            player.sendMessage(disable + " Cut Clean");
                            break;

                        default:
                            break;
                    }
                }

            }
        } catch (NullPointerException e) {
        }


    }

    public void register(Listener listener) {
        main.getServer().getPluginManager().registerEvents(listener, main);
    }
}






