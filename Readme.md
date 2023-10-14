# JavaIM示例插件项目
## 开发者食用方法
将JavaIM最新Jar文件改名为JavaIM.jar放到示例插件项目下的dep文件夹(重要！无此操作，插件可能不会支持最新版本的JavaIM,但当javaim发生主要更新，将会更新此jar）

然后根据正常java项目进行开发

### 一些提示
src/main/org/example/JavaIMExamplePlugin/PluginMain.java
onLoad为插件加载时被执行的代码，您可进行自定义提示/注册等操作
onUnload为插件卸载时执行的代码，您可进行自定义提示/取消注册等操作

src/main/org/example/JavaIMExamplePlugin/Listener.java
此文件为插件事件监听器的代码所在，带有@EventHandler注解的方法将会被自动处理为插件事件监听器，如果格式正确，JavaIM将会在发生此事件时调用
例如：
```java
    @EventHandler
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
```
此方法将会在出现有指令被执行时触发，方法名可以为任意

src/main/org/example/JavaIMExamplePlugin/TestCommand.java
此文件为示例CommandExecutor，当此class的实例被绑定到某个指令时，此class的execute方法将会被执行

### 如果您要重构项目名称，请进行以下修改
打开pom.xml 找到

```xml
    <groupId>org.example</groupId>
    <artifactId>JavaIMExamplePlugin</artifactId>
    <version>1.0-SNAPSHOT</version>
```
将groupId设置为包ID，artifactId设置为工件id

打开src/main/resources/PluginManifest.properties

修改src/main/java/org/example/JavaIMExamplePlugin为src/main/java/ PackageID,但是.要换成目录名 / 工件ID

找到Main-Class=org.example.JavaIMExamplePlugin.PluginMain

将他修改为Main-Class=PackageID.工件id.主class名
