package me.oggalz.uhc_games.races;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Elfes extends Races {


    private String message;

    public Elfes(me.oggalz.uhc_games.player.Player player) {
        super(player);
    }

    @Override
    public String messages() {
        message = "Félicitations, vous êtes un elfe, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de speed pendant 1 minute 30 secondes.\n" +
                "effets grâce au colorant vert foncé:\n" +
                "Speed 1 ( 1min 30)\n";
        return ChatColor.BLUE + message;
    }

    @Override
    public void power(Player player, me.oggalz.uhc_games.player.Player playerClass) {
        if (playerClass.isEnable()) {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED , 1800, 0);
            player.addPotionEffect(potionEffect);
            playerClass.setEnable(false);
        }
    }
}
