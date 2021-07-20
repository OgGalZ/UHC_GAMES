package me.oggalz.uhc_games.commands;

import me.oggalz.uhc_games.player.PlayerManager;
import me.oggalz.uhc_games.races.RacesManager;
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

public class RolesCommands implements CommandExecutor {

    private final RolesManagers rolesManagers;
    private final PlayerManager playerManager;
    private final RacesManager racesManager;
    private final Team team;
    private Player x;


    public RolesCommands(RolesManagers rolesManagers, PlayerManager playerManager, RacesManager racesManager, Team team) {
        this.rolesManagers = rolesManagers;
        this.playerManager = playerManager;
        this.racesManager = racesManager;
        this.team = team;
        x = null;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command sender, String label, String[] args) {
        if (label.equals("hob") && commandSender instanceof Player) {
            String message = ChatColor.DARK_BLUE + "La personne que vous avez espionnée est   ";
            String messAlreadyUse = ChatColor.DARK_BLUE + "Vous avez dêja utilisé votre pouvoir, attendez le prochain épisode..";
            String messServer = ChatColor.RED + "Vous n'avez pas la permission d'effectuer cette action .";
            String wrongCommand = ChatColor.BLUE + "Vous n'effectuez pas correctement la commande.";
            Player player = (Player) commandSender;
            if (rolesManagers.getRolesPLayers().containsKey(player.getUniqueId())) {
                if (rolesManagers.getRolesPLayers().get(player.getUniqueId()) == rolesManagers.getInstancesRoles().get("Voyant")) {
                    if (args[0].equals("camp") && args.length == 2) {
                        me.oggalz.uhc_games.player.Player playerClass = playerManager.getPlayer(player.getUniqueId());
                        if (playerClass.isSpyPowerVoyant()) {
                            if (findPseudoPlayer(args[1])) {
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


                                return true;
                            } else {
                                player.sendMessage(wrongCommand);
                            }
                        } else {
                            player.sendMessage(messAlreadyUse);
                        }
                    } else if (args[0].equals("race") && args.length == 2) {
                        if (findPseudoPlayer(args[1])) {
                            if (racesManager.containsUuid(x.getUniqueId())) {
                                player.sendMessage("Cette personne fait partie de la race des " + racesManager.getRaces(x.getUniqueId()).toString());
                            } else {
                                player.sendMessage("Cette personne fait partie de la race des " + racesManager.getRaces(player.getUniqueId()).toString());
                            }
                        } else {
                            player.sendMessage(wrongCommand);
                        }
                    } else {
                        player.sendMessage(wrongCommand);
                    }

                } else {
                    player.sendMessage(messServer);
                }
            } else if (args[0].equals("souvenir") && args.length == 2) {
                if (rolesManagers.getRolesPLayers().containsKey(player.getUniqueId())) {
                    if (rolesManagers.getRolesPLayers().get(player.getUniqueId()) == rolesManagers.getInstancesRoles().get("Sage")) {
                        me.oggalz.uhc_games.player.Player playerClass = playerManager.getPlayer(player.getUniqueId());
                        if (playerClass.isSpyCamp()) {
                            if (findPseudoPlayer(args[1])) {
                                playerClass.setSpyCamp(false);
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
                            } else {
                                player.sendMessage(wrongCommand);
                            }
                        } else {
                            player.sendMessage(messAlreadyUse);
                        }
                        return true;
                    } else {
                        player.sendMessage(messServer);
                    }
                } else {
                    player.sendMessage(messServer);
                }
            } else {
                player.sendMessage(wrongCommand);
            }

        }
        return false;
    }


    public boolean findPseudoPlayer(String args) {
        boolean check = false;

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getName().equalsIgnoreCase(args)) {
                x = player;
                check = true;
            }
        }
        return check;
    }

}
