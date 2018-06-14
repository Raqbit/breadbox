package xyz.breadbox;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import xyz.breadbox.handler.MessageReceivedHandler;
import xyz.breadbox.handler.ReadyEventHandler;

import java.util.HashMap;
import java.util.Map;

public class BreadboxApplication {

    private final String token;
    public static BreadboxApplication instance;
    private static Map<String, BreadboxCommand> commands = new HashMap<>();

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
        String tokenSubstring = token != null ? token.substring(0, 10) : "";
        System.out.println(String.format("Token: %s", tokenSubstring));

        IDiscordClient client = BreadboxAuthentication.createClient(token);
        if (client != null) {
            EventDispatcher dispatcher = client.getDispatcher();
            dispatcher.registerListener(new ReadyEventHandler());
            dispatcher.registerListener(new MessageReceivedHandler());
        }
    }

    /**
     * Run the Discord Bot.
     *
     * @param args the command line parameters
     */
    @SuppressWarnings({"unused", "UnusedAssignment"})
    public static void main(String[] args) {
        if (args.length > 0) {
            instance = new BreadboxApplication(args[0]);
        } else {
            instance = new BreadboxApplication();
        }
    }

    String getToken() {
        return token;
    }

    /**
     * Get a command from the {@link BreadboxApplication#commands} hashmap.
     *
     * @param command the command name
     * @return the command instance
     */
    public static BreadboxCommand getCommand(String command) {
        return commands.get(command);
    }

    /**
     * Get all the commands.
     *
     * @return all the commands
     */
    public static Map<String, BreadboxCommand> getCommands() {
        return commands;
    }

    public void registerCommand(String command, BreadboxCommand clazz) {
        commands.put(command, clazz);
    }
}
