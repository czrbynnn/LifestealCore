package com.czrbyn.lifestealCore.listeners;

import com.czrbyn.lifestealCore.LifestealCore;
import com.czrbyn.lifestealCore.utils.HeartTagManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {

    private final LifestealCore lCore;
    private final HeartTagManager htm;

    public PlayerDamageListener() {
        lCore = LifestealCore.getInstance();
        htm = lCore.getHtm();
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            Bukkit.getScheduler().runTaskLater(lCore, () ->
                    htm.createOrUpdateTag((Player) e.getEntity()), 1L);
        }
    }

}
