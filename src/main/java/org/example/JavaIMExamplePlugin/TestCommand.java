package org.example.JavaIMExamplePlugin;

import org.yuezhikong.newServer.ServerTools;
import org.yuezhikong.newServer.UserData.user;
import org.yuezhikong.newServer.plugin.command.CommandExecutor;

public class TestCommand implements CommandExecutor {
    @Override
    public void execute(user User, String Command, String[] argv) {
        ServerTools.getServerInstanceOrThrow().getServerAPI().SendMessageToUser(User,"Hello,World");
    }
}
