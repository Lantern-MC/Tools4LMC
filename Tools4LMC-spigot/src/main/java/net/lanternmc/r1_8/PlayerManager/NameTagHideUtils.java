package net.lanternmc.r1_8.PlayerManager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.NameTagVisibility;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class NameTagHideUtils {
    private static Scoreboard score = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
    
    public static void changePlayerNameTagVisiable(Player paramPlayer, boolean paramBoolean) {
        Team team = score.getTeam("NameTagHide");
        if (team == null) {
            team = score.registerNewTeam("NameTagHide");
            team.setNameTagVisibility(NameTagVisibility.HIDE_FOR_OWN_TEAM);
        } 
        if (paramBoolean) {
            team.addEntry(paramPlayer.getName());
        } else {
            team.removeEntry(paramPlayer.getName());
        } 
    }
}
