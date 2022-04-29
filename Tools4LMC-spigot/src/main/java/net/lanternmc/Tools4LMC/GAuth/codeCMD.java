package net.lanternmc.Tools4LMC.GAuth;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class codeCMD {

    public static void Run(String[] args, Player p) {
        String s = null;
        try {
            PreparedStatement statements =
                    AdminMySQL.conn.prepareStatement("SELECT * FROM t4lmc WHERE `Administrator` = '" + p.getName() + "'");
            ResultSet resultSet = statements.executeQuery();
            if (resultSet.next()) {
                s = resultSet.getString("SecretKey");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        GoogleAuth ga = new GoogleAuth();
        if (ga.authcode(args[1], s)) {
            //验证成功后更新lastlogin和删除限制
            long time = new Date().getTime();
            String data = "UPDATE t4lmc SET LastLogin=\""+ time +"\" WHERE Administrator=\"" + p.getName() + "\";";
            try {
                Statement stmt = AdminMySQL.conn.createStatement();
                stmt.executeUpdate(data);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            Checkperm.stats.remove(p);
            p.sendMessage("§6 OK!");
        } else {
            p.sendMessage("§6 错误!");
        }
    }

}
