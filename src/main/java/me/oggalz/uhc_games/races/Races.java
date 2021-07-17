package me.oggalz.uhc_games.races;

import me.oggalz.uhc_games.player.Player;


public abstract class Races {

    private final me.oggalz.uhc_games.player.Player player;

    public Races(me.oggalz.uhc_games.player.Player player) {
        this.player = player;
    }


    public abstract void messages(org.bukkit.entity.Player player);

    public abstract void power(org.bukkit.entity.Player player , Player playerClass);


}




