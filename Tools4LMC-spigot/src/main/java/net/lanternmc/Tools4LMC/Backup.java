package net.lanternmc.Tools4LMC;

import net.lanternmc.Tools4LMCSpigot;
import net.lanternmc.r1_8.File.FolderUtil;

import java.io.File;

public class Backup {

    public void RecoveryBuild(){
        File BukkitPath = new File("");

        File lock = new File(BukkitPath.getAbsolutePath() + "/.lock");
        if (lock.exists()) return;// 有锁绕开 让其他插件来

        File panding = new File(BukkitPath.getAbsolutePath() + "/Backup");
        if (panding.exists()){
            Tools4LMCSpigot.getheart().getLogger().info("服务器开启了备份功能哦!开始重置备份");
            FolderUtil.delFolder(BukkitPath.getAbsolutePath() + "/world");
            try {
                Thread.sleep(2000);
                FolderUtil.copyFolder(BukkitPath.getAbsolutePath() + "/Backup/world", BukkitPath.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
            }
            Tools4LMCSpigot.getheart().getLogger().info("重制地图完成");
        } // 有文件夹就进行
    }



}
