package net.lanternmc.Tools4LMC;

import net.lanternmc.Tools4LMCSpigot;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class TabManager implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if(args[0].equals("boom")) {
            List<String> playerlist = new ArrayList<>();
            for (Player p : Bukkit.getOnlinePlayers()) {
                playerlist.add(p.getName());
            }
            return playerlist;
        }
        if(args[0].equals("plugin")) {
            if(args[1].endsWith("load")){
                List<String> plugins = new ArrayList<>();
                for (Plugin p : Bukkit.getServer().getPluginManager().getPlugins()) {
                    plugins.add(p.getName());
                }
                return plugins;
            }
            List<String> manager = new ArrayList<>();
            manager.add("load");manager.add("unload");manager.add("reload");
            return manager;
        }

        List<String> main = new ArrayList<>();
        main.add("ssh");
        main.add("help");
        main.add("list");
        main.add("freeze");
        main.add("boom");
        main.add("plugin");
        main.add("fly");
        main.add("code");
        main.add("createcode");
        return main;
    }
}
