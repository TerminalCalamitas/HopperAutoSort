package com.terminalcalamitas.hopperautosort;

import com.terminalcalamitas.hopperautosort.commands.Commands;
import org.bukkit.plugin.java.JavaPlugin;
import com.terminalcalamitas.hopperautosort.listeners.HopSort;
import org.bukkit.Bukkit;

public final class HopperAutoSort extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        HopperAutoSort plugin = this;
        getServer().getPluginManager().registerEvents(new HopSort(), plugin);
        getCommand("hoppersort").setExecutor(new Commands());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
