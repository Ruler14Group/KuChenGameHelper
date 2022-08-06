package com.doreoom.kcHelper.commands;

import com.doreoom.kcHelper.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CommandView implements CommandExecutor {

    protected final Main plugin;

    public CommandView(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player &&commandSender.isOp() && strings.length>0){
            Player player = (Player) commandSender;
            List<Player> players= plugin.getServer().getWorld("world").getPlayers();

            if (strings[0].equals("0")){
                for (Player p : players) {
                    p.showPlayer(plugin, player);
                }
                player.sendRawMessage("隐身已关闭");
            }else {
                for (Player p : players) {
                    p.hidePlayer(plugin, player);
                }
                player.sendRawMessage("隐身已开启");
            }

        }
        return true;
    }
}
