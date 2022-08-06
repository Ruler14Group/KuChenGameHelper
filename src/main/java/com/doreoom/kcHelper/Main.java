package com.doreoom.kcHelper;

import com.doreoom.kcHelper.commands.*;
import com.doreoom.kcHelper.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onDisable() {
        super.onDisable();
        saveConfig();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        saveDefaultConfig();
        getCommand("v").setExecutor(new CommandView(this));
        getCommand("mt").setExecutor(new CommandMaintenance(this));
        getCommand("nt").setExecutor(new CommandNotice(this));
        getCommand("ab").setExecutor(new CommandAutoBan(this));
        getCommand("bt").setExecutor(new CommandBanTalk(this));
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }
}
