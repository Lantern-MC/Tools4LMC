package net.lanternmc.r1_8.message;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendMessageUtils {
    public static void sendMessage(CommandSender sender, String... message) {
        sender.sendMessage(message);
    }

    public static void sendconsoleMessage(String[] 文字) {
        Bukkit.getConsoleSender().sendMessage(文字);
    }
}