package io.github.blamebutton.breadbox;

import io.github.blamebutton.breadbox.command.BreadboxCommand;
import io.github.blamebutton.breadbox.handler.CommandHandler;
import io.github.blamebutton.breadbox.handler.ReadyEventHandler;
import io.github.blamebutton.breadbox.util.Environment;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;

import java.util.HashMap;
import java.util.Map;

public class BreadboxApplication {

    public static BreadboxApplication instance;

    private final String token;
    private final Map<String, BreadboxCommand> commands = new HashMap<>();
    private Environment environment;

    private BreadboxApplication() {
        this(System.getenv("BREADBOX_TOKEN"));
    }

    /**
     * Mainly used for testing.
     *
     * @param token the token to use
     */
    BreadboxApplication(String token) {
        this.token = token;
        setup(token);
    }

    /**
     * Get a command from the {@link BreadboxApplication#commands} hashmap.
     *
     * @param command the command name
     * @return the command instance
     */
    public static BreadboxCommand getCommand(String command) {
        return instance.commands.get(command);
    }

    /**
     * Run the Discord Bot.
     *
     * @param args the command line parameters
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            instance = new BreadboxApplication(args[0]);
        } else {
            instance = new BreadboxApplication();
        }
    }

    /**
     * Get all the commands.
     *
     * @return all the commands
     */
    public static Map<String, BreadboxCommand> getCommands() {
        return instance.commands;
    }

    String getToken() {
        return token;
    }

    /**
     * Set up the bot, connect to Discord API.
     *
     * @param token the Discord bot token
     */
    private void setup(String token) {
        environment = Environment.find(System.getenv("BREADBOX_ENV"));
        IDiscordClient client = BreadboxAuthentication.createClient(token);
        if (client != null) {
            EventDispatcher dispatcher = client.getDispatcher();
            dispatcher.registerListener(new ReadyEventHandler());
            dispatcher.registerListener(new CommandHandler());
        }
    }

    public void registerCommand(String command, BreadboxCommand clazz) {
        instance.commands.put(command, clazz);
    }

    public Environment getEnvironment() {
        return environment;
    }
}
