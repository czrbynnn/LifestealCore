package com.czrbyn.lifestealCore.listeners;

import com.czrbyn.lifestealCore.LifestealCore;
import com.czrbyn.lifestealCore.data.HeartsData;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PlayerRespawnListener implements Listener {

    private final LifestealCore lCore;
    private final HeartsData hData;

    public PlayerRespawnListener() {
        lCore = LifestealCore.getInstance();
        hData = lCore.gethData();
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        AttributeInstance abi = p.getAttribute(Attribute.MAX_HEALTH);
        if (abi != null) {
            int hearts = hData.getHearts(p);
            int i = hearts * 2;
            abi.setBaseValue(i);
        }
    }

}
