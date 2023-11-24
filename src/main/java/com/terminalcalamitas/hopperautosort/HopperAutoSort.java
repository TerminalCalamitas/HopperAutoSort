package com.terminalcalamitas.hopperautosort;

import org.bukkit.plugin.java.JavaPlugin;
import com.terminalcalamitas.hopperautosort.listeners.HopSort;
import org.bukkit.Bukkit;

public final class HopperAutoSort extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        HopperAutoSort plugin = this;
        Bukkit.getLogger().info("####Starting Hopper Sorting Plugin####");
        getServer().getPluginManager().registerEvents(new HopSort(), plugin);
       // getServer().getPluginManager().registerEvents(new onInventoryPickupItemEvent(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
