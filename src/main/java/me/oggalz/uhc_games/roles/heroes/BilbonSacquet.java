package me.oggalz.uhc_games.roles.heroes;

import me.oggalz.uhc_games.roles.Roles;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BilbonSacquet extends Roles {

    private String rolesMessage;

    @Override
    public String messages() {
        rolesMessage = "Vous êtes Bilbon Sacquet , votre objectif est de gagner avec la compagnie de Thorin. Pour cela vous disposez de l’effet régénération 1 permanent la régénération regen 1hp toutes les 8 secondes. Si vous êtes en possession de l’anneau unique, tous les hobbits gagneront 2 cœurs.  Vous connaissez les trois autres héros de la compagnie de thorin. \n";

        return ChatColor.ITALIC + rolesMessage;
    }

    @Override
    public void powerRoles(Player player) {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.REGENERATION , 999999999 , 0);
        player.addPotionEffect(potionEffect);
    }

    @Override
    public String toString() {
        return "BilbonSacquet";
    }
}
