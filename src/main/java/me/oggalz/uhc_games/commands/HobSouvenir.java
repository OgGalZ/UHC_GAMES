package me.oggalz.uhc_games.commands;

import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.roles.RolesManagers;
import me.oggalz.uhc_games.utils.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;


public class HobSouvenir implements CommandExecutor {

    private final RolesManagers rolesManagers;
    private final Team team;
    private final PlayerManager playerManager;

    public HobSouvenir(RolesManagers rolesManagers, Team team, PlayerManager playerManager) {
        this.rolesManagers = rolesManagers;
        this.team = team;
        this.playerManager = playerManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command sender, String label, String[] args) {
        if (label.equals("hob") && commandSender instanceof Player) {
            String message = ChatColor.DARK_BLUE + "Voici le rôle de la personne espionné : ";
            Player player = (Player) commandSender;
            if (args[0].equals("souvenir") && args.length == 2) {
                if (rolesManagers.getRolesPLayers().containsKey(player.getUniqueId())) {
                    if (rolesManagers.getRolesPLayers().get(player.getUniqueId()) == rolesManagers.getInstancesRoles().get("Sage")) {
                        me.oggalz.uhc_games.player.Player playerClass = playerManager.getPlayer(player.getUniqueId());
                        if (playerClass.isSpyCamp()) {
                            playerClass.setSpyCamp(false);
                            for (Player x : Bukkit.getOnlinePlayers()) {
                                if (x.getName().equalsIgnoreCase(args[1])) {
                                    player.setMaxHealth(player.getMaxHealth() - 3);
                                    List<Player> players = new ArrayList<>();
                                    players.add(player);
                                    players.add(x);
                                    if (!team.isSameTeam(players)) {
                                        x.setMaxHealth(x.getMaxHealth() + 2);
                                    }
                                    players.clear();
                                    if (rolesManagers.getRolesPLayers().containsKey(x.getUniqueId())) {
                                        player.sendMessage(message + rolesManagers.getRolesPLayers().get(x.getUniqueId()).toString());
                                    } else {
                                        player.sendMessage(message + rolesManagers.getRolesPlayersWithoutRaces().get(x.getUniqueId()).toString());
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