package net.lanternmc.r1_8.File;

import java.util.Properties;

public class St {
    /*看你是啥系统*/
    public static boolean isOSLinux() {
        Properties prop = System.getProperties();

        String os = prop.getProperty("os.name");
        return os != null && os.toLowerCase().contains("linux");
    }
}