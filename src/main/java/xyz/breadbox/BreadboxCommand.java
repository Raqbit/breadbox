package xyz.breadbox;

import sx.blah.discord.handle.obj.IMessage;

import java.util.List;

/**
 * Interface for any command.
 */
public interface BreadboxCommand {

    /**
     * Handle the execution of the command.
     */
    void handle(IMessage message, List<String> args);
}
