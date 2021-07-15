package me.oggalz.uhc_games.roles.heroes;

import me.oggalz.uhc_games.roles.Roles;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Thorin extends Roles {

    private String rolesMessage;

    @Override
    public String messages() {
        rolesMessage = "Vous êtes Thorin , votre objectif est de gagner avec la compagnie de Thorin.Pour cela vous disposez de l’effet résistance 1 permanent. Si vous êtes en possession de l’anneau unique, tous les nains gagneront 2 cœurs. Vous connaissez les trois autres héros de la compagnie de thorin. \n";
        return ChatColor.ITALIC + rolesMessage;
    }


    @Override
    public void powerRoles(Player player) {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999 ,  0);
        player.addPotionEffect(potionEffect);
    }
}
