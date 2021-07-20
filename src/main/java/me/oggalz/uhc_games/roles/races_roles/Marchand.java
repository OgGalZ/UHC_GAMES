package me.oggalz.uhc_games.roles.races_roles;

import me.oggalz.uhc_games.roles.Roles;
import me.oggalz.uhc_games.utils.Item;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class Marchand extends Roles {
    @Override
    public String messages() {
        return null;
    }

    @Override
    public void powerRoles(Player player) {
            player.getInventory().addItem(Item.createItemstack(Material.BOOK_AND_QUILL , 1 , ChatColor.GOLD + "Marchandise ", null));
    }

    @Override
    public String toString() {
        return "Marchand";
    }
}
