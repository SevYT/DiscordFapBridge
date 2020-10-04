package me.impurity.discordchatbridge.bukkitevents;

import me.impurity.discordchatbridge.DiscordChatBridge;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.awt.*;

public class PlayerChat implements Listener {
    DiscordChatBridge plugin;

    public PlayerChat(DiscordChatBridge discordChatBridge) {
        plugin = discordChatBridge;
    }
CooldownManager cm = new CooldownManager();
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (cm.checkCooldown(player)) {
            cm.setCooldown(player, 3);
            sendEmbed(event.getMessage(), player.getName(), plugin.getChannel());
        }
    }

    private void sendEmbed(String message, String playerName, TextChannel channel) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription("<" + playerName + "> " + message);
        embedBuilder.setColor(new Color(110, 255, 59));
        channel.sendMessage(embedBuilder.build()).queue();
    }
}