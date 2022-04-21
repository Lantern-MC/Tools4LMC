package net.lanternmc.r1_8.Tab;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class TagUtils {

    public static void setTag(Player p, Player target, String prefix, String suffix) {
        if (prefix.length() > 16) {
            prefix = prefix.substring(0, 16);
        }
        if (suffix.length() > 16) {
            suffix = suffix.substring(0, 16);
        }
        Scoreboard board = target.getScoreboard();
        Team t = board.getTeam(p.getName());
        if (t == null) {
            t = board.registerNewTeam(p.getName());
            t.setPrefix(prefix);
            t.setSuffix(suffix);
            t.addEntry(p.getName());
            return;
        }
        t.setPrefix(prefix);
        t.setSuffix(suffix);
        if (!t.hasEntry(p.getName())) {
            t.addEntry(p.getName());
        }
    }

    public static void setTag(Player p, Player target, String prefix, String suffix, String team) {
        if (prefix.length() > 16) {
            prefix = prefix.substring(0, 16);
        }
        if (suffix.length() > 16) {
            suffix = suffix.substring(0, 16);
        }
        Scoreboard board = target.getScoreboard();
        // a b cd
        Team t = board.getTeam(team + p.getName());
        if (t == null) {
            t = board.registerNewTeam(team + p.getName());
            t.setPrefix(prefix);
            t.setSuffix(suffix);
            t.addEntry(p.getName());
            return;
        }
        t.setPrefix(prefix);
        t.setSuffix(suffix);
        if (!t.hasEntry(p.getName())) {
            t.addEntry(p.getName());
        }
    }

    public static void unregisterTag(Player p,Player target) {
        Scoreboard board = p.getScoreboard();
        if (board != null && board.getEntryTeam(p.getName()) != null)
            board.getEntryTeam(p.getName()).unregister();
    }

}