package me.oggalz.uhc_games.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class Item {

    private final Material material;
    private final int number;
    private String name;
    private String lore;

    public Item(Material material, int number, String name, String lore) {
        this.material = material;
        this.number = number;
        this.name = name;
        this.lore = lore;
    }

    public static  ItemStack createItemstack(Material material, int number , String name , String lore )  {
        List<String> list = Arrays.asList(lore) ;
        ItemStack itemstack = new ItemStack(material, number);
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(name);
        if(list.get(0 ) != null){
            itemMeta.setLore(list);
        }
        itemstack.setItemMeta(itemMeta);
        return itemstack;

    }


}
