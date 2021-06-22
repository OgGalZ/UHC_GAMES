package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.Main;
import me.oggalz.uhc_games.gui.ScenariosGui;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.player.PlayerManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.ArrayList;
import java.util.List;

public class Teleportation extends BukkitRunnable {

    private final PlayerManager playerManager;
    private final Main main;

    public Teleportation(PlayerManager playerManager, Main main) {
        this.playerManager = playerManager;
        this.main = main;
    }

    @Override
    public void run() {
        step1();
    }
    public void step1(){

        String startMessage = ChatColor.RED + "La partie ce lancera dans ";
         for(Player player : Bukkit.getOnlinePlayers()){

         }
    }
    public void step2(){

        World world = Bukkit.getWorld("world");
        for(int x = 100/2 ; x  > -100 ; x--){
            for( int z  = 100/2  ; z > -100 ; z-- ){
                Location location = new Location(world , x , 60 , z );
                if(!(location.getChunk().isLoaded())){
                    location.getChunk().load();
                }
            }
        }
    }


    public void runTp() {
        Teleportation teleportation = new Teleportation(playerManager, main);
        teleportation.runTaskTimer(main, 200, 20);
    }
}