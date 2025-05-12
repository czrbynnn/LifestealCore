package com.czrbyn.lifestealCore.listeners;

import com.czrbyn.lifestealCore.LifestealCore;
import com.czrbyn.lifestealCore.data.HeartsData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    LifestealCore lCore = LifestealCore.getInstance();
    HeartsData hData = lCore.gethData();

    @EventHandler
    public void playerJoin(PlayerJoinEvent e) {
        if (hData.getHearts(e.getPlayer()) == 0 || !e.getPlayer().hasPlayedBefore()) {
            hData.setHearts(e.getPlayer(), 10);
        }
    }

}
