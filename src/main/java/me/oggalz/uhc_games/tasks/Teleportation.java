package me.oggalz.uhc_games.tasks;

import fr.minuskube.netherboard.Netherboard;
import me.oggalz.uhc_games.Main;

import me.oggalz.uhc_games.commands.Finish;
import me.oggalz.uhc_games.gui.WorldBorderGui;
import me.oggalz.uhc_games.state.StateManager;
import me.oggalz.uhc_games.utils.Item;
import me.oggalz.uhc_games.utils.NmsUtils;
import me.oggalz.uhc_games.utils.ScoreboardCreator;
import org.bukkit.*;
import org.bukkit.WorldBorder;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

public class Teleportation extends BukkitRunnable implements Listener {

    private int start = 30;
    private boolean test = false;
    private final Main main;
    private final NmsUtils nmsUtils;
    private double percentage = 0;
    private final StateManager stateManager;
    private final Finish finish;
    private final ScoreboardCreator scoreboardCreator;

    public Teleportation(Main main, NmsUtils nmsUtils, StateManager stateManager, Finish finish, ScoreboardCreator scoreboardCreator) {
        this.main = main;
        this.nmsUtils = nmsUtils;
        this.stateManager = stateManager;
        this.finish = finish;
        this.scoreboardCreator = scoreboardCreator;
    }

    @Override
    public void run() {
        step1();
        actionBarInfos();
        if (start < 0) {
            step3();

        }
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
        for (int x = WorldBorderGui.getBorderSize() / 2; x > -WorldBorderGui.getBorderSize() / 2; x--) {
            for (int z = WorldBorderGui.getBorderSize() / 2; z > -WorldBorderGui.getBorderSize() / 2; z--) {
                Location location = new Location(world, x, 60, z);
                if (!(location.getChunk().isLoaded())) {
                    location.getChunk().load();
                }
            }
        }
    }


    public void step3() {
        World world = Bukkit.getWorld("world");
        int randomTP = generate(-WorldBorderGui.getBorderSize() / 2 - 5, WorldBorderGui.getBorderSize() / 2 - 5);
        for (Player player : Bukkit.getOnlinePlayers()) {
            Location location = new Location(world, randomTP, 120, randomTP);
            player.setGameMode(GameMode.SURVIVAL);
            WorldBorder worldBorder = world.getWorldBorder();
            worldBorder.setCenter(0, 0);
            worldBorder.setSize(WorldBorderGui.getBorderSize());
            player.teleport(location);
            player.sendMessage(ChatColor.ITALIC + "Good Luck " + player.getName() + ChatColor.RED + "  :)");
            Item.clearArmor(player);
            player.getInventory().clear();
            scoreboardCreator.deleteScoreboard(Netherboard.instance().getBoard(player));
            scoreboardCreator.createScoreboardGame(player);
            scoreboardCreator.runScoreboardGame();
            stateManager.startGame();
            if(finish.getItemStacks() != null){
                Arrays.stream(finish.getItemStacks()).filter(Objects::nonNull).forEach(i -> player.getInventory().addItem(i));
            }
            if(finish.getArmor() != null){
                Arrays.stream(finish.getArmor()).filter(Objects::nonNull).forEach(i -> player.getInventory().setArmorContents(finish.getArmor()));
            }
        }
    }

    public void actionBarInfos() {
        percentage += 3.3;
        int percentage1 = (int) percentage;
        if (percentage1 > 100) {
            percentage1 = 100;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            nmsUtils.sendActionBar(player, percentage1 + "%");
        }
    }

    public static int generate(int borneInf, int borneSup) {
        Random random = new Random();
        int nb;
        nb = borneInf + random.nextInt(borneSup - borneInf);
        return nb;

    }

    private void runTp() {
        Teleportation teleportation = new Teleportation(main, nmsUtils, stateManager, finish , scoreboardCreator);
        teleportation.runTaskTimer(main, 0, 20);
    }


}