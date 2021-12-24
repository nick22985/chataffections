package me.nick22985.chatAffections.command;

import java.util.List;

import me.nick22985.chatAffections.model.Feelings;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.mineacademy.fo.PlayerUtil;
import org.mineacademy.fo.command.SimpleCommand;
import org.mineacademy.fo.settings.Lang;

/**
 * A sample standalone command.
 */
public final class UserCommands extends SimpleCommand {

    public Feelings feeling;

    public void setFeeling(Feelings feeling) {
        this.feeling = feeling;
    }

    public Feelings getFeeling() {
        return feeling;
    }

    /**
     * Create a new standalone command /sample
     */
    public UserCommands(Feelings feeling) {
        super(feeling.getName());
        setFeeling(feeling);

//        setUsage("<args...>");
//        setDescription("An example command");
        setMinArguments(1);
//        setPermission("chatfeelings.command.general");

        // Uncomment to get rid of the automatically generated help text: https://i.imgur.com/Q79RKN0.png
        setAutoHandleHelp(false);
    }

//    @Override
//    protected String[] getMultilineUsageMessage() {
//        return new String[]{
//                "{label} An example command that will print",
//                "what's being written into it."
//        };
//    }

    /**
     * Perform the main command logic.
     */
    @Override
    protected void onCommand() {
        checkConsole();
        Feelings feeling = getFeeling();
        findOfflinePlayer(args[0], targetPlayer -> {
            if (targetPlayer.isOnline() && targetPlayer.isBanned()) {
                if (!PlayerUtil.isVanished((Player) targetPlayer) || hasPerm("chataffections.admin")) {
                    if (targetPlayer.getName() == sender.getName())
                        tellError("Can not {label} yourself");
                    else {
                        ((Player) targetPlayer).playSound(((Player) targetPlayer).getLocation(), Sound.ENTITY_CAT_PURREOW, 1F, 1F);
                        ((Player) sender).playSound(((Player) sender).getLocation(), Sound.ENTITY_CAT_PURREOW, 1F, 1F);
                        send(sender, feeling.sender.replace("%player%", targetPlayer.getName()));
                        send((CommandSender) targetPlayer, feeling.target.replace("%player%", sender.getName()));
                    }
                } else {
                    tellError(Lang.of("Player.Not_Online").replace("{player}", targetPlayer.getName()));
                }
            } else {
                tellError(Lang.of("Player.Not_Online").replace("{player}", targetPlayer.getName()));
            }
        });
    }

    protected void send(CommandSender s, String msg) {
        s.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    /**
     * @see org.mineacademy.fo.command.SimpleCommand#tabComplete()
     */
    @Override
    protected List<String> tabComplete() {
        switch (args.length) {
            case 1:
                return completeLastWordPlayerNames();
        }
        return NO_COMPLETE;
    }
}
