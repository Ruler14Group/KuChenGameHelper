package com.doreoom.kcHelper.listener;

import com.doreoom.kcHelper.Main;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerChatEvent;

import java.awt.*;
import java.time.format.TextStyle;

public class PlayerListener implements Listener {

    protected final Main plugin;

    public PlayerListener(Main plugin) {
        this.plugin = plugin;
    }

    //@EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection notice = plugin.getConfig().getConfigurationSection("notice");
        ConfigurationSection bt = plugin.getConfig().getConfigurationSection("bantalk");

        if (player.isOp()){
            if (notice != null && notice.getString("enable").equals("1")) {
                String msg = ((TextComponent) event.message()).content();
                event.setCancelled(true);
                Bukkit.getServer().broadcast(
                        Component.text()
                        .decoration(TextDecoration.BOLD, true)
                        .append(Component.text(" [ " + "提示" + " ] ", NamedTextColor.GOLD))
                        .append(Component.text(msg)
                                .color(NamedTextColor.RED)
                                .decoration(TextDecoration.ITALIC, true)).build()
                );
            }
        }else if (bt != null && bt.getString("enable").equals("1")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event) {
        OfflinePlayer p = Bukkit.getOfflinePlayer(event.getUniqueId());

        //跳过op
        if (p.isOp()){return;}

        ConfigurationSection maintenance = plugin.getConfig().getConfigurationSection("maintenance");
        if (maintenance != null && maintenance.getString("enable").equals("1")) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, Component.text(maintenance.getString("msg")));
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        ConfigurationSection autoban = plugin.getConfig().getConfigurationSection("autoban");
        if (autoban != null && autoban.getString("enable").equals("1") && !player.isOp()){
            Bukkit.banIP(player.getAddress().getHostString());
            player.kick(Component.text(autoban.getString("msg")));
        }

    }

}
