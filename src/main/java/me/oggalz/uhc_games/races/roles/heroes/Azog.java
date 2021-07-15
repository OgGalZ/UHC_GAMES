package me.oggalz.uhc_games.races.roles.heroes;

import me.oggalz.uhc_games.races.roles.Roles;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Azog extends Roles {

    private String rolesMessage;

    @Override
    public String messages() {
        rolesMessage = "Vous êtes Azog , votre objectif est de gagner avec les orques. Pour cela vous disposez de l’effet force 1 permanent. Si vous êtes en possession de l’anneau unique, tous les orques gagneront 2 cœurs. Vous êtes connue de tous les orques, mais vous ne connaissez personne.\n";
        return ChatColor.ITALIC + rolesMessage;
    }

    @Override
    public void powerRoles(Player player) {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE , 999999999, 0);
        player.addPotionEffect(potionEffect);
    }
}
