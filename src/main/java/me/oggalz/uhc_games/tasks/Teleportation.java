package me.oggalz.uhc_games.tasks;

import me.oggalz.uhc_games.Main;

import me.oggalz.uhc_games.utils.NmsUtils;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Optional;

public class Teleportation extends BukkitRunnable implements Listener {

    private int start = 30;
    private boolean test = false;
    private final Main main;
    private final NmsUtils nmsUtils;
    private double percentage = 0 ;

    public Teleportation(Main main, NmsUtils nmsUtils) {
        this.main = main;
        this.nmsUtils = nmsUtils;
    }

    @Override
    public void run() {
        step1();
        actionBarInfos();
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {
        Optional<ItemStack> itemStack = Optional.ofNullable(event.getCurrentItem());
        Optional<Inventory> inventory = Optional.ofNullable(event.getInventory());
        Entity player = event.getWhoClicked();
        if (inventory.isPresent() && inventory.get().getTitle().equals(ChatColor.RED + "Configuration")) {
            if (itemStack.isPresent()) {
                if (itemStack.get().getItemMeta() != null && itemStack.get().getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Start")) {
                    if (player instanceof Player) {
                        if (!test) {
                            test = true;
                            runTp();
                            step2();

                        }
                    }
                }
            }
        }
    }

    public void step1() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (start == 0) {
                cancel();
            }
            player.playSound(player.getLocation(), Sound.FIREWORK_LARGE_BLAST, 99, 2);
            Bukkit.broadcastMessage(ChatColor.RED + "La partie se lancera dans " + ChatColor.BLUE + start);
            start--;
        }
    }

    public void step2() {
        World world = Bukkit.getWorld("world");
        for (int x = 100 / 2; x > -100; x--) {
            for (int z = 100 / 2; z > -100; z--) {
                Location location = new Location(world, x, 60, z);
                if (!(location.getChunk().isLoaded())) {
                    location.getChunk().load();

                }
            }
        }
    }

    public void actionBarInfos() {
        percentage += 3.3;
        int percentage1 = (int) percentage;
        if(percentage1 > 100){
            percentage1 = 100;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            nmsUtils.sendActionBar(player,  percentage1  +   "%");
        }
    }

    private void runTp() {
        Teleportation teleportation = new Teleportation(main, nmsUtils);
        teleportation.runTaskTimer(main, 0, 20);
    }

}