package com.doreoom.kcHelper.commands;

import com.doreoom.kcHelper.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class CommandBanTalk implements CommandExecutor {

    protected final Main plugin;

    public CommandBanTalk(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player && commandSender.isOp() && strings.length>0){
            ConfigurationSection bt = plugin.getConfig().getConfigurationSection("bantalk");

            bt.set("enable", strings[0]);
            plugin.getConfig().set("bt", bt);
            plugin.saveConfig();

            ((Player)commandSender).sendRawMessage("BanTalk 目前状态："+strings[0]);
        }

        return true;
    }
}
