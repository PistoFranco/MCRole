package com.pistofranco.roleplay;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Jordi on 14/02/2017.
 */
public class Listeners implements Listener {

    RoleGame plugin = RoleGame.getPlugin();
    String SPECIALITY = ChatColor.YELLOW + "Speciality selected: " + ChatColor.AQUA;
    String HABILITIES = ChatColor.GRAY + "Hability selected: " + ChatColor.AQUA;
    int movements = plugin.getConfig().getInt("options.MovementsPerRound");

    @EventHandler
    public void onPlayerWalk(PlayerMoveEvent event) {
        if (GameState.isState(GameState.STARTED)) {
            if (event.getFrom().getBlockX() == event.getTo().getBlockX() + 1 || event.getFrom().getBlockZ() == event.getTo().getBlockZ() + 1 ||
                    event.getFrom().getBlockX() == event.getTo().getBlockX() - 1 || event.getFrom().getBlockZ() == event.getTo().getBlockZ() - 1) {
                if (movements == 0) {
                    event.setCancelled(true);
                    event.getPlayer().sendTitle("",ChatColor.RED+"You ended your round!");
                    MovementsManager game = new MovementsManager();
                    game.findNextPlayer();
                    return;
                }
                movements--;
                event.getPlayer().sendMessage(ChatColor.GOLD + String.valueOf(movements) + " movements left");
            }
        }
    }

    @EventHandler
    public void KitSelector(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        Entity e = event.getRightClicked();
        if(GameState.isState(GameState.CREATING)){
            return;
        }
        if (GameState.isState(GameState.CHOSING)) {
            if (e instanceof ItemFrame) {
                if (player.getInventory().getItemInMainHand().getType() == Material.BARRIER) {
                    player.sendMessage(ChatColor.RED + "You can't put nothing in that slot!");
                    event.setCancelled(true);
                    return;
                    //SPECIALIST
                } else if (((ItemFrame) e).getItem().getType() == Material.DIAMOND_CHESTPLATE) {
                    event.setCancelled(true);
                    player.sendTitle("", SPECIALITY + "Juggernaut");
                    player.getInventory().setItem(0, new ItemStack(Material.DIAMOND_CHESTPLATE));

                }
                if (((ItemFrame) e).getItem().getType() == Material.BOW) {
                    event.setCancelled(true);
                    player.sendTitle("", SPECIALITY + "Archer");
                    player.getInventory().setItem(0, new ItemStack(Material.BOW));
                }
                if (((ItemFrame) e).getItem().getType() == Material.TNT) {
                    event.setCancelled(true);
                    player.sendTitle("", SPECIALITY + "Bomber");
                    player.getInventory().setItem(0, new ItemStack(Material.TNT));
                }
                    //HABILITIES
                if (((ItemFrame) e).getItem().getType() == Material.SHIELD) {
                    event.setCancelled(true);
                    if(player.getInventory().getHeldItemSlot() == 0){
                        player.sendMessage(ChatColor.RED+"You can't pick an ability in the same slot of the specialist.");
                        return;
                    }
                    player.sendTitle("", HABILITIES + "Shield");
                    player.getInventory().setItem(player.getInventory().getHeldItemSlot(),new ItemStack(Material.SHIELD));
                }
            }
        } else {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "You can't choose, we aren't in choosing phase!");
        }
    }

    @EventHandler
    public void onQuitEvent(PlayerQuitEvent event){
    MovementsManager movementsManager = new MovementsManager();
    movementsManager.removePlayer(event.getPlayer());
    }
}