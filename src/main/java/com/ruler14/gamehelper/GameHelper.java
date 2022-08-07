package com.ruler14.gamehelper;

import org.bukkit.plugin.java.JavaPlugin;

public final class GameHelper extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }
}
