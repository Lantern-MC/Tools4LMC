package net.lanternmc.Tools4LMC;

import net.lanternmc.Tools4LMC.GAuth.AdminMySQL;
import net.lanternmc.Tools4LMCSpigot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Tools4LMC {

    public static List<String> HuLue = new ArrayList<>();

    public static void Start() {
        //屏蔽插件
        HuLue.add("Tools4LMC");
        Tools4LMCSpigot.getheart().ignoredPlugins = HuLue;
        //正常启动
        String 使用此前置必读 = "计分板第一套是静态变量模式,计分板2才是可更新但是 更新必须是6以上的变动，另外必须硬前置我";
        Tools4LMCSpigot.getheart().getCommand("lmc").setExecutor((new CommandManager()));
        Tools4LMCSpigot.getheart().getCommand("lmc").setTabCompleter(new TabManager());
        Tools4LMCSpigot.getheart().getServer().getPluginManager().registerEvents(new TLMCListern(), Tools4LMCSpigot.getheart());
        // 脱离剑客云服务器 内网 插件将永远失效除非改内置
        try {
            AdminMySQL.enclosure();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
