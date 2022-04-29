package net.lanternmc.Lanternlevel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class CoinsAPI extends JavaPlugin {
    static Connection con;

    static int schedulerID = 0;

    static Plugin instance;

    static String host;

    static String user;

    static String database;

    static String password;

    static String port;

    public static void connect(Plugin instance, String host, String user, String database, String password, String port) {
        CoinsAPI.instance = instance;
        CoinsAPI.host = host;
        CoinsAPI.user = user;
        CoinsAPI.database = database;
        CoinsAPI.password = password;
        CoinsAPI.port = port;
        if (!isConnected())
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", user, password);
                System.out.println("[COINS] MySQL Verbindung hergestellt!");
                onReconnectScheduler();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        createTable();
    }

    public static void disconnect() {
        if (!isConnected())
            try {
                con.close();
                Bukkit.getScheduler().cancelTask(schedulerID);
                System.out.println("[COINS] MySQL Verbindung getrennt!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    private static boolean isConnected() {
        return (con != null);
    }

    private static void onReconnectScheduler() {
        schedulerID = Bukkit.getScheduler().scheduleSyncRepeatingTask(instance, new Runnable() {
            public void run() {
                CoinsAPI.onReconnect();
            }
        }, 36000L, 36000L);
    }

    private static void onReconnect() {
        if (con != null)
            try {
                con.close();
                System.out.println("[COINS] MySQL Verbindung getrennt!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        Bukkit.getScheduler().scheduleSyncDelayedTask(instance, new Runnable() {
            public void run() {
                try {
                    CoinsAPI.con = DriverManager.getConnection("jdbc:mysql://" + CoinsAPI.host + ":" + CoinsAPI.port + "/" + CoinsAPI.database + "?autoReconnect=true", CoinsAPI.user, CoinsAPI.password);
                    System.out.println("[COINS] MySQL Verbindung hergestellt!");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 1L);
    }

    private static void createTable() {
        try {
            con.prepareStatement("CREATE TABLE IF NOT EXISTS lanterncoin (UUID VARCHAR(100), coins INT(16))").executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getCoins(UUID uuid) {
        try {
            PreparedStatement st = con.prepareStatement("SELECT coins FROM coinTable WHERE UUID = ?");
            st.setString(1, uuid.toString());
            ResultSet rs = st.executeQuery();
            if (rs.next())
                return rs.getInt("coins");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void setCoins(UUID uuid, int coins) {
        if (getCoins(uuid) == -1) {
            try {
                PreparedStatement st = con.prepareStatement("INSERT INTO coinTable (UUID,coins) VALUES (?,?)");
                st.setString(1, uuid.toString());
                st.setInt(2, coins);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement st = con.prepareStatement("UPDATE coinTable SET coins = ? WHERE UUID = ?");
                st.setString(2, uuid.toString());
                st.setInt(1, coins);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void resetCoins(UUID uuid) {
        if (getCoins(uuid) == -1) {
            try {
                PreparedStatement st = con.prepareStatement("INSERT INTO coinTable (UUID,coins) VALUES (?,?)");
                st.setString(1, uuid.toString());
                st.setInt(2, 0);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                PreparedStatement st = con.prepareStatement("UPDATE coinTable SET coins = ? WHERE UUID = ?");
                st.setString(2, uuid.toString());
                st.setInt(1, 0);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addCoins(UUID uuid, int coins) {
        setCoins(uuid, getCoins(uuid) + coins);
    }

    public static void removeCoins(UUID uuid, int coins) {
        setCoins(uuid, getCoins(uuid) - coins);
    }
}