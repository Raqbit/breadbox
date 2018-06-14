package xyz.breadbox.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.RequestBuffer;
import xyz.breadbox.BreadboxApplication;
import xyz.breadbox.command.GitCommand;

public class ReadyEventHandler implements IListener<ReadyEvent> {

    private static Logger logger = LoggerFactory.getLogger(ReadyEventHandler.class);
    private final String name = "BreadBox";

    @Override
    public void handle(ReadyEvent event) {
        IDiscordClient client = event.getClient();
        String applicationName = client.getApplicationName();
        logger.info("Current application name '{}'", applicationName);
        if (!client.getOurUser().getName().equals(applicationName)) {
            RequestBuffer.request(() -> client.changeUsername(name));
            logger.debug("Changed name from {} to {}", event.getClient().getOurUser().getName(), name);
        } else {
            logger.debug("Name already matches.");
        }
        registerCommands();
    }

    /**
     * Register all the application commands.
     */
    private void registerCommands() {
        BreadboxApplication instance = BreadboxApplication.instance;
        instance.registerCommand("git", new GitCommand());
    }
}
