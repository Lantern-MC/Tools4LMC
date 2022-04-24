package net.lanternmc.Tools4LMC.GAuth;

import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

public class Checkperm implements Listener {
    public static HashMap<Player, Boolean> stats = new HashMap<>(); // 你该重新登陆了

    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        try {
            PreparedStatement stmt =
                    AdminMySQL.conn.prepareStatement("SELECT * FROM t4lmc WHERE Administrator = \"" + e.getPlayer().getName() + "\"");
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()){ // 库里没有的
                if (e.getPlayer().hasPermission("*")
                        || isPlayerInGroup(e.getPlayer(), "owner")
                        || isPlayerInGroup(e.getPlayer(), "admin")
                        || e.getPlayer().isOp()) { // 还是管理的
                    e.getPlayer().kickPlayer("§6你咋不听话啊" +
                            "有管理权限不启动2FA 就像疫情防控期间不测核酸一样" +
                            "你还想进服你这样服务器安全会不保的 " +
                            "请联系后台管理 " +
                            "给你下权限重新进入后处理 重新开启管理权限触发QRcode启动");
                }
            } else { // 库里有的 判定LastLogin + 30 * 60 * 1000 >= dangqian
                PreparedStatement statements =
                        AdminMySQL.conn.prepareStatement("SELECT * FROM t4lmc WHERE `Administrator` = '" + e.getPlayer().getName() + "'");
                ResultSet resultSet = statements.executeQuery();
                if (resultSet.next()) {
                    long sql = resultSet.getLong("LastLogin");
                    if (sql + (30 * 60 * 1000) <= new Date().getTime()) {
                        stats.put(e.getPlayer(), true);
                        e.getPlayer().sendMessage("§f 请您输入/lmc code <6位> 才能登陆成功");
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        if (stats.get(e.getPlayer()) != null) {
            // 创建通道
            e.setCancelled(true);
//            e.getPlayer().kickPlayer("§6进来后不要动 写code");
        }
    }

}
