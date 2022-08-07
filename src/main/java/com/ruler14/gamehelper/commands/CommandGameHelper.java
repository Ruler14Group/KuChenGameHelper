package com.ruler14.gamehelper.commands;

import com.ruler14.gamehelper.GameHelper;
import net.kyori.adventure.text.Component;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class CommandGameHelper implements CommandExecutor {

    protected final GameHelper plugin;

    public CommandGameHelper(GameHelper plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if ( sender instanceof Player && sender.isOp() && args.length > 0){
            Player player = (Player) sender;
            String arg2 = null;
            ConfigurationSection conf = plugin.getConfig().getConfigurationSection(args[0]);
            if (args.length>1){
                arg2 = args[1];
            }

            switch (args[0]){
                case "autoBan":
                    break;
                case "notice":
                    break;
                case "talkBan":
                    break;
                case "maintenance":
                    Collection<? extends Player> onlinePlayers = plugin.getServer().getOnlinePlayers();

                    conf.set("enable", arg2);
                    if (arg2.equals("1")){
                        for (Player p : onlinePlayers) {
                            if (!p.isOp()){
                                p.kick(Component.text("服务器已进入维护模式哦~"));
                            }
                        }
                    }
                    break;
                case "invisible":
                    break;
            }

            if (!args[0].equals("invisible")){
                plugin.saveConfig();
                plugin.getConfig().set(args[0], conf);
            }
            player.sendRawMessage("目前状态："+args[1]);

        }

        return true;
    }
}
