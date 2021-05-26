package me.oggalz.uhc_games.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Item {

    private final Material material;
    private final int number;

    public Item(Material material, int number) {
        this.material = material;
        this.number = number;
    }

    public ItemStack createItemstack(Material material, int number) {
        ItemStack itemstack = new ItemStack(material, number);
        return itemstack;
    }

    public ItemMeta getItemMeta(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        return itemMeta;
    }
}
