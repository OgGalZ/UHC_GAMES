package me.oggalz.uhc_games.roles.races_roles;

import me.oggalz.uhc_games.roles.Roles;
import me.oggalz.uhc_games.utils.Team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Tavernier extends Roles {

    private final Team team;
    private boolean potion1 = true;
    private boolean potion2 = true;

    public Tavernier(Team team) {
        this.team = team;
    }

    @Override
    public String messages() {

        String message = "Vous êtes Tavernier votre objectif et de gagner avec l’autre tavernier. Pour cela vous disposez de 20 cœurs à partager avec votre duo vous avez également l’effet “force ou résistance\"(1 a force l’autre à rési) et votre allié possède “ l’autre effet\".Néanmoins si votre duo meurt vous serez bloqué à 8 coeurs permanent mais vous aurez les effet force et résistance permanent.";
        return ChatColor.BLUE + message;
    }

    @Override
    public void powerRoles(Player player) {
        Bukkit.broadcastMessage("1");
        if (team.getTavernier1().containsKey(player)) {
            player.sendMessage(ChatColor.RED + "Votre duo est composée de " + team.getTavernier1().values());
            player.sendMessage(ChatColor.RED + "L'autre duo est " + team.getTavernier2().values());
            if (potion1) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 0));
                potion1 = false;
            } else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 0));
            }
        } else {
            player.sendMessage(ChatColor.RED + "Votre duo est composée de " + team.getTavernier2().values());
            player.sendMessage(ChatColor.RED + "L'autre duo est " + team.getTavernier1().values());
            if (potion2) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 0));
                potion2 = false;
            } else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 999999999, 0));
            }

        }
    }

    @Override
    public String toString() {
        return "Tavernier";
    }
}
