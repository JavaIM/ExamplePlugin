package org.example.JavaIMExamplePlugin;

import org.yuezhikong.newServer.UserData.user;
import org.yuezhikong.newServer.plugin.Tools;
import org.yuezhikong.newServer.plugin.command.CommandExecutor;

public class TestCommand implements CommandExecutor {
    @Override
    public void execute(user User, String Command, String[] argv) {
        Tools.getServerInstance().getServerAPI().SendMessageToUser(User,"Hello,World");
    }
}
