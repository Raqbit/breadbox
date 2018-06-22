package io.github.blamebutton.breadbox.handler;

import io.github.blamebutton.breadbox.BreadboxApplication;
import io.github.blamebutton.breadbox.command.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.events.EventSubscriber;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEditEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageEvent;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.RequestBuffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Handles everything related to messages being received.
 */
public class CommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(CommandHandler.class);

    private static final List<String> COMMAND_PREFIXES = Arrays.asList("?", Character.toString('\u00bf'));

    /**
     * Handle the receiving of a message.
     *
     * @param event the message received event
     */
    @SuppressWarnings("unused")
    @EventSubscriber
    public void handle(MessageReceivedEvent event) {
        messageReceived(event);
    }

    @SuppressWarnings("unused")
    @EventSubscriber
    public void handle(MessageEditEvent event) {
        messageReceived(event);
    }

    private void messageReceived(MessageEvent event) {
        String content = event.getMessage().getContent();
        String[] args = content.split(" ");
        String firstArgument = args[0];
        boolean isCommand = COMMAND_PREFIXES.contains(firstArgument.substring(0, 1));
        if (isCommand) {
            handleCommand(event, args);
            return;
        }
        String displayName = event.getAuthor().getName();
        logger.debug("User: {}, message: {}: {}", displayName, event.getMessageID(), content);
    }

    /**
     * Handle the execution of a command.
     *
     * @param event the original message received event
     * @param args  the arguments for the command
     */
    private void handleCommand(MessageEvent event, String[] args) {
        List<String> arguments = new ArrayList<>(Arrays.asList(args));
        if (arguments.size() < 1) {
            return;
        }
        String command = arguments.get(0).substring(1);
        arguments.remove(0);
        callCommand(event, command, arguments);
    }

    /**
     * Call a specific command.
     *
     * @param event     the message event
     * @param command   the command to execute
     * @param arguments the arguments for the command
     */
    private void callCommand(MessageEvent event, String command, List<String> arguments) {
        ICommand cmd = BreadboxApplication.instance.getCommand(command);
        if (cmd == null) {
            RequestBuffer.request(() -> {
                String message = String.format("Command `%s` does not exist.", command);
                event.getChannel().sendMessage(message);
            });
            return;
        }
        logger.debug("Command '{}' arguments: {}", command, Arrays.toString(arguments.toArray()));
        cmd.handle(event.getMessage(), arguments);
    }
}
