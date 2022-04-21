package net.lanternmc.AllUtils.JavaUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;

public class ReportWriter {
    private static SimpleDateFormat dateFmt = new SimpleDateFormat("yyyy-MM-dd kk:mm Z");
    
    private Date date = new Date();
    
    public StringBuilder output = new StringBuilder();
    
    private String flags = "";
    
    Plugin plugin;
    
    Method customInfo;
    
    Object methodinstance;
    
    public ReportWriter(Plugin paramPlugin, Method paramMethod, Object paramObject) {
        this.plugin = paramPlugin;
        this.customInfo = paramMethod;
        this.methodinstance = paramObject;
    }
    
    public void generate() {
        appendReportHeader(this.plugin);
        appendServerInformation(this.plugin.getServer());
        appendPluginInformation(this.plugin.getServer().getPluginManager().getPlugins());
        try {
            this.customInfo.invoke(this.methodinstance, (Object[])null);
        } catch (Exception exception) {
            appendln("在收集报告信息时发生异常错误");
            appendln(exception.getMessage());
            for (StackTraceElement stackTraceElement : exception.getStackTrace())
                appendln(stackTraceElement.toString()); 
        } 
        appendln("-------------");
        appendln(">> 报告结束");
        appendln();
    }
    
    protected static String repeat(String paramString, int paramInt) {
        if (paramString == null)
            return null; 
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b = 0; b < paramInt; b++)
            stringBuilder.append(paramString); 
        return stringBuilder.toString();
    }
    
    public void append(LogListBlock paramLogListBlock) {
        this.output.append(paramLogListBlock.toString());
    }
    
    public void appendln(String paramString) {
        this.output.append(paramString);
        this.output.append("\r\n");
    }
    
    public void appendln(String paramString, Object... paramVarArgs) {
        this.output.append(String.format(paramString, paramVarArgs));
        this.output.append("\r\n");
    }
    
    public void appendln() {
        this.output.append("\r\n");
    }
    
    public void appendHeader(String paramString) {
        String str = repeat("-", paramString.length());
        this.output.append(str);
        this.output.append("\r\n");
        appendln(paramString);
        this.output.append(str);
        this.output.append("\r\n");
        appendln();
    }
    
    private void appendReportHeader(Plugin paramPlugin) {
        appendln(paramPlugin.getName() + " By.LanternMC");
        appendln("生成时间: " + dateFmt.format(this.date));
        appendln();
        appendln("插件版本: " + paramPlugin.getDescription().getVersion());
        appendln();
    }
    
    private void appendServerInformation(Server paramServer) {
        appendHeader("服务器信息");
        LogListBlock logListBlock = new LogListBlock();
        Runtime runtime = Runtime.getRuntime();
        logListBlock.put("Java", "%s %s (%s)", System.getProperty("java.vendor"), System.getProperty("java.version"), System.getProperty("java.vendor.url"));
        logListBlock.put("操作系统", "%s %s (%s)", System.getProperty("os.name"), System.getProperty("os.version"), System.getProperty("os.arch"));
        logListBlock.put("处理器数量", runtime.availableProcessors());
        logListBlock.put("空闲内存", (runtime.freeMemory() / 1024L / 1024L) + " MB");
        logListBlock.put("最大内存", (runtime.maxMemory() / 1024L / 1024L) + " MB");
        logListBlock.put("总内存", (runtime.totalMemory() / 1024L / 1024L) + " MB");
        logListBlock.put("服务器", paramServer.getServerId());
        logListBlock.put("服务器名", paramServer.getServerName());
        logListBlock.put("服务器版本", paramServer.getVersion());
        logListBlock.put("在线玩家", "%d/%d", paramServer.getOnlinePlayers().size(), paramServer.getMaxPlayers());
        append(logListBlock);
        appendln();
    }
    
    private void appendPluginInformation(Plugin[] paramArrayOfPlugin) {
        appendHeader("插件列表 (" + paramArrayOfPlugin.length + ")");
        LogListBlock logListBlock = new LogListBlock();
        for (Plugin plugin : paramArrayOfPlugin)
            logListBlock.put(plugin.getDescription().getName(), plugin.getDescription().getVersion()); 
        append(logListBlock);
        appendln();
    }
    
    public void write(File paramFile) throws IOException {
        OutputStreamWriter outputStreamWriter = null;
        try {
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(paramFile), "UTF-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(this.output.toString());
            bufferedWriter.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        } 
        if (outputStreamWriter != null)
            try {
                outputStreamWriter.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }  
    }
    
    public String toString() {
        return this.output.toString();
    }
    
    public String getFlags() {
        return this.flags;
    }
    
    public void appendFlags(String paramString) {
        this.flags += paramString;
    }
    
    public class LogListBlock {
        private LinkedHashMap<String, Object> items = new LinkedHashMap<>();
        
        private int maxKeyLength = 0;
        
        private void updateKey(String param1String) {
            if (param1String.length() > this.maxKeyLength)
                this.maxKeyLength = param1String.length(); 
        }
        
        public LogListBlock put(String param1String1, String param1String2) {
            updateKey(param1String1);
            this.items.put(param1String1, String.valueOf(param1String2));
            return this;
        }
        
        public LogListBlock put(String param1String, LogListBlock param1LogListBlock) {
            updateKey(param1String);
            this.items.put(param1String, param1LogListBlock);
            return this;
        }
        
        public LogListBlock put(String param1String, Object param1Object) {
            put(param1String, String.valueOf(param1Object));
            return this;
        }
        
        public LogListBlock put(String param1String1, String param1String2, Object... param1VarArgs) {
            put(param1String1, String.format(param1String2, param1VarArgs));
            return this;
        }
        
        public LogListBlock put(String param1String, int param1Int) {
            put(param1String, String.valueOf(param1Int));
            return this;
        }
        
        public LogListBlock put(String param1String, byte param1Byte) {
            put(param1String, String.valueOf(param1Byte));
            return this;
        }
        
        public LogListBlock put(String param1String, double param1Double) {
            put(param1String, String.valueOf(param1Double));
            return this;
        }
        
        public LogListBlock put(String param1String, float param1Float) {
            put(param1String, String.valueOf(param1Float));
            return this;
        }
        
        public LogListBlock put(String param1String, short param1Short) {
            put(param1String, String.valueOf(param1Short));
            return this;
        }
        
        public LogListBlock put(String param1String, long param1Long) {
            put(param1String, String.valueOf(param1Long));
            return this;
        }
        
        public LogListBlock put(String param1String, boolean param1Boolean) {
            put(param1String, String.valueOf(param1Boolean));
            return this;
        }
        
        public LogListBlock putChild(String param1String) {
            updateKey(param1String);
            LogListBlock logListBlock = new LogListBlock();
            this.items.put(param1String, logListBlock);
            return logListBlock;
        }
        
        private String padKey(String param1String, int param1Int) {
            return String.format("%-" + param1Int + "s", param1String);
        }
        
        protected String getOutput(String param1String) {
            StringBuilder stringBuilder = new StringBuilder();
            for (Map.Entry<String, Object> entry : this.items.entrySet()) {
                Object object = entry.getValue();
                if (object instanceof LogListBlock) {
                    stringBuilder.append(param1String);
                    stringBuilder.append(padKey((String)entry.getKey(), this.maxKeyLength));
                    stringBuilder.append(":\r\n");
                    stringBuilder.append(((LogListBlock)object).getOutput(param1String + "    "));
                    continue;
                } 
                stringBuilder.append(param1String);
                stringBuilder.append(padKey((String)entry.getKey(), this.maxKeyLength));
                stringBuilder.append(": ");
                stringBuilder.append(object.toString());
                stringBuilder.append("\r\n");
            } 
            return stringBuilder.toString();
        }
        
        public String toString() {
            return getOutput("");
        }
    }
}
