package com.ruler14.gamehelper.listener;

import com.ruler14.gamehelper.GameHelper;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class PlayerListener implements Listener {

    protected final GameHelper plugin;

    public PlayerListener(GameHelper plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        ConfigurationSection notice = plugin.getConfig().getConfigurationSection("notice");
        ConfigurationSection bt = plugin.getConfig().getConfigurationSection("bantalk");

        if (player.isOp()){
            if (notice != null && notice.getString("enable").equals("1")) {
                String msg = ((TextComponent) event.message()).content();
                event.setCancelled(true);
                plugin.getServer().broadcast(
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
        OfflinePlayer p = plugin.getServer().getOfflinePlayer(event.getUniqueId());

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
            plugin.getServer().banIP(player.getAddress().getHostString());
            player.kick(Component.text(autoban.getString("msg")));
        }

    }


}
