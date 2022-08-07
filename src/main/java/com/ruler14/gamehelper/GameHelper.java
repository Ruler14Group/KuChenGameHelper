package com.ruler14.gamehelper;

import com.ruler14.gamehelper.commands.CommandGameHelper;
import org.bukkit.plugin.java.JavaPlugin;

public final class GameHelper extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig();
        getCommand("gh").setExecutor(new CommandGameHelper(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }
}
