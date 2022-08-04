package com.doreoom.kcHelper.commands;

import com.doreoom.kcHelper.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class CommandNotice implements CommandExecutor {
    protected final Main plugin;

    public CommandNotice(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player && commandSender.isOp() && strings.length>0){
            ConfigurationSection notice = plugin.getConfig().getConfigurationSection("notice");

            notice.set("enable", strings[0]);
            plugin.getConfig().set("notice", notice);
            plugin.saveConfig();

            ((Player)commandSender).sendRawMessage("OP提示目前状态："+strings[0]);
        }

        return true;
    }
}
