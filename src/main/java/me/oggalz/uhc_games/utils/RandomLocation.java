package me.oggalz.uhc_games.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Random;

public class RandomLocation {


    public void RandomLocation(){
        int randomX = new Random(100).nextInt();
        int randomZ = new Random(100).nextInt();
        World world = Bukkit.getWorld("world");
        Location loc = null;
        for (int y = 30; y < 100; y++) {
            loc = new Location(world, randomX, y, randomZ);
            if (loc.getBlock() == null && loc.add(0,1,0).getBlock() == null) {
            } else {
                if (loc.getBlock().getType() == Material.AIR && loc.add(0,1,0).getBlock().getType() == Material.AIR) {

                }
            }
            break;
        }
    }
    }

