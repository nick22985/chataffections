package me.nick22985.chatAffections.model;

import lombok.Getter;
import org.mineacademy.fo.constants.FoConstants;
import org.mineacademy.fo.model.ConfigSerializable;
import org.mineacademy.fo.settings.YamlConfig;

public class Feelings extends YamlConfig {

    public String name;
    public String sender;
    public String target;
    public String permissionNode;
    public Boolean enabled;

    public Feelings(String name, String sender, String target, String permissionNode, Boolean enabled) {
        this.name = name;
        this.sender = sender;
        this.target = target;
        this.permissionNode = permissionNode;
        this.enabled = enabled;
    }

    public String getName() {
        return this.name;
    }
}

//public class Feelings extends YamlConfig implements ConfigSerializable {
//    private String name;
//    private String sender;
//    private String target;
//    private String permissionNode;
//    private Boolean enabled;
//
//    public Feelings(final String name) {
//        setHeader("Welcome to Chat Affection Configuration File!");
//
//        loadConfiguration("chataffection.yml");
//    }
//
//    protected void onLoadFinish() {
//        name = getString("");
//    }
//}
