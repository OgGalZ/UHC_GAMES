package me.oggalz.uhc_games.teams;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum TeamColor {

    BLUE("bleu", ChatColor.BLUE, getMaterial() , DyeColor.BLUE),
    GREEN("vert", ChatColor.GREEN, getMaterial() , DyeColor.GREEN),
    RED("red", ChatColor.RED, getMaterial() , DyeColor.RED),
    YELLOW("jaune", ChatColor.YELLOW, getMaterial() , DyeColor.YELLOW);

    private final String name;
    private final ChatColor color;
    private final DyeColor dyeColor;

    TeamColor(String name, ChatColor color, Material material, DyeColor dyecolor ) {
        this.name = name;
        this.color = color;
        this.dyeColor = dyecolor;
    }

    public String getName() {
        return name;
    }

    public ChatColor getColor() {
        return color;
    }

    public static Material getMaterial() {
        return Material.WOOL;
    }
    public DyeColor getDyecolor(){
        return dyeColor;
    }

}


