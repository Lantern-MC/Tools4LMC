package net.lanternmc.java;

import me.zhenxin.qqbot.api.ApiManager;
import me.zhenxin.qqbot.entity.AccessInfo;
import me.zhenxin.qqbot.entity.Guild;
import net.lanternmc.qqbot.IEventHandler;
import net.lanternmc.qqbot.Messsge;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class main {
    public static boolean soft;
    // 不在MineCraft环境下运行入口 所有模式分开写 可以用文件判定 这边已于与Spigo完全做分离
    public static void main(String[] args) {
        soft = true;
        System.out.print("欢迎使用 LanternMC Java组件\n");
        System.out.print("T4产品\n");
        System.out.print("请输出系列 或者在运行根目录放置相应的文件启动对应的模式\n");
        System.out.print("1.QQ频道机器人模式        根目录文件.qqguild\n");
        System.out.print("0.退出                  根目录文件\n");

        File folder = new File("");
        File path = new File(folder.getAbsolutePath() + File.separator + ".qqguild");

        if (path.isFile()) {
            soft = false;
            main m = new main();
            m.StartBot();
        } else {
            System.out.println("没找到文件请手动进入到模式");
        }

        while(soft){//定义死循环
            Scanner s=new Scanner(System.in);//创建scanner，控制台会一直等待输入，直到敲回车结束
            String str=s.nextLine();//将用户的输入转换为字符串形式
            switch (str) {
                case "0":
                    System.exit(0);//关闭进程
                    break;
                case "1":
                    soft = false;
                    main m = new main();
                    m.StartBot();
                    break;
            }
        }
    }

    public void StartBot() {
        AccessInfo accessInfo = new AccessInfo();
        accessInfo.setBotAppId(102002582); // 管理端的BotAppId
        accessInfo.setBotToken("9gyOGECExtfYOLPNJoKdwCXDdUqqclS3"); // 管理端的BotToken
        accessInfo.setBotSecret("jNq69ze7OSKzShjZ");
        // 使用沙盒模式
        accessInfo.useSandBoxMode();
        // 创建实例
        ApiManager api = new ApiManager(accessInfo);
        // 调用
        Messsge handler = new Messsge();
        handler.setRemoveAt(false); // 取消删除消息中的艾特

//        BotCore bot = new BotCore(accessInfo);
//        // 获取API管理器
//        ApiManager api = bot.getApiManager();
//        // 注册AT消息相关事件
//        bot.registerAtMessageEvent();
//        // 设置事件处理器
//        IEventHandler handler = new IEventHandler(api);
//        // handler.setRemoveAt(false); // 取消删除消息中的艾特
//        bot.setEventHandler(handler);
//        // 启动
//        bot.start();

    }

}
