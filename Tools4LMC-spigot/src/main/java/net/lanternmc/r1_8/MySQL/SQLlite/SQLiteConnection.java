package net.lanternmc.r1_8.MySQL.SQLlite;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteConnection {
    private Connection con;
    
    private Statement stmt;
    
    public SQLiteConnection(File paramFile, String paramString1, String paramString2) throws SQLException, IOException, IllegalArgumentException {
        if (!paramFile.exists())
            paramFile.createNewFile(); 
        if (paramFile.isDirectory())
            throw new IllegalArgumentException("Database file can not be directory!"); 
        String str = paramFile.getAbsolutePath();
        this.con = DriverManager.getConnection("sqlite:" + str, paramString1, paramString2);
        this.stmt = this.con.createStatement();
    }
    
    public SQLiteConnection(String paramString1, String paramString2, String paramString3) throws SQLException {
        this.con = DriverManager.getConnection(paramString1, paramString2, paramString3);
        this.stmt = this.con.createStatement();
    }
    
    public boolean loadDriver() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException classNotFoundException) {
            return false;
        } 
        return true;
    }
    
    public boolean exec(String paramString) {
        check();
        try {
            return this.stmt.execute(paramString);
        } catch (Exception exception) {
            System.out.println("[ldcr.sql]: SQL Query Failed!");
            exception.printStackTrace();
            return false;
        } 
    }
    
    public ResultSet select(String paramString) {
        check();
        ResultSet resultSet = null;
        try {
            resultSet = this.stmt.executeQuery(paramString);
        } catch (Exception exception) {
            System.out.println("[ldcr.sql]: SQL Query Failed!");
            exception.printStackTrace();
        } 
        return resultSet;
    }
    
    public ResultSet select(String paramString1, String paramString2, String paramString3) {
        return select("SELECT * from " + paramString1 + " WHERE " + paramString2 + "='" + paramString3 + "'");
    }
    
    private void check() {
        try {
            if (this.stmt.isClosed())
                this.stmt = this.con.createStatement(); 
        } catch (SQLException sQLException) {
            System.out.println("[ldcr.sql]: SQL Query Failed!");
            sQLException.printStackTrace();
        } 
    }
    
    public boolean createTable(String paramString1, String paramString2) {
        return exec("CREATE TABLE IF NOT EXISTS `" + paramString1 + "` (`ID` int NOT NULL AUTO_INCREMENT, " + paramString2 + ");");
    }
}