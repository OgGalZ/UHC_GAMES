package me.oggalz.uhc_games.scenarios;

import me.oggalz.uhc_games.Main;
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
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class RegisterUnRegister implements Listener {
    private final Main main;
    private final CutClean cutClean;
    private final DiamondLimite diamondLimite;
    private final HastyBoy hastyBoy;
    private final Timber timber;
    private final VanillaPlus vanillaPlus;
    private final List<Object> instances = new ArrayList<>();

    public RegisterUnRegister(Main main, CutClean cutClean, DiamondLimite diamondLimite, HastyBoy hastyBoy, Timber timber, VanillaPlus vanillaPlus) {
        this.main = main;
        this.cutClean = cutClean;
        this.diamondLimite = diamondLimite;
        this.hastyBoy = hastyBoy;
        this.timber = timber;
        this.vanillaPlus = vanillaPlus;
        instances.add(cutClean);
        instances.add(diamondLimite);
        instances.add(hastyBoy);
        instances.add(timber);
        instances.add(vanillaPlus);

    }

    @EventHandler
    public void Onclick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        Material material = event.getCurrentItem().getType();
        ClickType action = event.getClick();


        if (inventory.contains(Material.DIAMOND_ORE) && inventory.getTitle().equalsIgnoreCase(ChatColor.BLUE + "Scenarios") && material != null) {
            if (action == ClickType.LEFT) {
                player.sendMessage(ChatColor.GREEN + "Activé ");
                switch (material) {
                    case WOOD_AXE:
                        register((Listener) instances.get(0));
                        break;
                    case DIAMOND_ORE:
                        register((Listener) instances.get(1));
                        break;
                    case APPLE:
                        register((Listener) instances.get(2));
                        break;
                    case DIAMOND_PICKAXE:
                        register((Listener) instances.get(3));
                        break;
                    case IRON_INGOT:
                        register((Listener) instances.get(4));

                        break;

                    default:
                        break;
                }

            }
            if (action == ClickType.RIGHT) {
                player.sendMessage(ChatColor.RED + "Désactivé ");
                switch (material) {
                    case WOOD_AXE:
                        HandlerList.unregisterAll((Listener) instances.get(0));
                        break;
                    case DIAMOND_ORE:
                        HandlerList.unregisterAll((Listener) instances.get(1));

                        break;
                    case APPLE:
                        HandlerList.unregisterAll((Listener) instances.get(2));

                        break;
                    case DIAMOND_PICKAXE:
                        HandlerList.unregisterAll((Listener) instances.get(3));

                    case IRON_INGOT:
                        HandlerList.unregisterAll((Listener) instances.get(4));

                        break;

                    default:
                        break;
                }
            }

        }


    }

    public void register(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, main);
    }
}






