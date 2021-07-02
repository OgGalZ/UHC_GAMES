package me.oggalz.uhc_games.races;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Orques extends Races {

    private String message;

    public Orques(me.oggalz.uhc_games.player.Player player) {
        super(player);
    }

    @Override
    public String messages() {
        message = "Félicitations, vous êtes un orque, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de force pendant 2 minutes.\n" +
                "effets grâce au colorant vert foncé :\n" +
                "- Strength 1 (20%) (pendant 2 minute)";
        return ChatColor.RED + message;
    }

    @Override
    public void power(Player player, me.oggalz.uhc_games.player.Player playerClass) {
        if (playerClass.isEnable()) {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2400, 1);
            player.addPotionEffect(potionEffect);
            playerClass.setEnable(false);
        }
    }
}


