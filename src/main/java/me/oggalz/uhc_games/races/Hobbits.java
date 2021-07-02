package me.oggalz.uhc_games.races;

import me.oggalz.uhc_games.player.Player;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Hobbits extends Races {

    private String message;

    public Hobbits(Player player) {
        super(player);
    }

    @Override
    public String messages() {
        message = "Félicitations, vous êtes un Hobbit, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de régénération pendant 30 secondes.\n" +
                "effets grâce au colorant vert foncé:\n" +
                " régénération 1 (1 hp par 3s )(30s)\n";

        return ChatColor.GOLD + message;
    }

    @Override
    public void power(org.bukkit.entity.Player player, Player playerClass) {
        if (playerClass.isEnable()) {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.REGENERATION, 600, 1);
            player.addPotionEffect(potionEffect);
            playerClass.setEnable(false);
        }
    }
}
