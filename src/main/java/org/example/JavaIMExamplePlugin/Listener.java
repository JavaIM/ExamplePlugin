package org.example.JavaIMExamplePlugin;

import org.yuezhikong.newServer.plugin.Tools;
import org.yuezhikong.newServer.plugin.event.EventHandler;
import org.yuezhikong.newServer.plugin.event.events.UserCommandEvent;
import org.yuezhikong.newServer.plugin.event.events.UserLoginEvent;

public class Listener implements org.yuezhikong.newServer.plugin.event.Listener {
    @EventHandler
    @SuppressWarnings("unused")
    public void onUserLogin(UserLoginEvent event)
    {
        Tools.getServerInstance().getLogger().info("用户："+event.UserName()+"登录成功！");
    }

    @EventHandler
    @SuppressWarnings("unused")
    public void onUserCommand(UserCommandEvent event)
    {
        StringBuilder Args = new StringBuilder();
        for (String arg : event.getCommand().argv())
        {
            Args.append(arg).append(" ");
        }
        if (Args.length() > 0)
            Args.deleteCharAt(Args.length() - 1);
        Tools.getServerInstance().getLogger().info
                ("用户："+event.getUserData().getUserName()+"执行了命令："
                        +event.getCommand().Command()+"参数："+ Args);
    }
}
