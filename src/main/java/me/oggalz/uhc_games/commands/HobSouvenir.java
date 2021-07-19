package me.oggalz.uhc_games.commands;

import me.oggalz.uhc_games.roles.RolesManagers;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class HobSouvenir implements CommandExecutor {

    private final RolesManagers rolesManagers;

    public HobSouvenir(RolesManagers rolesManagers) {
        this.rolesManagers = rolesManagers;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command sender, String label, String[] args) {
        if (label.equals("hob") && commandSender instanceof Player) {
            String message = ChatColor.DARK_BLUE + "Voici le rôle de la personne espionné : ";
            Player player = (Player) commandSender;
            if (args[0].equals("souvenir") && args.length == 2) {
                if (rolesManagers.getRolesPLayers().containsKey(player.getUniqueId())) {
                    if (rolesManagers.getRolesPLayers().get(player.getUniqueId()) == rolesManagers.getInstancesRoles().get("Sage")) {
                        for (Player x : Bukkit.getOnlinePlayers()) {
                            if (x.getName().equalsIgnoreCase(args[1])) {
                                if (rolesManagers.getRolesPLayers().containsKey(x.getUniqueId())) {
                                    player.sendMessage(message + rolesManagers.getRolesPLayers().get(x.getUniqueId()).toString());
                                } else {
                                    player.sendMessage(message + rolesManagers.getRolesPlayersWithoutRaces().get(x.getUniqueId()).toString());
                                }
                            }
                        }
                        return true;
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