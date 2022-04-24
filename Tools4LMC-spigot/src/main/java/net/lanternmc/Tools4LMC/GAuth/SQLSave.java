package net.lanternmc.Tools4LMC.GAuth;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class SQLSave {

    public static void defaultAdminData(String playerName, String miyao) {
        try{
            PreparedStatement stmt =
                    AdminMySQL.conn.prepareStatement("SELECT * FROM t4lmc WHERE Administrator = \"" + playerName + "\"");
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                long time = new Date().getTime();
                PreparedStatement create =
                        AdminMySQL.conn.prepareStatement("INSERT INTO t4lmc (Administrator,SecretKey,LastLogin) " +
                                "VALUES (\"" + playerName + "\", \"" + miyao + "\" , \"" + time + "\");");
                create.execute();
                System.out.print("创建完成");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

}
