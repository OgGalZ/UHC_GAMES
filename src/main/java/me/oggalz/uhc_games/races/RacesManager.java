package me.oggalz.uhc_games.races;

import me.oggalz.uhc_games.gui.RacesGui;
import me.oggalz.uhc_games.player.Player;
import me.oggalz.uhc_games.player.PlayerManager;
import org.bukkit.Bukkit;

import java.util.*;

public class RacesManager {

    private final PlayerManager playerManager;
    private Map<UUID, Races> racesPlayers;
    private final RacesGui racesGui;

    public RacesManager(PlayerManager playerManager, RacesGui racesGui) {
        this.playerManager = playerManager;
        this.racesGui = racesGui;
        racesPlayers = new HashMap<>();
    }

    public void generateMap() {
        int statementTrue = 1;
        Races races = new Elfes();
        int playersRaces = racesGui.resultsRaces();
        int test = 0;
        for (UUID uuid : playerManager.getKeys()) {
            if (playersRaces == test) {
                statementTrue++;
                test = 0;
                switch (statementTrue) {
                    case 2:
                        races = new Hobbits();
                        break;
                    case 3:
                        races = new Nains();
                        break;

                    case 4:
                        races = new Orques();
                        break;
                    case 5:
                        races = null;
                        break;
                }
            }

            while (playersRaces != test) {
                racesPlayers.put(uuid, races);
                test++;
                Bukkit.broadcastMessage("t" + test);
            }
        }


    }

    public Races getRaces(UUID uuid) {
        return racesPlayers.get(uuid);
    }
}

