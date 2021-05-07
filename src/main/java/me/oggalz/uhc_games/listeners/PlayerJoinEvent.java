package me.oggalz.uhc_games.listeners;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class PlayerJoinEvent implements Listener {

    @EventHandler
    public void playerJoinEvent(org.bukkit.event.player.PlayerJoinEvent event){

        Player player = event.getPlayer();
        player.getInventory().addItem(new ItemStack(Material.WOOD_BUTTON ));

    }
}
