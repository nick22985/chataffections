package me.nick22985.chatAffections;

import me.nick22985.chatAffections.command.AdminCommand;
import me.nick22985.chatAffections.command.UserCommands;
import me.nick22985.chatAffections.model.Feelings;
import org.mineacademy.fo.Valid;
import org.mineacademy.fo.command.SimpleCommandGroup;
import org.mineacademy.fo.model.HookManager;
import org.mineacademy.fo.plugin.SimplePlugin;
import me.nick22985.chatAffections.command.SampleCommandGroup;
import me.nick22985.chatAffections.model.Packets;

import lombok.Getter;

import java.util.ArrayList;


/**
 * PluginTemplate is a simple template you can use every time you make
 * a new plugin. This will save you time because you no longer have to
 * recreate the same skeleton and features each time.
 * <p>
 * It uses Foundation for fast and efficient development process.
 */
public final class ChatAffectionsPlugin extends SimplePlugin {

    /**
     * Automatically registers the main command group. A command group holds different
     * commands, such as /chatcontrol is the main command group holding commands
     * /chatcontrol mute, /chatcontrol clear etc.
     */
    @Getter
    private final SimpleCommandGroup mainCommand = SampleCommandGroup.getInstance();

    /**
     * Automatically perform login ONCE when the plugin starts.
     */
    @Override
    protected void onPluginStart() {
    }

    /**
     * Automatically perform login when the plugin starts and each time it is reloaded.
     */
    @Override
    protected void onReloadablesStart() {

        // You can check for necessary plugins and disable loading if they are missing
        Valid.checkBoolean(HookManager.isVaultLoaded(), "You need to install Vault so that we can work with packets, offline player data, prefixes and groups.");

        // Load parts of the plugin
        Packets.load();

        // Uncomment to load variables
        // Variable.loadVariables();
        System.out.println("HERE---------------------------------------");
        //TODO import yaml file with commands
        ArrayList<Feelings> obj = new ArrayList<Feelings>();
        obj.add(new Feelings("hug", "&7You give &a&l%player% &r&7a warm hug. &cAwww &4❤", "&a&l%player% &r&7gives you a warm hug. &cAwww &4❤", "test", true));
        obj.add(new Feelings("bite", "&7You sink your teeth into &c&l%player_name%&r&7''s skin.", "&c&l%player% &r&7sinks their teeth into your skin.", "test", true));
        obj.add(new Feelings("Punch", "&7You strike &c&l%player% &r&7with a punch. Ouch!", "&c&l%player% &r&7strikes you with a punch. Ouch!", "test", false));
        obj.add(new Feelings("Murder", "&7You murder &c&l%player% &r&7and have no regrets.", "&c&l%player% &r&7just murdered you. Bandaid anyone?", "test", false));
        registerCommand(new AdminCommand("chataffection"));
        for (Feelings feeling : obj) {
            registerCommand(new UserCommands(feeling));
        }
    }

    @Override
    protected void onPluginReload() {
        reload();
    }

    /* ------------------------------------------------------------------------------- */
    /* Static */
    /* ------------------------------------------------------------------------------- */

    /**
     * Return the instance of this plugin, which simply refers to a static
     * field already created for you in SimplePlugin but casts it to your
     * specific plugin instance for your convenience.
     *
     * @return
     */
    public static ChatAffectionsPlugin getInstance() {
        return (ChatAffectionsPlugin) SimplePlugin.getInstance();
    }
}
