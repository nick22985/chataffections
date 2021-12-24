package me.nick22985.chatAffections.command;

import java.util.List;

import org.mineacademy.fo.command.SimpleSubCommand;

/**
 * A sample command belonging to a command group.
 */
public final class SampleGroupCommand extends SimpleSubCommand {

    /**
     * Create a new sample group command, such as /sample menu.
     */
    public SampleGroupCommand() {
        super("menu");

        setDescription("Opens the menu");
        //setPermission("chatcontrol.command.announce");
    }

    /**
     * Perform the main command logic.
     */
    @Override
    protected void onCommand() {
        checkConsole();

    }

    /**
     * @see org.mineacademy.fo.command.SimpleCommand#tabComplete()
     */
    @Override
    protected List<String> tabComplete() {
        return NO_COMPLETE;
    }
}
