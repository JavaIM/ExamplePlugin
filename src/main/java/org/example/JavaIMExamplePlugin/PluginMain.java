package org.example.JavaIMExamplePlugin;

import org.yuezhikong.newServer.plugin.Plugin.JavaPlugin;
import org.yuezhikong.newServer.plugin.Tools;
import org.yuezhikong.newServer.plugin.configuration.PluginConfig;

import java.util.Properties;

public class PluginMain extends JavaPlugin {

    private final String commandName = "test";
    private Listener listener;
    @Override
    public void onLoad() {
        Tools.getServerInstance().getLogger().info("[ExamplePlugin] 插件正在被加载");
        PluginConfig.SaveDefaultConfiguration(this);//释放默认插件配置文件
        Properties config = PluginConfig.getConfiguration(this);
        if (config == null) {
            Tools.getServerInstance().getLogger().error("无法获取配置文件");
        }
        else {
            if (config.getProperty("Enable-Example-Command-System").equals("true")) {
                if (!Tools.getServerInstance().getPluginManager().
                        RegisterCommand(commandName, "一个测试命令",
                                new TestCommand(), this)) {
                    Tools.getServerInstance().getLogger().error("无法注册指令：/test");
                }
            }
            if (config.getProperty("Enable-Example-Event-System").equals("true")) {
                listener = new Listener();
                Tools.getServerInstance().getPluginManager().AddEventListener(listener,this);
            }
        }
        Tools.getServerInstance().getLogger().info("[ExamplePlugin] 插件加载成功");
    }

    @Override
    public void onUnload() {
        Tools.getServerInstance().getLogger().info("[ExamplePlugin] 插件正在被卸载");
        Tools.getServerInstance().getPluginManager().UnRegisterCommand(commandName);
        if (listener != null)
            Tools.getServerInstance().getPluginManager().RemoveEventListener(listener,this);
        Tools.getServerInstance().getLogger().info("[ExamplePlugin] 插件卸载成功");
    }

}
