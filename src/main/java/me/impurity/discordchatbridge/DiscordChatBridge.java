package me.impurity.discordchatbridge;

import me.impurity.discordchatbridge.bukkitevents.PlayerChat;
import me.impurity.discordchatbridge.bukkitevents.PlayerJoin;
import me.impurity.discordchatbridge.bukkitevents.PlayerQuit;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.awt.*;

public final class DiscordChatBridge extends JavaPlugin {
    TextChannel channel;
    JDA jda;

    public Color getTPSColor(double i) {
        if (i >= 18.0D) {
            return Color.GREEN;
        } else {
            return i >= 13.0D && i < 18.0D ? Color.YELLOW : Color.RED;
        }
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new PlayerChat(this), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(this), this);
        getServer().getPluginManager().registerEvents(new PlayerQuit(this), this);
        try {
            jda = JDABuilder.createDefault("NzYyMDc5NzkxNTM2NTM3NjEw.X3j8HQ.hlKmbmqfjLp_EeAJTt6pKX8R1c8").addEventListeners(new Object[]{
                    new MessageSendEvent(this)
            }).build();
            channel = jda.getGuildById("700845369587794052").getTextChannelById("762104745493266492");
        } catch (LoginException e) {
            e.printStackTrace();
        }
        assert channel != null;
        sendEmbed(":white_check_mark: Server Started", channel, Color.green);
    }



    @Override
    public void onDisable() {
        sendEmbed(":x: Server Stopped", channel, Color.RED);
        getJda().shutdown();
    }

    public JDA getJda() {
        return jda;
    }

    public TextChannel getChannel() {
        return channel;
    }
    private void sendEmbed(String message, TextChannel channel, Color color) {
        EmbedBuilder embedBuilder = new EmbedBuilder();
        embedBuilder.setTitle(message);
        embedBuilder.setColor(color);
        channel.sendMessage(embedBuilder.build()).queue();
    }
}

