package com.pistofranco.roleplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
/**
 * Created by Jordi on 14/02/2017.
 */
public class Countdown extends BukkitRunnable {
    RoleGame plugin = RoleGame.getPlugin();
    int time = plugin.getConfig().getInt("options.TimeLobby");
    public void run() {
        GameState.setState(GameState.STARTING);
        if (Bukkit.getOnlinePlayers().size() > 0) {
            if (time % 10 == 0) {
                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "RoleGame" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " Choosing phase starts in: " + ChatColor.AQUA + time);
            }
            if (time < 10 && time > 0) {
                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "RoleGame" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " Choosing phase starts in: " + ChatColor.AQUA + time);
            }
            if (time == 0) {
                Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "RoleGame" + ChatColor.GRAY + "]" + ChatColor.YELLOW + " Starting the choosing phase!");
                GameState.setState(GameState.CHOSING);
                plugin.getServer().broadcastMessage("State: "+ChatColor.AQUA+GameState.getState().toString());
                MovementsManager game = new MovementsManager();
                for (Player playersOnline : Bukkit.getOnlinePlayers()) {
                    game.setPlayer(playersOnline);
                }
                plugin.stopCountdown();
                plugin.startCountdown2();
            }
            time--;
        } else {
            GameState.setState(GameState.WAITING);
            plugin.stopCountdown();
            plugin.startCountdown();
        }
    }
}
