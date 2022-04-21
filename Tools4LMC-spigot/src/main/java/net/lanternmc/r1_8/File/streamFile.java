package net.lanternmc.r1_8.File;

import net.lanternmc.r1_8.message.SendMessageUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class streamFile {

    public void start(String dlurl, String plpath, String name) throws Exception {

        if (!St.isOSLinux()) {

            String mfurl = dlurl;
            File directory = new File("");
            String mflocal = directory.getAbsolutePath() + "\\plugins\\" + plpath;
            File file = new File(mflocal);
            //判断文件是否存在，不存在则创建文件
            if (!file.exists()) { file.createNewFile(); }
            URL url = new URL(mfurl);
            //使用http打开这个URL 以及设置连接的时间与超时时间
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setConnectTimeout(6000);
            urlCon.setReadTimeout(6000);
            int code = urlCon.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK)
                throw new Exception("[Lantern] 文件读取失败 检查阿里云桶子");
            DataInputStream in = new DataInputStream(urlCon.getInputStream());
            DataOutputStream out = new DataOutputStream(new FileOutputStream(mflocal));
            byte[] buffer = new byte[2048];
            int count = 0;
            while ((count = in.read(buffer)) > 0) { out.write(buffer, 0, count); }
            SendMessageUtils.sendconsoleMessage(new String[]{"§a[Lantern] " + name + "同步完成"});
            try { out.close(); in.close(); } catch (Exception e) { e.printStackTrace(); }

        } else {

            String mfurl = dlurl;
            File directory = new File("");
            String mflocal = directory.getAbsolutePath() + "/plugins/" + plpath.replace('\\', '/');
            File file = new File(mflocal);
            if (!file.exists()) { file.createNewFile(); }
            URL url = new URL(mfurl);
            HttpURLConnection urlCon = (HttpURLConnection) url.openConnection();
            urlCon.setConnectTimeout(6000);
            urlCon.setReadTimeout(6000);
            int code = urlCon.getResponseCode();
            if (code != HttpURLConnection.HTTP_OK)
                throw new Exception("[Lantern] 文件读取失败 检查阿里云桶子");
            DataInputStream in = new DataInputStream(urlCon.getInputStream());
            DataOutputStream out = new DataOutputStream(new FileOutputStream(mflocal));
            byte[] buffer = new byte[2048];
            int count = 0;
            while ((count = in.read(buffer)) > 0) { out.write(buffer, 0, count); }
            SendMessageUtils.sendconsoleMessage(new String[]{"§a[Lantern] " + name + "同步完成"});
            try { out.close(); in.close(); } catch (Exception e) { e.printStackTrace(); }

        }

    }

}