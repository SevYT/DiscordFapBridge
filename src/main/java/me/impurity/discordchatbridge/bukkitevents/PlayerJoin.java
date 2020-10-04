package me.impurity.discordchatbridge.bukkitevents;

import me.impurity.discordchatbridge.DiscordChatBridge;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;

public class PlayerJoin implements Listener {
    DiscordChatBridge plugin;

    public PlayerJoin(DiscordChatBridge discordChatBridge) {
        plugin = discordChatBridge;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        sendEmbed(event.getPlayer().getName(), plugin.getChannel());
    }

    private void sendEmbed(String playerName, TextChannel channel) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setDescription(":heavy_plus_sign: " + playerName);
        embedBuilder.setColor(Color.GREEN);
        channel.sendMessage(embedBuilder.build()).queue();
    }
}