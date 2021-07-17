package me.oggalz.uhc_games.races;

import me.oggalz.uhc_games.player.Player;
import me.oggalz.uhc_games.tasks.Teleportation;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Nains extends Races {

    private String message;
    private final RacesManager racesManager;

    public Nains(Player player, RacesManager racesManager) {
        super(player);
        this.racesManager = racesManager;
    }

    @Override
    public void messages(org.bukkit.entity.Player player) {
        int n = 0;
        message = "Félicitations, vous êtes un Nain, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de résistance pendant 1 minute.\n" +
                "effets grâce au colorant vert foncé:\n" +
                "Résistance 1 (1 minute)\n";

        for (int i = 0; i < racesManager.getNains().size(); i++) {
            for (String s : racesManager.getNains()) {
                if (s.equalsIgnoreCase(player.getName())) {
                    n = i;
                }
            }
        }
        racesManager.getNains().remove(n);
        player.sendMessage(ChatColor.BLACK + message);
        player.sendMessage(ChatColor.BLUE + "Team : " + racesManager.getNains().get(Teleportation.generate(0, racesManager.getNains().size())));
        racesManager.getNains().add(player.getName());
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
