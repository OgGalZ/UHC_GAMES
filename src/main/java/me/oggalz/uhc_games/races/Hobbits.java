package me.oggalz.uhc_games.races;

import me.oggalz.uhc_games.player.Player;
import me.oggalz.uhc_games.tasks.Teleportation;
import org.bukkit.ChatColor;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Hobbits extends Races {

    private String message;
    private final RacesManager racesManager;

    public Hobbits(Player player, RacesManager racesManager) {
        super(player);
        this.racesManager = racesManager;
    }

    @Override
    public void messages(org.bukkit.entity.Player player) {
        int n = 0;
        message = "Félicitations, vous êtes un Hobbit, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de régénération pendant 30 secondes.\n" +
                "effets grâce au colorant vert foncé:\n" +
                " régénération 1 (1 hp par 3s )(30s)\n";

        for (int i = 0; i < racesManager.getHobbits().size(); i++) {
            for (String s : racesManager.getHobbits()) {
                if (s.equalsIgnoreCase(player.getName())) {
                    n = i;
                }
            }
        }
        racesManager.getHobbits().remove(n);
        if (!(racesManager.getHobbits().size() <= 0)) {
            player.sendMessage(ChatColor.BLUE + "Team : " + racesManager.getHobbits().get(Teleportation.generate(0, racesManager.getHobbits().size() - 1)));
            racesManager.getHobbits().add(player.getName());
        }else{
            racesManager.getHobbits().add(player.getName());
        }

        player.sendMessage(ChatColor.BLUE + message);

    }

    @Override
    public void power(org.bukkit.entity.Player player, Player playerClass) {
        if (playerClass.isEnable()) {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.REGENERATION, 600, 0);
            player.addPotionEffect(potionEffect);
            playerClass.setEnable(false);
        }
    }
}
