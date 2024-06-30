package org.example.JavaIMExamplePlugin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yuezhikong.Server.ServerTools;
import org.yuezhikong.Server.plugin.Plugin.JavaPlugin;
import org.yuezhikong.Server.plugin.event.EventHandler;
import org.yuezhikong.Server.plugin.event.Listener;
import org.yuezhikong.Server.plugin.event.events.Server.ServerStartSuccessfulEvent;

// 在此处实现 Listener 接口仅是为在同一文件内演示 JavaIM 启动阶段
// 您可以在其他类实现此接口，后在onLoad/onPreload(推荐在onLoad)注册
public class PluginMain extends JavaPlugin implements Listener {
    // 我们建议引入 lombok 依赖，并使用 @Slf4j 注解
    private final static Logger log = LoggerFactory.getLogger(PluginMain.class);

    @Override
    public void onPreload() {
        // 此阶段被设计于所有于所有启动操作之前调用
        // 此阶段，ServerAPI、ChatRequest、NetworkServer 等均未启动!
    }

    @Override
    public void onLoad() {
        // 此阶段被设计于启动过程中调用
        // 与数据库启动、网络启动并行
        // 请勿在此阶段操作网络、数据库等IO
        log.info("[ExamplePlugin] 插件正在被加载");
        ServerTools.getServerInstanceOrThrow().getPluginManager().addEventListener(this,this);
        log.info("[ExamplePlugin] 插件加载成功");
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onServerStartSuccessful(ServerStartSuccessfulEvent event) {
        // 此阶段被设计于启动结束后调用
        // 此阶段，启动计时等已全部结束，用户已经可以进入
        log.info("[ExamplePlugin] JavaIM 启动完成!");
    }

    @Override
    public void onUnload() {
        // 当插件卸载时
        // 一般情况下，此阶段在用户踢出后IO断开前调用
        log.info("[ExamplePlugin] 插件正在被卸载");
        ServerTools.getServerInstanceOrThrow().getPluginManager().removeEventListener(this,this);
        log.info("[ExamplePlugin] 插件卸载成功");
    }

}
