package com.doreoom.kcHelper;

import com.doreoom.kcHelper.commands.*;
import com.doreoom.kcHelper.listener.PlayerListener;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    public ConfigurationSection AUTO_BAN = getConfig().getConfigurationSection("autoban");
    public ConfigurationSection NOTICE = getConfig().getConfigurationSection("noticee");
    public ConfigurationSection MUTE = getConfig().getConfigurationSection("bantalk");
    public ConfigurationSection MAINTENANCE = getConfig().getConfigurationSection("maintenance");
    public ConfigurationSection DEAD_SHOW = getConfig().getConfigurationSection("deadshow");

    public Main(){
        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

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
        getCommand("ks").setExecutor(new CommandDeadShow());
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
    }
}
