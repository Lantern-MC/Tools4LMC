File spigot = new File("");
File plugins = new File(spigot.getAbsolutePath() + File.separator + "plugins");
List<File> files = Explorer.searchFiles(plugins, pluginname);
//                    if (files.size() == 0) {
////                        sender.sendMessage(plugins.getAbsolutePath());
//                        sender.sendMessage("§4没找到名为" + pluginname + "的插件");
//                    } else {
//                        sender.sendMessage("共找到:" + files.size() + "个文件");
//                        for (File file : files) {
//                            System.out.println(file.getAbsolutePath());
Tools4LMCSpigot.getheart().getPluginLoader().loadPlugin(PluginName);
if (Tools4LMCSpigot.getheart().getServer().getPluginManager().isPluginEnabled(pluginname)) {
sender.sendMessage("§a" + pluginname + "加载成功");
}

//                            }
//                        }