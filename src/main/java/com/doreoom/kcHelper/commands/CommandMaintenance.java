package com.doreoom.kcHelper.commands;

import com.doreoom.kcHelper.Main;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.LinkedList;

public class CommandMaintenance implements CommandExecutor {

    protected final Main plugin;

    public CommandMaintenance(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if (commandSender instanceof Player && commandSender.isOp() && strings.length>0){
            ConfigurationSection maintenance = plugin.getConfig().getConfigurationSection("maintenance");
            Collection<? extends Player> players = Bukkit.getOnlinePlayers();

            maintenance.set("enable", strings[0]);
            if(strings[0].equals("1")){
                for (Player p : players) {
                    if (!p.isOp()){
                        p.kick(Component.text("服务器已进入维护模式哦~"));
                    }
                }
            }
            plugin.getConfig().set("maintenance", maintenance);
            plugin.saveConfig();
            ((Player)commandSender).sendRawMessage("维护目前状态："+strings[0]);
        }

        return true;
    }
}
