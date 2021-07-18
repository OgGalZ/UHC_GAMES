package me.oggalz.uhc_games.races;

import me.oggalz.uhc_games.tasks.Teleportation;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Elfes extends Races {


    private String message;
    private final RacesManager racesManager;

    public Elfes(me.oggalz.uhc_games.player.Player player, RacesManager racesManager) {
        super(player);
        this.racesManager = racesManager;
    }

    @Override
    public void messages(Player player) {
        int n = 0;
        message = "Félicitations, vous êtes un elfe, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de speed pendant 1 minute 30 secondes.\n" +
                "effets grâce au colorant vert foncé:\n" +
                "Speed 1 ( 1min 30)\n";


        for (int i = 0; i < racesManager.getElfes().size(); i++) {
            for (String s : racesManager.getElfes()) {
                if (s.equalsIgnoreCase(player.getName())) {
                    n = i;
                    break;
                }
            }
        }
        player.sendMessage(ChatColor.BLUE + message);
        racesManager.getElfes().remove(n);
        if ((!(racesManager.getElfes().size() <= 1))) {
            player.sendMessage(ChatColor.BLUE + "Team : " + racesManager.getElfes().get(Teleportation.generate(0, racesManager.getElfes().size() - 1 )));
            racesManager.getElfes().add(player.getName());
        }else{
            racesManager.getElfes().add(player.getName());

        }

    }

    @Override
    public void power(Player player, me.oggalz.uhc_games.player.Player playerClass) {
        if (playerClass.isEnable()) {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.SPEED, 1800, 0);
            player.addPotionEffect(potionEffect);
            playerClass.setEnable(false);
        }
    }
}
