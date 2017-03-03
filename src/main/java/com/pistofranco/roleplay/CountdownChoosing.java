package com.pistofranco.roleplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownChoosing extends BukkitRunnable{
    private RoleGame plugin = RoleGame.getPlugin();
    private int time = plugin.getConfig().getInt("options.TimeChoosingKit");

    public void run() {
            if (time % 10 == 0) {
                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "RoleGame" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " The game starts in: " + ChatColor.AQUA + time);
            }
            if (time < 10 && time > 0) {
                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "RoleGame" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " The game starts in: " + ChatColor.AQUA + time);
            }
            if (time == 0) {
                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "RoleGame" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " Starting the game!");
                GameState.setState(GameState.STARTED);
                plugin.getServer().broadcastMessage("State: "+ChatColor.AQUA+GameState.getState().toString());
                plugin.stopCountdown2();
                for (Player player : Bukkit.getOnlinePlayers()) {
                    MovementsManager game = new MovementsManager();
                    game.setPlayer(player);
                    game.findNextPlayer();
                }
            }
            time--;
    }
}
