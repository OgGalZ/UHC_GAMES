package me.oggalz.uhc_games.commands;

import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.races.RacesManager;
import me.oggalz.uhc_games.roles.RolesManagers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HobCamp implements CommandExecutor {

    private final RolesManagers rolesManagers;
    private final PlayerManager playerManager;
    private final RacesManager racesManager;

    public HobCamp(RolesManagers rolesManagers, PlayerManager playerManager, RacesManager racesManager) {
        this.rolesManagers = rolesManagers;
        this.playerManager = playerManager;
        this.racesManager = racesManager;
    }

    public boolean onCommand(CommandSender commandSender, Command sender, String label, String[] args) {
        if (label.equals("hob") && commandSender instanceof Player) {
            String message = ChatColor.DARK_BLUE + "La personne que vous avez espionnée est   ";
            Player player = (Player) commandSender;
            if (args[0].equals("camp") && args.length == 2) {
                if (rolesManagers.getRolesPLayers().containsKey(player.getUniqueId())) {
                    if (rolesManagers.getRolesPLayers().get(player.getUniqueId()) == rolesManagers.getInstancesRoles().get("Voyant")) {
                        me.oggalz.uhc_games.player.Player playerClass = playerManager.getPlayer(player.getUniqueId());
                        if (playerClass.isSpyPowerVoyant()) {
                            for (Player x : Bukkit.getOnlinePlayers()) {
                                if (x.getName().equalsIgnoreCase(args[1])) {
                                    playerClass.setSpyPowerVoyant(false);
                                    player.setMaxHealth(player.getMaxHealth() - 2);
                                    if (racesManager.containsUuid(x.getUniqueId())) {
                                        if (racesManager.getRaces(x.getUniqueId()) == racesManager.getRaces(player.getUniqueId())) {
                                            player.sendMessage(message + "dans votre camp.");
                                        } else {
                                            player.sendMessage(message + "pas dans votre camp.");
                                        }
                                    } else {
                                        player.sendMessage(message + "dans votre camp.");
                                    }
                                }
                            }
                            return true;
                        } else {
                            player.sendMessage(ChatColor.DARK_BLUE + "Vous avez dêja utilisé votre pouvoir, attendez le prochain épisode..");
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'effectuer cette action .");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'effectuer cette action .");

                }
            }
        }
        return false;
    }
}
