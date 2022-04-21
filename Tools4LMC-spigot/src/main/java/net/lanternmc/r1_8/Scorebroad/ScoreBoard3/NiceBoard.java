package net.lanternmc.r1_8.Scorebroad.ScoreBoard3;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

public class NiceBoard {

    private Scoreboard scoreboard;

    public NiceBoard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    /**
     *
     * Set the score of NiceBoard to an index(Score)
     *
     * @param text
     * @param index
     */
    public void setScore(String text, int index, int defscore){
        ScoreboardUtil.setScore(this, ChatColor.translateAlternateColorCodes('&', text), index, defscore);
    }

    /**
     *
     * Remove a line of NiceBoard by an index(Score)
     *
     * @param index
     */
    public void removeScore(int index, int defscore){
        ScoreboardUtil.removeScore(this, index, defscore);
    }

    /**
     *
     * Set the NiceBoard to the player
     *
     * @param player
     */
    public void addPlayer(Player player){
        player.setScoreboard(scoreboard);
    }

}