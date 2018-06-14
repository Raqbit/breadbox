package io.github.blamebutton.breadbox.handler;

import io.github.blamebutton.breadbox.BreadboxApplication;
import io.github.blamebutton.breadbox.command.BreadboxCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEditEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles everything related to messages being received.
 */
public class CommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommandHandler.class);

    private static final String commandPrefix = ".b";
    private static final String testChannel = "breadbot-test-spam";

    /**
     * Handle the receiving of a message.
     *
     * @param event the message received event
     */
    @EventSubscriber
    public void handle(MessageReceivedEvent event) {
        messageReceived(event);
    }

    @EventSubscriber
    public void handle(MessageEditEvent event) {
        messageReceived(event);
    }

    private void messageReceived(MessageEvent event) {
        if (!event.getChannel().isPrivate() && !testChannel.equals(event.getChannel().getName())) {
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
     *  @param event the original message received event
     * @param args  the arguments for the command
     */
    private void handleCommand(MessageEvent event, String[] args) {
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
        arguments.remove(0);
        arguments.remove(0);
        command.handle(event.getMessage(), arguments);
    }
}
