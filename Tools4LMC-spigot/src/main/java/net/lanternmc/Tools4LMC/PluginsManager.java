package net.lanternmc.Tools4LMC;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.*;

import java.io.File;

public class PluginsManager {
    private Plugin main;
    public static String getVersion(Plugin plugin) {
        return StringUtils.substring(plugin.getDescription().getVersion(), 0, 15);
    }

    public boolean load(CommandSender sender, String name) {
        ConsoleCommandSender consoleCommandSender;
        String filename = name;
        if (sender == null) {
            consoleCommandSender = Bukkit.getConsoleSender();
        }
        if (!name.endsWith(".jar")) {
            filename = name + ".jar";
        }
        File pluginDir = new File("plugins");
        File updateDir = new File(pluginDir, "update");
        if (!pluginDir.isDirectory()) {
            sender.sendMessage("§6教入: sc插件目录不存在或Io错误!'");
            return false;
        }
        File pluginFile = new File(pluginDir, filename);
        if (!pluginFile.isFile() && !(new File(updateDir, filename)).isFile()) {
            pluginFile = null;
            for (File file : pluginDir.listFiles()) {
                if (file.getName().endsWith(".jar"))
                    try {
                        PluginDescriptionFile desc = this.main.getPluginLoader().getPluginDescription(file);
                        if (desc.getName().equalsIgnoreCase(name)) {
                            pluginFile = file;
                            break;
                        }
                    } catch (InvalidDescriptionException invalidDescriptionException) {
                    }
            }
            if (pluginFile == null) {
                sender.sendMessage("§6载入:§c在插件目录和更新目录均未找到§c" + name + "§c插件请确认文件是否存在!");
                return false;
            }
        }
        return load(sender, pluginFile);
    }


    public boolean load(CommandSender sender, File pluginFile) {
        Plugin target = null;
        String name = pluginFile.getName();
        try {
            try {
                target = Bukkit.getPluginManager().loadPlugin(pluginFile);
            } catch (UnsupportedClassVersionError e) {
                sender.sendMessage("§4异常: §C" + e.getMessage());
                sender.sendMessage("§C服务器或者Java的版本低于插件" + name + "所需要的版本!" );
                return false;
            } catch (InvalidPluginException e) {
                if ("Plugin already initialized!".equalsIgnoreCase(e.getMessage()) || "java.lang.IllegalArgumentException: Plugin already initialized!"
                        .equals(e.getMessage())) {
                    sender.sendMessage("§4异常: §c"+ e.getMessage());
                    sender.sendMessage("§4插件: §c"+ name + "已加载到服务器");
                    sender.sendMessage("§4注意: §c当前插件无法在运行时重载 请重启服务器");
                    return false;
                }
                sender.sendMessage("§4异常: §c" + e.getMessage());
                sender.sendMessage("§4文件: §c" + name + " 不是一个可载入的插件!");
                sender.sendMessage("§4注意: §cMOD服重载插件3次以上需重启服务器");
                return false;
            } catch (UnknownDependencyException e) {
                sender.sendMessage("§4异常: §c服务器未安装必须依赖: " + e.getMessage());
                sender.sendMessage("§4插件: §c" + name + " 载入失败 缺少部分依赖项目!");
                return false;
            }
            if (target == null) {
                sender.sendMessage("§4异常: §c服务器类加载器载入插件失败 请查看后台信息!");
                return false;
            }
            target.onLoad();
            Bukkit.getPluginManager().enablePlugin(target);
            sender.sendMessage("§6载入: §a插件 §b" + target.getName() + " §a版本 §d" + getVersion(target) + " §a已成功载入到服务器!");
            return true;
        } catch (Throwable e) {
            sender.sendMessage("§4错误: §c" + e.getClass().getName() + ": " + e.getMessage());
            sender.sendMessage("§4异常: §c具体信息请查看后台异常堆栈!");
            e.printStackTrace();
            sender.sendMessage("§4载入: §c插件 §b" + target.getName() + " §c版本 §d" + getVersion(target) + " §c载入失败!");
            return false;
        }
    }

    public boolean load(File pluginFile) {
        return load(Bukkit.getConsoleSender(), pluginFile);
    }

    public boolean load(String name) {
        return load(Bukkit.getConsoleSender(), name);
    }
}