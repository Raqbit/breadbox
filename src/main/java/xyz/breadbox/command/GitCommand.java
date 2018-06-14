package xyz.breadbox.command;

import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.RequestBuffer;
import xyz.breadbox.BreadboxCommand;

public class GitCommand implements BreadboxCommand {

    @Override
    public void handle(IMessage message) {
        RequestBuffer.request(() -> {
            message.getChannel().sendMessage("GIT WORLD HELLO!");
        });
    }
}
