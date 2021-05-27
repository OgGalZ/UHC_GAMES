package me.oggalz.uhc_games.utils;


import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class InventoryClass {

    private final  int size;
    private final String label;

    public InventoryClass(int size, String label) {
        this.size = size;
        this.label = label;
    }

    public static Inventory createdInventory(int size , String label){
        return Bukkit.createInventory(null , size , label  );
    }
}
