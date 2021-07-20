package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.oggalz.uhc_games.roles.*;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ItemsId;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;


public class RolesGui implements InventoryProvider {

    private int nMarchand = 0;
    private int nSage = 0;
    private int nVoyant = 0;
    private int nGarde = 0;
    private int nChasseur = 0;
    private int nNazgûl = 0;
    private int nTavernier = 0;
    private int nSauron = 0;
    private int nSmaug = 0;
    private int results;
    private final RolesManagers rolesManagers;

    public RolesGui(RolesManagers rolesManagers) {
        this.rolesManagers = rolesManagers;
    }

    @Override
    public void init(Player player, InventoryContents contents) {

    }


    @Override
    public void update(Player player, InventoryContents contents) {
        contents.set(0, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Merchant.getId(), "Marchand", nMarchand), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nMarchand++;
                rolesManagers.getRolesList().add(rolesManagers.getInstancesRoles().get("Marchand"));
            } else {
                if ((nMarchand != 0)) {
                    nMarchand--;
                    deleteInstance(rolesManagers.getInstancesRoles().get("Marchand"));
                }
            }

        }));


        contents.set(0, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.WiseMan.getId(), "Sage", nSage), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nSage++;
                rolesManagers.getRolesList().add(rolesManagers.getInstancesRoles().get("Sage"));
            } else {
                if ((nSage != 0)) {
                    nSage--;
                    deleteInstance(rolesManagers.getInstancesRoles().get("Sage"));
                }
            }
        }));


        contents.set(1, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Seer.getId(), "Voyant", nVoyant), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nVoyant++;
                rolesManagers.getRolesList().add(rolesManagers.getInstancesRoles().get("Voyant"));
            } else {
                if ((nVoyant != 0)) {
                    nVoyant--;
                    deleteInstance(rolesManagers.getInstancesRoles().get("Voyant"));
                }
            }
        }));

        contents.set(1, 4, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Guard.getId(), "Garde", nGarde), e -> {
           /* player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nGarde++;
                rolesManagers.getRolesList().add(rolesManagers.getInstancesRoles().get("Garde"));
            } else {
                if ((nGarde != 0)) {
                    nGarde--;
                    rolesManagers.getRolesList().remove(rolesManagers.getInstancesRoles().remove("Garde"));
                }
            }*/
        }));


        contents.set(1, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Hunter.getId(), "Chasseur", nChasseur), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nChasseur++;
                rolesManagers.getRolesList().add(rolesManagers.getInstancesRoles().get("Chasseur"));
            } else {
                if ((nChasseur != 0)) {
                    nChasseur--;
                    deleteInstance(rolesManagers.getInstancesRoles().get("Chasseur"));
                }
            }
        }));

        contents.set(2, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Nazgûl.getId(), "Nazgûl", nNazgûl), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nNazgûl++;
                rolesManagers.getRolesList().add(rolesManagers.getInstancesRoles().get("Nazgul"));
            } else {
                if ((nNazgûl != 0)) {
                    nNazgûl--;
                    deleteInstance(rolesManagers.getInstancesRoles().get("Nazgul"));
                }
            }
        }));

        contents.set(2, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Tavernier.getId(), "Tavernier", nTavernier), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nTavernier++;
                rolesManagers.getRolesList().add(rolesManagers.getInstancesRoles().get("Tavernier"));
            } else {
                if ((nTavernier != 0)) {
                    nTavernier--;
                    deleteInstance(rolesManagers.getInstancesRoles().get("Tavernier"));
                }
            }
        }));

        contents.set(3, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Sauron.getId(), "Sauron", nSauron), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nSauron++;
            } else {
                if ((nSauron != 0)) {
                    nSauron--;
                }
            }
        }));

        contents.set(3, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Smaug.getId(), "Smaug", nSmaug), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nSmaug++;
            } else {
                if ((nSmaug != 0)) {
                    nSmaug--;
                }
            }
        }));
    }

    public int resultsRoles() {
        results = nSmaug + nSauron;
        return results;
    }

    public int resultsRaces() {
        results = nMarchand + nSage + nVoyant + nGarde + nChasseur + nNazgûl + nTavernier;
        return results;
    }
    public void deleteInstance(Roles roles) {
        for (int i = 0; i < rolesManagers.getRolesList().size(); i++) {
            if (rolesManagers.getRolesList().get(i) == roles) {
                rolesManagers.getRolesList().remove(i);
                break;
            }
        }

    }
}