package com.czrbyn.lifestealCore.data;

import com.czrbyn.lifestealCore.LifestealCore;
import org.bukkit.attribute.Attribute;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class HeartsData {

    LifestealCore lCore = LifestealCore.getInstance();
    private File f;
    private FileConfiguration cfg;

    public HeartsData() {
        f = new File(lCore.getDataFolder(), "hearts.yml");
        cfg = YamlConfiguration.loadConfiguration(f);

        if (!f.exists()) {
            lCore.getLogger().severe("[LifestealCore] HEARTS.YML DOES NOT EXIST.");
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void reload() {
        f = new File(lCore.getDataFolder(), "hearts.yml");
        cfg = YamlConfiguration.loadConfiguration(f);

        if (!f.exists()) {
            lCore.getLogger().severe("[LifestealCore] HEARTS.YML DOES NOT EXIST.");
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public int getHearts(Player p) {
        if (cfg.get(p.getUniqueId() + ".hearts") != null) {
            return cfg.getInt(p.getUniqueId() + ".hearts");
        } else {
            return 0;
        }
    }

    public void setHearts(Player p, int i) {
        if (i <= lCore.getCfgHandler().getMaxHearts()) {
            cfg.set(p.getUniqueId() + ".hearts", i);
            try {
               cfg.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Objects.requireNonNull(p.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(i * 2);


        }
    }

    public void removeHearts(Player p, int i) {
        int b = cfg.getInt(p.getUniqueId() + ".hearts");
        if ((b - i) > 0) {
            cfg.set(p.getUniqueId() + ".hearts", (b - i));
        }
        Objects.requireNonNull(p.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(cfg.getInt(p.getUniqueId() + ".hearts") * 2);
    }

    public void addHearts(Player p, int i) {
        int b = cfg.getInt(p.getUniqueId() + ".hearts");
        if ((b + i) <= 40) {
            cfg.set(p.getUniqueId() + ".hearts", (b + i));
        }
        Objects.requireNonNull(p.getAttribute(Attribute.MAX_HEALTH)).setBaseValue(cfg.getInt(p.getUniqueId() + ".hearts") * 2);
    }

}
