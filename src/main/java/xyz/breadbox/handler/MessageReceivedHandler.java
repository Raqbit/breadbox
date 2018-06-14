package xyz.breadbox.handler;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.guild.channel.message.MessageReceivedEvent;
import sx.blah.discord.util.RequestBuffer;
import xyz.breadbox.BreadboxCommand;
import xyz.breadbox.command.GitCommand;

import java.util.HashMap;
import java.util.Map;

public class MessageReceivedHandler implements IListener<MessageReceivedEvent> {

    private Map<String, Class<? extends BreadboxCommand>> commands = new HashMap<>();

    @Override
    public void handle(MessageReceivedEvent event) {
        if (!"breadbot-test-spam".equals(event.getChannel().getName())) {
            return;
        }

        String displayName = event.getAuthor().getName();
        System.out.println(String.format("User: %s, message: %s: %s", displayName, event.getMessageID(), event.getMessage()));
        RequestBuffer.request(() -> {
            event.getMessage().delete();
            event.getChannel().sendMessage("I got your message " + displayName + " !");
        });
        registerCommand("git", GitCommand.class);
    }

    private void registerCommand(String command, Class<? extends BreadboxCommand> clazz) {
        commands.put(command, clazz);
    }
}
