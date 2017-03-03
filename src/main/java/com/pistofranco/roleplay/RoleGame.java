package com.pistofranco.roleplay;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class RoleGame extends JavaPlugin {
    private static RoleGame plugin;
    int task;
    int task2;

    public void onEnable(){
        plugin = this;
        startCountdown();
        setupConfig();
        getServer().getPluginManager().registerEvents(new Listeners(),this);
        getCommand("role").setExecutor(new Commands());
        //Auto-reload when the plugin is exported.
        BukkitTask task = new BukkitRunnable() {
            long lastModified = getFile().lastModified();
            public void run() {
                if (lastModified < getFile().lastModified()) {
                    cancel();
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload");
                }
            }
        }.runTaskTimer(this,20L,20L);
        MySQL mySQL = new MySQL();
        mySQL.getDataSource();
        mySQL.stabilishConnection();
        System.out.println("Connection stabilished!");
    }

    public void onDisable(){
        plugin = null;
    }

    public static RoleGame getPlugin(){
        return plugin;
    }

    public void startCountdown(){
        task = getServer().getScheduler().scheduleSyncRepeatingTask(this,new Countdown(),20L,20L);
    }
    public void stopCountdown2(){
        getServer().getScheduler().cancelTask(task2);
    }
    public void startCountdown2(){
        task2 = getServer().getScheduler().scheduleSyncRepeatingTask(this,new CountdownChoosing(),20L,20L);
    }
    public void stopCountdown(){
        getServer().getScheduler().cancelTask(task);
    }

    private void setupConfig(){
        getConfig().addDefault("options.TimePerRound",20);
        getConfig().addDefault("options.MovementsPerRound",25);
        getConfig().addDefault("options.TimeLobby",31);
        getConfig().addDefault("options.TimeChoosingKit",60);
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
}
