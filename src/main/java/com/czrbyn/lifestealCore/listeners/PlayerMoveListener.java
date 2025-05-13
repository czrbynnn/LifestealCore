package com.czrbyn.lifestealCore.listeners;

import com.czrbyn.lifestealCore.LifestealCore;
import com.czrbyn.lifestealCore.utils.HeartTagManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private LifestealCore lCore;
    private HeartTagManager htm;

    public PlayerMoveListener() {
        lCore = LifestealCore.getInstance();
        htm = lCore.getHtm();
    }

    @EventHandler
    public void playerMove(PlayerMoveEvent e) {
        htm.createOrUpdateTag(e.getPlayer());
    }

}
