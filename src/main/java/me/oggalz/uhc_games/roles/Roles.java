package me.oggalz.uhc_games.roles;

import org.bukkit.entity.Player;

public abstract class Roles {

    public abstract String  messages();
    public abstract void powerRoles(Player player);

    @Override
    public abstract String toString();
}
