package xyz.breadbox.command;

import sx.blah.discord.handle.obj.IMessage;
import xyz.breadbox.BreadboxCommand;

public class GitCommand implements BreadboxCommand {
    @Override
    public void handle(IMessage message) {
        System.out.println("GIT COMMAND");
    }
}
