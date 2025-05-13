package com.czrbyn.lifestealCore.utils;

import com.czrbyn.lifestealCore.LifestealCore;
import com.czrbyn.lifestealCore.config.ConfigHandler;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class HeartTagManager {

    private final ConfigHandler cfg = LifestealCore.getInstance().getCfgHandler();
    private final Map<UUID, TextDisplay> tags = new HashMap<>();

    public void createOrUpdateTag(Player target) {
        TextDisplay tag = tags.get(target.getUniqueId());

        if (tag == null || tag.isDead() || !tag.isValid()) {
            spawnTag(target);
        } else {
            updateTag(target, tag);
        }
    }

    private void updateTag(Player target, TextDisplay tag) {
        Location loc = target.getLocation().clone().add(0, cfg.getOffset(), 0);

        if (tag.getWorld() != target.getWorld() || !tag.getLocation().equals(target.getLocation())) {
            tag.teleport(loc);
        }

        tag.setText(formatHealth((int) target.getHealth(), (int) target.getMaxHealth()));
    }

    public void removeTag(Player target) {
        TextDisplay tag = tags.remove(target.getUniqueId());
        if (tag != null && !tag.isDead()) {
            tag.remove();
        }
    }

    public void removeAll() {
        for (TextDisplay tag : tags.values()) {
            if (tag != null && !tag.isDead()) {
                tag.remove();
            }
        }
        tags.clear();
    }

    private void spawnTag(Player target) {
        Location loc = target.getLocation().clone().add(0, cfg.getOffset(), 0);
        World world = target.getWorld();

        TextDisplay tag = (TextDisplay) world.spawnEntity(loc, EntityType.TEXT_DISPLAY);
        tag.setText(formatHealth((int) target.getHealth(), (int) target.getMaxHealth()));
        tag.setBillboard(Display.Billboard.CENTER);
        tag.setSeeThrough(true);
        tag.setShadowed(false);
        tag.setBrightness(new Display.Brightness(15, 15));
        tag.setPersistent(true);
        tag.setCustomNameVisible(false);
        tag.setVisibleByDefault(true);

        tags.put(target.getUniqueId(), tag);
    }

    private String formatHealth(int health, int maxHealth) {
        return ColorUtils.colorize("&c❤ &7" + health + " &f/ &7" + maxHealth + "&c ❤");
    }
}
