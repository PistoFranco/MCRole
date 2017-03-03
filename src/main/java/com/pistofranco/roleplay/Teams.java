package com.pistofranco.roleplay;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Jordi on 15/02/2017.
 */
public class Teams {
    ArrayList<UUID> red = new ArrayList<UUID>();
    ArrayList<UUID> blue = new ArrayList<UUID>();
    ArrayList<UUID> AllPlayers = new ArrayList<UUID>();

    public void addRed(Player player) {
        red.add(player.getUniqueId());
    }

    public void addBlue(Player player) {
        blue.add(player.getUniqueId());
    }

    private void addToArray() {
        for (int i = 0; i < red.size(); i++) {
            if (red.get(i) == null) {
                return;
            } else {
                AllPlayers.add(red.get(i));
            }
            if (blue.get(i) == null) {
                return;
            } else {
                AllPlayers.add(blue.get(i));
            }
        }
    }

    public boolean isBlue(UUID player){
        for (int i = 0; i < blue.size();i++) {
            if(blue.get(i) == player){
                return true;
            }
        }
        return false;
    }

    public boolean isRed(UUID player){
        for (int i = 0; i < red.size();i++) {
            if(red.get(i) == player){
                return true;
            }
        }
        return false;
    }

    public boolean hasTeam(UUID uuid){
        for (int i = 0; i < red.size();i++){
            for (int o = 0; o < blue.size();o++){
                if (red.get(i) == uuid){
                    return true;
                } else if (blue.get(o) == uuid){
                    return true;
                }
            }
        }
        return false;
    }
    public int getIntPlayer(Player player){
        if (hasTeam(player.getUniqueId())){
            if (isBlue(player.getUniqueId())){
                for (int i = 0; 0 < blue.size();i++){
                    if(blue.get(i) == player.getUniqueId()){
                        return i;
                    }
                }
            }
            if (isRed(player.getUniqueId())){
                for (int i = 0; 0 < red.size();i++){
                    if(red.get(i) == player.getUniqueId()){
                        return i;
                    }
                }
            }
        }
        return 0;
    }

}
