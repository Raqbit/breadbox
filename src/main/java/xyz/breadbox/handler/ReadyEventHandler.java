package xyz.breadbox.handler;

import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;
import sx.blah.discord.util.RequestBuffer;

public class ReadyEventHandler implements IListener<ReadyEvent> {

    @Override
    public void handle(ReadyEvent event) {
        IDiscordClient client = event.getClient();
        System.out.println(client.getApplicationName());
        if (!client.getOurUser().getName().equals(client.getApplicationName())) {
            RequestBuffer.request(() -> client.changeUsername("BreadBox"));
        }
    }
}
