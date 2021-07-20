package me.oggalz.uhc_games.races;

import me.oggalz.uhc_games.roles.RolesManagers;
import me.oggalz.uhc_games.tasks.Teleportation;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Orques extends Races {

    private String message;
    private final RacesManager racesManager;
    private final RolesManagers rolesManagers;

    public Orques(me.oggalz.uhc_games.player.Player player, RacesManager racesManager, RolesManagers rolesManagers) {
        super(player);
        this.racesManager = racesManager;
        this.rolesManagers = rolesManagers;
    }

    @Override
    public void messages(Player player) {
        int n = 0;
        message = "Félicitations, vous êtes un orque, vous recevez un colorant vert foncé quand vous faites clique droit avec, vous gagnez un effet de force pendant 2 minutes.\n" +
                "effets grâce au colorant vert foncé :\n" +
                "- Strength 1 (20%) (pendant 2 minute)" + ChatColor.RED + "Azog : " + rolesManagers.getPseudoAzog();

        for (int i = 0; i < racesManager.getOrques().size(); i++) {
            for (String s : racesManager.getOrques()) {
                if (s.equalsIgnoreCase(player.getName())) {
                    n = i;
                }
            }
        }
        racesManager.getOrques().remove(n);
        if (!(racesManager.getOrques().size() <= 1)) {
            int random1 = Teleportation.generate(0, racesManager.getOrques().size() - 1);
            int random2 = Teleportation.generate(0, racesManager.getOrques().size() - 1);
            if (racesManager.getOrques().size() == 2) {
                while (random2 == random1) {
                    random2 = Teleportation.generate(0, racesManager.getOrques().size() - 1);
                }
            }
            player.sendMessage(ChatColor.BLUE + "Team : " + racesManager.getOrques().get(random1) + racesManager.getOrques().get(random2));
            racesManager.getOrques().add(player.getName());
        } else {
            racesManager.getOrques().add(player.getName());

        }
        player.sendMessage(ChatColor.RED + message);


    }

    @Override
    public void power(Player player, me.oggalz.uhc_games.player.Player playerClass) {
        if (playerClass.isEnable()) {
            PotionEffect potionEffect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 2400, 0);
            player.addPotionEffect(potionEffect);
            playerClass.setEnable(false);
        }
    }

    @Override
    public String toString() {
        return "Orques";
    }
}


