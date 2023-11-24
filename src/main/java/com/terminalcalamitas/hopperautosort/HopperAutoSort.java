package com.terminalcalamitas.hopperautosort;

import org.bukkit.plugin.java.JavaPlugin;
import com.terminalcalamitas.hopperautosort.listeners.HopSort;

public final class HopperAutoSort extends JavaPlugin {

    private static HopperAutoSort plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        System.out.println("####Starting Hopper Sorting Plugin####");
        getServer().getPluginManager().registerEvents(new HopSort(), this);
       // getServer().getPluginManager().registerEvents(new onInventoryPickupItemEvent(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
