package xyz.breadbox.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import xyz.breadbox.BreadboxApplication;
import xyz.breadbox.BreadboxCommand;

import java.util.Arrays;

public class MessageReceivedHandler implements IListener<MessageReceivedEvent> {

    private static Logger logger = LoggerFactory.getLogger(MessageReceivedHandler.class);

    @Override
    public void handle(MessageReceivedEvent event) {
        if (!"breadbot-test-spam".equals(event.getChannel().getName())) {
            return;
        }
        String content = event.getMessage().getContent();
        String[] args = content.split(" ");
        boolean isCommand = args.length > 0 && ".b".equals(args[0]);
        if (isCommand) {
            handleCommand(event, args);
            return;
        }
        String displayName = event.getAuthor().getName();
        logger.debug("User: {}, message: {}: {}", displayName, event.getMessageID(), event.getMessage());
    }

    private void handleCommand(MessageReceivedEvent event, String[] args) {
        logger.debug(Arrays.toString(args));
        if (args.length < 2) {
            return;
        }
        String commandString = args[1];
        BreadboxCommand command = BreadboxApplication.getCommand(commandString);
        if (command == null) {
            event.getChannel().sendMessage(String.format("Command `%s` does not exist.", commandString));
            return;
        }
        command.handle(event.getMessage());
    }
}
