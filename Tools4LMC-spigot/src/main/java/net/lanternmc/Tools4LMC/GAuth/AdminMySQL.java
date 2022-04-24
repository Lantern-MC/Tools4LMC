package net.lanternmc.Tools4LMC.GAuth;

import lombok.SneakyThrows;
import net.lanternmc.r1_8.MySQL.MySQLFunc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AdminMySQL {

    public static String host = "172.16.6.21";
    public static String db = "proxy";
    public static String user = "lanternmc";
    public static String pass = "lanterndata";
    public static Connection conn;

    @SneakyThrows
    public static void enclosure() throws SQLException {
        conn = new MySQLFunc(host,db,user,pass).open();
        if (!conn.getMetaData().getTables(null, null, "t4lmc", null).next()) {
            CreateSQL();
        } else {
            System.out.println("MySQL创建成功");
        }
    }

    @SneakyThrows
    public static void CreateSQL() throws SQLException {
        // 执行查询
        System.out.println("创建表");
        String yuju = "CREATE TABLE `proxy`.`t4lmc`  (\n" +
                "  `Administrator` varchar(255) NULL COMMENT '管理员',\n" +
                "  `SecretKey` varchar(255) NULL COMMENT '密钥',\n" +
                "  `LastLogin` bigint NULL COMMENT '当时登录的时间戳'" +
                ");";
        Statement stmt = conn.createStatement();
        System.out.println(stmt.executeUpdate(yuju));
    }

}
