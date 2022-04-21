package net.lanternmc.r1_8.Scorebroad;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class ScoreBoardManager {
    public ScoreBoardManager(Plugin plugin, String Title, List<String> 文字内容){
        Bukkit.getScheduler().runTaskTimer(plugin,()->{
            Bukkit.getOnlinePlayers().forEach(p -> Bukkit.getScheduler().runTask(plugin,new ScoreBoardTask(p, plugin, Title, 文字内容)));
        },0L,5L);
    }
}
