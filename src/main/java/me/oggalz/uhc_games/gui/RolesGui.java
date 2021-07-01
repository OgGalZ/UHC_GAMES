package me.oggalz.uhc_games.gui;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.ItemsId;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class RolesGui implements InventoryProvider {

    private int nMarchand = 1;
    private int nSage = 1;
    private int nVoyant = 1;
    private int nGarde = 1;
    private int nChasseur = 1;
    private int nNazgûl = 1;
    private int nTavernier = 1;
    private int nSauron = 1;
    private int nSmaug = 1;
    private int results;


    @Override
    public void init(Player player, InventoryContents contents) {

    }

    @Override
    public void update(Player player, InventoryContents contents) {
        contents.set(0, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Merchant.getId(), "Marchand", nMarchand), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nMarchand++;
            } else {
                if ((nMarchand != 0)) {
                    nMarchand--;
                }
            }

        }));


        contents.set(0, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.WiseMan.getId(), "Sage", nSage), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nSage++;
            } else {
                if ((nSage != 0)) {
                    nSage--;
                }
            }
        }));


        contents.set(1, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Seer.getId(), "Voyant", nVoyant), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nVoyant++;
            } else {
                if ((nVoyant != 0)) {
                    nVoyant--;
                }
            }
        }));

        contents.set(1, 4, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Guard.getId(), "Garde", nGarde), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nGarde++;
            } else {
                if ((nGarde != 0)) {
                    nGarde--;
                }
            }
        }));


        contents.set(1, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Hunter.getId(), "Chasseur", nChasseur), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nChasseur++;
            } else {
                if ((nChasseur != 0)) {
                    nChasseur--;
                }
            }
        }));

        contents.set(2, 3, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Nazgûl.getId(), "Nazgûl", nNazgûl), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nNazgûl++;
            } else {
                if ((nNazgûl != 0)) {
                    nNazgûl--;
                }
            }
        }));

        contents.set(2, 5, ClickableItem.of(Item.getCustomTextureHead(ItemsId.Tavernier.getId(), "Tavernier", nTavernier), e -> {
            player.playSound(player.getLocation(), Sound.CLICK, 99, 2);
            if (e.isLeftClick()) {
                nTavernier++;
            } else {
                if ((nTavernier != 0)) {
                    nTavernier--;
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
        results =  nSmaug + nSauron;
        return results;
    }
    public int resultsRaces(){
        results = nMarchand + nSage + nVoyant + nGarde + nChasseur + nNazgûl + nTavernier ;
        return results;
    }
}