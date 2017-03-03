package com.pistofranco.roleplay;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;


//on X es digit i b la base, 10110001, 226, R,Ctrl + Shift + U 7E, 256000

class MovementsManager {

    private RoleGame plugin = RoleGame.getPlugin();
    private ArrayList<UUID> players = new ArrayList<UUID>(9);

    private Player m_player;
    private int currentPlayer = 0;
    boolean[] trowed;

    public void setPlayer(Player player){
        players.add(player.getUniqueId());
    }

    public void removePlayer(Player player){
        players.remove(player.getUniqueId());
    }

    public Player getCurrentPlayer(){
        return m_player;
    }

    void findNextPlayer() {
        Bukkit.broadcastMessage("Im being called!");
        for (int i = currentPlayer; i < players.size(); i++) {
            Bukkit.broadcastMessage("Current player int: "+currentPlayer);
            if (currentPlayer == 10)
                currentPlayer = 0;
            for (int o = 0; o < 10; o++){
                trowed[o] = false;
            }
            if(players.get(currentPlayer) == null){
                currentPlayer++;
                return;
            }else{
                setCurrentPlayer(i);
            }
        }
    }

    private void setCurrentPlayer(Integer number){
        m_player = null;
        m_player = Bukkit.getPlayer(players.get(number));
        trowed[number] = true;
        m_player.sendTitle("",ChatColor.GOLD+"Is your time!");
        Bukkit.broadcastMessage(m_player.getName()+" is trowing!");
    }
}
