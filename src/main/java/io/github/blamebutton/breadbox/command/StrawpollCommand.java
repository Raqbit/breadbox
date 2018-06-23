package io.github.blamebutton.breadbox.command;

import io.github.blamebutton.breadbox.util.I18n;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sx.blah.discord.handle.obj.IMessage;

/**
 * Command for creating strawpolls.
 *
 * <pre>
 * Usage: ?strawpoll &lt;options>
 * </pre>
 */
public class StrawpollCommand implements ICommand {

    private static final Logger logger = LoggerFactory.getLogger(StrawpollCommand.class);

    @Override
    public void handle(IMessage message, CommandLine commandLine) {
        message.getChannel().sendMessage("got it");
    }

    @Override
    public String getUsage() {
        return I18n.get("command.strawpoll.usage");
    }

    @Override
    public String getDescription() {
        return I18n.get("command.strawpoll.description");
    }

    @Override
    public Options getOptions() {
        return null;
    }
}
