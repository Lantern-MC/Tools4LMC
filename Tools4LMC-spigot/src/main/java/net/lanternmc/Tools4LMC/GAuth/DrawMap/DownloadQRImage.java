package net.lanternmc.Tools4LMC.GAuth.DrawMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import net.lanternmc.Tools4LMCSpigot;
import net.lanternmc.r1_8.File.streamFile;
import org.apache.log4j.Logger;
import org.bukkit.entity.Player;

/**
 * 下载http对应URL的图片资源
 *
 */
public class DownloadQRImage {
	/**
	 *
	 * @param httpUrlStr
	 * @param p
	 * @return
	 */
	public static String download(String httpUrlStr, Player p){
		try {
			new streamFile().start(httpUrlStr,
					"update"+ File.separator + p.getName() + ".png",
					"玩家认证");
		} catch (Exception e) {
			e.printStackTrace();
		}
		File BukkitPath = new File("");
		String fileName = BukkitPath.getAbsolutePath()
				+ File.separator + "plugins"
				+ File.separator + "update"
				+ File.separator + p.getName() +  ".png";
		return fileName;
	}

	/**
	 * 删除临时网站
	 * @param p
	 * @return
	 */
	public static boolean deleteFile(Player p) {
    	boolean flag = false;
    	try{
			File BukkitPath = new File("");
			String fileName = BukkitPath.getAbsolutePath()
					+ File.separator + "plugins"
					+ File.separator + "update"
					+ File.separator + p.getName() +  ".png";
    		Tools4LMCSpigot.getheart().getLogger().info(String.format("删除临时文件 path = {%s} ", fileName));
    		File file = new File(fileName);
    		// 路径为文件且不为空则进行删除
    		if (file.isFile() && file.exists()) {
    			file.delete();
    			flag = true;
    		}
    	} catch(Exception e){
			Tools4LMCSpigot.getheart().getLogger().info("删除文件异常 " + e.getMessage());
    	}
    	return flag;
    }

}