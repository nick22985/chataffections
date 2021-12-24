package me.nick22985.chatAffections;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;
import org.mineacademy.fo.collection.SerializedMap;
import org.mineacademy.fo.constants.FoConstants;
import org.mineacademy.fo.remain.Remain;
import org.mineacademy.fo.settings.YamlSectionConfig;

import lombok.Getter;

/**
 * A sample player cache storing permanent player information
 * to data.db or MySQL database for players.
 */
@Getter
public final class PlayerCache extends YamlSectionConfig {

    /**
     * The player cache map caching data for players online.
     */
    private static volatile Map<UUID, PlayerCache> cacheMap = new HashMap<>();

    /**
     * This instance's player's unique id
     */
    private final UUID uniqueId;

    /**
     * This instance's player's name
     */
    private final String playerName;

    //
    // Store any custom saveable data here
    //

    /*
     * Creates a new player cache (see the bottom)
     */
    private PlayerCache(String name, UUID uniqueId) {
        super("Players." + uniqueId.toString());

        this.playerName = name;
        this.uniqueId = uniqueId;

        this.loadConfiguration(NO_DEFAULT, FoConstants.File.DATA);
    }

    /**
     * Automatically called when loading data from disk.
     *
     * @see org.mineacademy.fo.settings.YamlConfig#onLoadFinish()
     */
    @Override
    protected void onLoadFinish() {
        //
        // Load any custom fields here, example:
        // this.chatColor = get("Chat_Color", CompChatColor.class);
        //
    }

    /**
     * Convert data in this class into a map that can
     * be saved into file or written to the databaes.
     *
     * @return
     */
    @Override
    public SerializedMap serialize() {
        final SerializedMap map = new SerializedMap();

        //
        // Save any custom fields here, example:
        // map.putIf("Chat_Color", this.chatColor);
        //

        return map;
    }

    /* ------------------------------------------------------------------------------- */
    /* Data-related methods */
    /* ------------------------------------------------------------------------------- */

    //
    // Implement your own data getter/setters here according to this example:
    //

    //public boolean hasChatColor() {
    //	return this.chatColor != null;
    //}

    //public void setChatColor(@Nullable CompChatColor chatColor) {
    //	this.chatColor = chatColor;
    //
    //	save();
    //}

    /* ------------------------------------------------------------------------------- */
    /* Misc methods */
    /* ------------------------------------------------------------------------------- */

    /**
     * Return player from cache if online or null otherwise
     *
     * @return
     */
    @Nullable
    public Player toPlayer() {
        final Player player = Remain.getPlayerByUUID(this.uniqueId);

        return player != null && player.isOnline() ? player : null;
    }

    /**
     * Remove this cached data from memory if it exists
     *
     * @param uniqueId
     */
    public void removeFromMemory() {
        synchronized (cacheMap) {
            cacheMap.remove(this.uniqueId);
        }
    }

    /**
     * @see org.mineacademy.fo.settings.YamlSectionConfig#toString()
     */
    @Override
    public String toString() {
        return "PlayerCache{" + this.playerName + ", " + this.uniqueId + "}";
    }

    /* ------------------------------------------------------------------------------- */
    /* Static access */
    /* ------------------------------------------------------------------------------- */

    /**
     * Return or create new player cache for the given player
     *
     * @param player
     * @return
     */
    public static PlayerCache from(Player player) {
        synchronized (cacheMap) {
            final UUID uniqueId = player.getUniqueId();
            final String playerName = player.getName();

            PlayerCache cache = cacheMap.get(uniqueId);

            if (cache == null) {
                cache = new PlayerCache(playerName, uniqueId);

                cacheMap.put(uniqueId, cache);
            }

            return cache;
        }
    }

    /**
     * Clear the entire cache map
     */
    public static void clear() {
        synchronized (cacheMap) {
            cacheMap.clear();
        }
    }
}
