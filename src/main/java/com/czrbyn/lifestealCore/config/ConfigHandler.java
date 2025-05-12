package com.czrbyn.lifestealCore.config;

import com.czrbyn.lifestealCore.LifestealCore;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConfigHandler {

    LifestealCore lCore = LifestealCore.getInstance();
    private FileConfiguration cfg;

    public ConfigHandler() {
        cfg = lCore.getConfig();
    }

    public void reload() {
        lCore.saveConfig();
        cfg = lCore.getConfig();
    }

    public int getMaxHearts() {
        if (cfg.get("maxHearts") != null) {
            return cfg.getInt("maxHearts");
        } else {
            cfg.set("maxHearts", 20);
            lCore.getLogger().warning("[LifestealCore] No value found for maxHearts in config, reverting to default 20");
            lCore.saveConfig();
            return 20;
        }
    }

    public int getHeartsPerKill() {
        if (cfg.get("heartsPerKill") != null) {
            return cfg.getInt("heartsPerKill");
        } else {
            cfg.set("heartsPerKill", 1);
            lCore.getLogger().warning("[LifestealCore] No value found for heartsPerKill in config, reverting to default 1.");
            lCore.saveConfig();
            return 1;
        }
    }

    public List<String> getFinalDeathCommands() {
        if (cfg.get("commandsAfterFinalDeath") != null) {
            return cfg.getStringList("commandsAfterFinalDeath");
        } else {
            List<String> cmds = new ArrayList<>();
            cmds.add("ban %player%");

            cfg.set("commandsAfterFinalDeath", cmds);
            lCore.getLogger().warning("[LifestealCore] No value found for commandsAfterFinalDeath in config, reverting to default: /ban %player%");
            lCore.saveConfig();
            return cfg.getStringList("commandsAfterFinalDeath");
        }
    }
}
