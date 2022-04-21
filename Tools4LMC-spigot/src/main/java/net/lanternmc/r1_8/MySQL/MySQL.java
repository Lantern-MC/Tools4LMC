package net.lanternmc.r1_8.MySQL;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

/**
 * 原生MySQL链接
 */
public class MySQL {
  private Plugin instance;
  private String HOST = null;
  private String DB = null;
  private String USER = null;
  private String PASS = null;
  private boolean connected = false;
  private Statement st = null;
  private Connection con = null;
  private MySQLFunc MySQL;
  private String conName;
  
  public MySQL(Plugin p,String name) {
    this.instance = p;
    this.conName = name;
    this.connected = false;
  }
  
  public Boolean Connect(Plugin p,String host, String db, String user, String pass) {
    this.HOST = host;
    this.DB = db;
    this.USER = user;
    this.PASS = pass;
    this.MySQL = new MySQLFunc(host, db, user, pass);
    this.con = this.MySQL.open();
    try {
      this.st = this.con.createStatement();
      this.connected = true;
      p.getLogger().info("[" + this.conName + "] 链接数据库成功.");
    } catch (SQLException e) {
      this.connected = false;
      p.getLogger().info("[" + this.conName + "] 无法连接到数据库.");
    } 
    this.MySQL.close(this.con);
    return Boolean.valueOf(this.connected);
  }
  
  public int countRows(Plugin p,String table) {
    int count = 0;
    ResultSet set = query(p, String.format("SELECT * FROM %s", new Object[] { table }));
    try {
      while (set.next())
        count++; 
    } catch (SQLException e) {
      Bukkit.getLogger().log(Level.SEVERE, "无法从表中选择所有行: " + table + ", 错误: " + e.getErrorCode());
    } 
    return count;
  }
  
  public void update(Plugin p,String query) {
    this.MySQL = new MySQLFunc(this.HOST, this.DB, this.USER, this.PASS);
    this.con = this.MySQL.open();
    try {
      this.st = this.con.createStatement();
      this.st.execute(query);
    } catch (SQLException e) {
      p.getLogger().info("[" + this.conName + "] 执行语句时出错: " + e.getErrorCode());
    } 
    this.MySQL.close(this.con);
  }
  
  public ResultSet query(Plugin p, String query) {
    this.MySQL = new MySQLFunc(this.HOST, this.DB, this.USER, this.PASS);
    this.con = this.MySQL.open();
    ResultSet rs = null;
    try {
      this.st = this.con.createStatement();
      rs = this.st.executeQuery(query);
    } catch (SQLException e) {
      p.getLogger().info("[" + this.conName + "] 执行查询时出错: " + e.getErrorCode());
    } 
    return rs;
  }
}
