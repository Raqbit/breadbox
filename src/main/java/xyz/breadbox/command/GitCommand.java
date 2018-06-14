package xyz.breadbox.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.RequestBuffer;
import xyz.breadbox.BreadboxCommand;

import java.util.Arrays;
import java.util.List;

public class GitCommand implements BreadboxCommand {

    private static Logger logger = LoggerFactory.getLogger(GitCommand.class);

    @Override
    public void handle(IMessage message, List<String> args) {
        RequestBuffer.request(() -> {
            message.getChannel().sendMessage("GIT WORLD HELLO!");
        });
        logger.debug(Arrays.toString(args.toArray()));
    }
}
