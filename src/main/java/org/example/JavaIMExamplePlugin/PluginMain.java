package org.example.JavaIMExamplePlugin;

import org.yuezhikong.newServer.ServerTools;
import org.yuezhikong.newServer.plugin.Plugin.JavaPlugin;
import org.yuezhikong.newServer.plugin.configuration.PluginConfig;

import java.util.Properties;

public class PluginMain extends JavaPlugin {

    private Listener listener;
    @Override
    public void onLoad() {
        ServerTools.getServerInstanceOrThrow().getLogger().info("[ExamplePlugin] 插件正在被加载");
        PluginConfig.SaveDefaultConfiguration(this);//释放默认插件配置文件
        Properties config = PluginConfig.getConfiguration(this);
        if (config == null) {
            ServerTools.getServerInstanceOrThrow().getLogger().error("无法获取配置文件");
        }
        else {
            if (config.getProperty("Enable-Example-Command-System").equals("true")) {
                if (!ServerTools.getServerInstanceOrThrow().getPluginManager().
                        RegisterCommand("test", "一个测试命令",
                                new TestCommand(), this)) {
                    ServerTools.getServerInstanceOrThrow().getLogger().error("无法注册指令：/test");
                }
            }
            if (config.getProperty("Enable-Example-Event-System").equals("true")) {
                listener = new Listener();
                ServerTools.getServerInstanceOrThrow().getPluginManager().AddEventListener(listener,this);
            }
        }
        ServerTools.getServerInstanceOrThrow().getLogger().info("[ExamplePlugin] 插件加载成功");
    }

    @Override
    public void onUnload() {
        ServerTools.getServerInstanceOrThrow().getLogger().info("[ExamplePlugin] 插件正在被卸载");
        ServerTools.getServerInstanceOrThrow().getPluginManager().UnRegisterCommand("test");
        if (listener != null)
            ServerTools.getServerInstanceOrThrow().getPluginManager().RemoveEventListener(listener,this);
        ServerTools.getServerInstanceOrThrow().getLogger().info("[ExamplePlugin] 插件卸载成功");
    }

}
