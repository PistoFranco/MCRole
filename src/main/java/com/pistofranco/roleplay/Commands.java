package com.pistofranco.roleplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class Commands implements CommandExecutor{
    RoleGame plugin = RoleGame.getPlugin();
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        Player player = (Player) sender;
        if(lbl.equalsIgnoreCase("role")){
            if(args.length<1){
                player.sendMessage("Help goes here!");
            }else {
                if(args[0].equalsIgnoreCase("build")){
                    Bukkit.broadcastMessage(ChatColor.RED+"Changing to CREATING state, all players kicked!");
                    GameState.setState(GameState.CREATING);
                    plugin.stopCountdown();
                    plugin.stopCountdown2();
                    for (Player pl : Bukkit.getOnlinePlayers()) {
                        if(!pl.isOp()){
                            pl.kickPlayer(ChatColor.RED+"This server is on building state! Only admins.");
                        }
                    }
                }
            }
        }
        return false;
    }
}
