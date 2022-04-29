package net.lanternmc;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import lombok.Getter;
import net.lanternmc.Tools4LMC.Backup;
import net.lanternmc.Tools4LMC.Tools4LMC;
import net.lanternmc.r1_8.File.FolderUtil;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public final class Tools4LMCSpigot extends JavaPlugin {

    @Getter
    public static ProtocolManager protocolManager;
    public static Tools4LMCSpigot ToolsHeart;
    public static Tools4LMCSpigot getheart()
    {
        return ToolsHeart;
    }
    public List<String> ignoredPlugins = null;
    public List<String> getIgnoredPlugins() {
        return ignoredPlugins;
    }

    @Override
    public void onEnable() {
        ToolsHeart = this;
        protocolManager = ProtocolLibrary.getProtocolManager();
        Tools4LMC.Start();
    }


}
