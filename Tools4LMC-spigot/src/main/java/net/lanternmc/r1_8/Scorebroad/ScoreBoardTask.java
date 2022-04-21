package net.lanternmc.r1_8.Scorebroad;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ScoreBoardTask implements Runnable {

    Player p;
    Plugin plugin;
    String Title;
    List<String> lines;

    public ScoreBoardTask(Player 玩家, Plugin 插件instance, String Title, List<String> 文字内容) {
        this.p = 玩家;
        this.plugin = 插件instance;
        this.Title = Title;
        this.lines = 文字内容;
    }

    @Override
    public void run() {
        SimpleDateFormat format = new SimpleDateFormat ("yy/mm/dd");
        String sb = format.format(new Date());
        String title = ChatColor.translateAlternateColorCodes('&', Title);
        lines = PlaceholderAPI.setPlaceholders(p, lines);
        lines.forEach(l -> ChatColor.translateAlternateColorCodes('&',l.replace("{data}", sb)));

        new ScoreBoardUtils().SidebarDisplay(p, title, lines);
    }
}