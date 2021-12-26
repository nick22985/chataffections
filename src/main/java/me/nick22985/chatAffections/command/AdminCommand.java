package me.nick22985.chatAffections.command;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import me.nick22985.chatAffections.model.Feelings;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.plugin.SimplePlugin;
import org.mineacademy.fo.settings.Lang;

import static java.lang.Boolean.parseBoolean;
import static org.mineacademy.fo.remain.Remain.registerCommand;


/**
 * A sample standalone command.
 */
public final class AdminCommand extends SimpleCommand {
    /**
     * Create a new standalone command /sample
     */
    public AdminCommand(String command) {
        super(command);
        setUsage("<args...>");
        setDescription("An example command");
        setMinArguments(1);
        setPermission("chataffections.admin");
        List<String> alliases = new ArrayList<>();
        alliases.add("ca");
        setAliases(alliases);

        // Uncomment to get rid of the automatically generated help text: https://i.imgur.com/Q79RKN0.png
        //setAutoHandleHelp(false);
    }

    @Override
    protected String[] getMultilineUsageMessage() {
        return new String[]{
                "{label} An example command that will print",
                "what's being written into it."
        };
    }

    /**
     * Perform the main command logic.
     */
    @Override
    protected void onCommand() {
        String param = args[0].toLowerCase();
        checkBoolean("add".equals(param) || "remove".equals(param) || "reload".equals(param), "Invalid parameter, use add or remove, not {0}");
        if ("reload".equals(param)) {
            //TODO reload plugin

        }
        if ("remove".equals(param)) {
            tellSuccess(Lang.of("Commands.Command_Removed").replace("%command%", args[1]));
        }
        checkArgs(2, "Usage /{label} <add> <name> <toSender> <toTarget> <?permissionNode> <true/false>");
        if ("add".equals(param)) {
            // TODO Save to file
            // FIXME set default values in the Feelings class
            if (args[4].length() == 0) {
                args[4] = "";
            }
            if (args[5].length() == 0) {
                args[5] = "true";
            }
            Feelings feeling = new Feelings(args[1].toLowerCase(), args[2], args[3], args[4], parseBoolean(args[5]));

            registerCommand(new UserCommands(feeling));
            tellSuccess(Lang.of("Commands.Command_Added").replace("%command%", args[1]));
        }


    }

    /**
     * @see org.mineacademy.fo.command.SimpleCommand#tabComplete()
     */
    @Override
    protected List<String> tabComplete() {
        switch (args.length) {
            case 1:
                return completeLastWord("add", "remove");
            case 6:
                return completeLastWord("true", false);
        }
        return NO_COMPLETE;
    }
}
