package com.terminalcalamitas.hopperautosort;

import com.terminalcalamitas.hopperautosort.listeners.HopperListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class HopperAutoSort extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new HopperListener(), this);
        Bukkit.getLogger().info("HopperAutoSort started!");
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
