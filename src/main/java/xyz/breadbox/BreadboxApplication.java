package xyz.breadbox;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.EventDispatcher;
import xyz.breadbox.handler.MessageReceivedHandler;
import xyz.breadbox.handler.ReadyEventHandler;

public class BreadboxApplication {

    private final String token;

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
        BreadboxApplication instance;
        if (args.length > 0) {
            instance = new BreadboxApplication(args[0]);
        } else {
            instance = new BreadboxApplication();
        }
    }

    String getToken() {
        return token;
    }
}
