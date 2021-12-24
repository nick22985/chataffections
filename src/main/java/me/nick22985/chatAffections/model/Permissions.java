package me.nick22985.chatAffections.model;

import org.mineacademy.fo.annotation.Permission;
import org.mineacademy.fo.annotation.PermissionGroup;
import org.mineacademy.fo.command.PermsCommand;
import org.mineacademy.fo.constants.FoPermissions;

/**
 * A sample permissions class. This is the preferred way of keeping all permissions
 * of your plugin in one place.
 * <p>
 * You will also be able to use the {@link PermsCommand} to list them automatically
 * if you choose to this class.
 */
public final class Permissions extends FoPermissions {

    /**
     * A sample permission group for your convenience. The {@link PermissionGroup}
     * is used in the {@link PermsCommand} for your convenience automatically.
     */
    @PermissionGroup("Execute main plugin command for /{label}.")
    public static final class Command {

        @Permission("Sends a sample message")
        public static final String SAMPLE = "plugintemplate.command.sample";
        public static final String GENERAL = "plugintemplate.command.general";

    }
}
