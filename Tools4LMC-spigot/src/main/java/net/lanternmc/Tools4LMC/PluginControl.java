package net.lanternmc.Tools4LMC;

import net.lanternmc.Tools4LMCSpigot;
import net.lanternmc.r1_8.File.Explorer;
import net.lanternmc.r1_8.PluginUtils.PluginUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

public class PluginControl {
    public static void pl(String command, String pluginname, Player sender) throws InvalidPluginException {
        switch (command) {
            case "load":
                Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin(pluginname);
                if (plugin == null) {
                    sender.sendMessage(PluginUtil.load(pluginname));
                } else {
                    sender.sendMessage("§c名为§7" + pluginname + "§c加载了");
                }
                break;
            case "unload" :
                Plugin unplugin = PluginUtil.getPluginByName(pluginname);
                if (unplugin != null) {
                    sender.sendMessage(PluginUtil.unload(unplugin));
                } else {
                    sender.sendMessage("§c名为§7" + pluginname + "§c不在服务器无需卸载");
                }
                break;
            case "reload" :
                Plugin thisplugin = PluginUtil.getPluginByName(pluginname);
                if (thisplugin != null) {
                    sender.sendMessage(PluginUtil.unload(thisplugin));
                    sender.sendMessage(PluginUtil.load(pluginname));
                } else {
                    sender.sendMessage("§c名为§7" + pluginname + "§c不在服务器无需重载");
                }
                break;
        }
    }
}
