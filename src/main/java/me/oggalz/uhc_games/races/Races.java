package me.oggalz.uhc_games.races;

import me.oggalz.uhc_games.player.Player;
import me.oggalz.uhc_games.player.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public abstract class Races {

    private final me.oggalz.uhc_games.player.Player player;

    public Races(me.oggalz.uhc_games.player.Player player) {
        this.player = player;
    }

    public abstract String messages();

    public abstract void power(org.bukkit.entity.Player player , Player playerClass);


}




