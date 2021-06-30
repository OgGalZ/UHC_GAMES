package me.oggalz.uhc_games.scenarios;

import me.oggalz.uhc_games.Main;
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
import java.util.*;

public class RegisterUnRegister implements Listener {
    private final Main main;
    private final Map<Material, Object> scenarios = new HashMap<>();

    public RegisterUnRegister(Main main, CutClean cutClean, DiamondLimite diamondLimite, HastyBoy hastyBoy, Timber timber, VanillaPlus vanillaPlus) {
        this.main = main;
        scenarios.put(Material.WOOD_AXE, timber);
        scenarios.put(Material.DIAMOND_ORE, diamondLimite);
        scenarios.put(Material.APPLE, vanillaPlus);
        scenarios.put(Material.DIAMOND_PICKAXE, hastyBoy);
        scenarios.put(Material.IRON_INGOT, cutClean);

    }

    @EventHandler
    public void Onclick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        Player player = (Player) event.getWhoClicked();
        ItemStack itemStack = event.getCurrentItem();
        ClickType action = event.getClick();
        String enable = ChatColor.GREEN + "Vous venez d'activer ";
        String disable = ChatColor.RED + "Vous venez de désactiver ";


            if (inventory.contains(Material.DIAMOND_ORE) && inventory.getTitle().equalsIgnoreCase(ChatColor.BLUE + "Scenarios")) {
                if (action == ClickType.LEFT) {

                    if (scenarios.containsKey(itemStack.getType() )) {
                        Object object = scenarios.get(itemStack.getType() );
                        register((Listener) object);
                        player.sendMessage(enable + object.toString());
                    }else if(itemStack.getType() == Material.BLAZE_ROD){
                        player.sendMessage(enable + "FinalHeal");
                    }

                }
                if (action == ClickType.RIGHT) {
                    if (scenarios.containsKey(itemStack.getType() )) {
                        Object object = scenarios.get(itemStack.getType() );
                        HandlerList.unregisterAll((Listener) object);
                        player.sendMessage(disable + object.toString());
                    }
                    else if(itemStack.getType() == Material.BLAZE_ROD){
                        player.sendMessage(disable + "FinalHeal");
                    }

                }
            }

        }


    public void register(Listener listener) {
        main.getServer().getPluginManager().registerEvents(listener, main);
    }

}

