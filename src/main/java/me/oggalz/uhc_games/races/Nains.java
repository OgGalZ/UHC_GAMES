package me.oggalz.uhc_games.races;

import me.oggalz.uhc_games.player.Player;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.spigotmc.Metrics;

import java.util.UUID;


public class Nains extends Races {

    private String message;

    public Nains(Player player) {
        super(player);
    }

    @Override
    public String messages() {
        message = "Félicitations, vous êtes un Nain, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de résistance pendant 1 minute.\n" +
                "effets grâce au colorant vert foncé:\n" +
                "Résistance 1 (1 minute)\n";
        return ChatColor.BLACK + message;
    }


    @Override
    public void power(org.bukkit.entity.Player player, Player playerClass) {
        if (playerClass.isEnable()) {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 1200, 0);
            player.addPotionEffect(potionEffect);
            playerClass.setEnable(false);
        }
    }
}
