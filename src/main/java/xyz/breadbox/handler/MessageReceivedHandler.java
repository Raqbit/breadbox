package xyz.breadbox.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import xyz.breadbox.BreadboxApplication;
import xyz.breadbox.BreadboxCommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles everything related to messages being received.
 */
public class MessageReceivedHandler implements IListener<MessageReceivedEvent> {

    private static final String commandPrefix = ".b";
    private static Logger logger = LoggerFactory.getLogger(MessageReceivedHandler.class);

    /**
     * Handle the receiving of a message.
     *
     * @param event the message received event
     */
    @Override
    public void handle(MessageReceivedEvent event) {
        if (!"breadbot-test-spam".equals(event.getChannel().getName())) {
            return;
        }
        String content = event.getMessage().getContent();
        String[] args = content.split(" ");
        boolean isCommand = args.length > 0 && commandPrefix.equals(args[0]);
        if (isCommand) {
            handleCommand(event, args);
            return;
        }
        String displayName = event.getAuthor().getName();
        logger.debug("User: {}, message: {}: {}", displayName, event.getMessageID(), event.getMessage());
    }

    /**
     * Handle the execution of a command.
     *
     * @param event the original message received event
     * @param args  the arguments for the command
     */
    private void handleCommand(MessageReceivedEvent event, String[] args) {
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        logger.debug(Arrays.toString(arguments.toArray()));
        if (arguments.size() < 2) {
            return;
        }
        String commandString = arguments.get(1);
        BreadboxCommand command = BreadboxApplication.getCommand(commandString);
        if (command == null) {
            event.getChannel().sendMessage(String.format("Command `%s` does not exist.", commandString));
            return;
        }
        arguments.removeAll(Arrays.asList(commandPrefix, commandString));
        command.handle(event.getMessage(), arguments);
    }
}
