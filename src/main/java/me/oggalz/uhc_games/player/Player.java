package me.oggalz.uhc_games.player;

import java.util.UUID;

public class Player {

    private final UUID uuid;
   // private Team team;

    public Player(UUID uuid) {
        this.uuid = uuid;
      //  this.team = null;
    }

    /*public void joinTeam(Team team) {
        this.team = team;
        team.addPlayer(this);
    }*/

    /*public void leaveTeam() {
        if (hasTeam()) {
            team.removePlayer(this);
            team = null;
        } else {
            throw new UnsupportedOperationException("Le joueur n'a pas d'équipe");
        }
    }*/

  /*  public boolean hasTeam() {
        return team != null;
    }

    public UUID getUniqueId() {
        return uuid;
    }*/


}
