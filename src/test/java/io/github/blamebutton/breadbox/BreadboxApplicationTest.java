package io.github.blamebutton.breadbox;

import io.github.blamebutton.breadbox.command.ICommand;
import io.github.blamebutton.breadbox.util.Environment;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.lang3.NotImplementedException;
import org.junit.jupiter.api.Test;
import sx.blah.discord.handle.obj.IMessage;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class BreadboxApplicationTest extends BaseTest {

    @Test
    void main() {
        assertEquals(app.getToken(), token, "Token did not equal.");
    }

    @Test
    void getCommand() {
        String commandName = "get-command-test";

        app.registerCommand(commandName, new ICommand() {
            @Override
            public void handle(IMessage message, CommandLine commandLine) {
            }

            @Override
            public String getUsage() {
                return "usage";
            }

            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public Options getOptions() {
                return null;
            }
        });
        ICommand command = app.getCommand(commandName);
        assertNotNull(command);
        assertEquals(command.getUsage(), "usage");
    }

    @Test
    void getCommands() {
        String commandName = "get-commands-test";

        app.registerCommand(commandName, (ICommand) null);
        Map<String, ICommand> commands = app.getCommands();
        boolean contains = commands.containsKey(commandName);
        assertTrue(contains);
    }

    @Test
    void getToken() {
    }

    @Test
    void registerCommand() {
        String commandName = "register-command-test";
        app.registerCommand(commandName, new ICommand() {
            @Override
            public void handle(IMessage message, CommandLine commandLine) {
                throw new NotImplementedException("Not implemented.");
            }

            @Override
            public String getUsage() {
                return "test-command <arg> [arg]";
            }

            @Override
            public String getDescription() {
                return "Command for testing purposes.";
            }

            @Override
            public Options getOptions() {
                return null;
            }
        });

        Map<String, ICommand> commands = app.getCommands();

        boolean contains = commands.containsKey(commandName);
        ICommand command = commands.get(commandName);
        assertTrue(contains);
        assertEquals(command.getUsage(), "test-command <arg> [arg]");
        assertEquals(command.getDescription(), "Command for testing purposes.");
        assertThrows(NotImplementedException.class, () -> command.handle(null, null));
    }

    @Test
    void getEnvironment() {
        assertEquals(Environment.LOCAL, app.getEnvironment());
    }
}