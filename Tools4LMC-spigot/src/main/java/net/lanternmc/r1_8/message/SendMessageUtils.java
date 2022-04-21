package net.lanternmc.r1_8.message;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class SendMessageUtils {
    public static void sendMessage(CommandSender sender, String... message) {
        sender.sendMessage(message);
    }

    public static void sendconsoleMessage(String[] 文字) {
        Bukkit.getConsoleSender().sendMessage(文字);
    }
}