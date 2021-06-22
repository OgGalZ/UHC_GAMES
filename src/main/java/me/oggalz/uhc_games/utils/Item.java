package me.oggalz.uhc_games.utils;

import com.mojang.authlib.GameProfile;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import com.mojang.authlib.properties.Property;


import java.lang.reflect.Field;
import java.util.*;

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

    public static ItemStack createItemstack(Material material, int number, String name, String lore) {
        List<String> list = Collections.singletonList(lore);
        ItemStack itemstack = new ItemStack(material, number);
        ItemMeta itemMeta = itemstack.getItemMeta();
        itemMeta.setDisplayName(name);
        if (list.get(0) != null) {
            itemMeta.setLore(list);
        }
        itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        itemstack.setItemMeta(itemMeta);
        return itemstack;

    }



    public  static  ItemStack getCustomTextureHead(String value , String name) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta) head.getItemMeta();
        meta.setDisplayName(name);
        GameProfile profile = new GameProfile(UUID.randomUUID(), "");
        profile.getProperties().put("textures", new Property("textures", value));
        Field profileField = null;
        try {
            profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);
        } catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
            e.printStackTrace();
        }
        head.setItemMeta(meta);
        return head;
    }
    public static void clearArmor(Player player) {
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
    }
}