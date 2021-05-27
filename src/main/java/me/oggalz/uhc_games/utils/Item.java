package me.oggalz.uhc_games.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

    private final Material material;
    private final int number;
    private String name;

    public Item(Material material, int number, String name) {
        this.material = material;
        this.number = number;
        this.name = name;
    }

    public static  ItemStack createItemstack(Material material, int number , String name) {
        ItemStack itemstack = new ItemStack(material, number);
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemstack.setItemMeta(itemMeta);
        return itemstack;

    }


}
