package com.czrbyn.lifestealCore.listeners;

import com.czrbyn.lifestealCore.LifestealCore;
import com.czrbyn.lifestealCore.data.HeartsData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinListener implements Listener {

    LifestealCore lCore = LifestealCore.getInstance();
    HeartsData hData = lCore.gethData();

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        if (hData.getHearts(e.getPlayer()) == 0 || !e.getPlayer().hasPlayedBefore()) {
            hData.setHearts(e.getPlayer(), 10);
        }


        lCore.getHtm().createOrUpdateTag(e.getPlayer());
    }

    @EventHandler
    public void playerQuit(PlayerQuitEvent e) {
        lCore.getHtm().removeTag(e.getPlayer());
    }

}
