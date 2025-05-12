package com.czrbyn.lifestealCore.listeners;

import com.czrbyn.lifestealCore.LifestealCore;
import com.czrbyn.lifestealCore.config.ConfigHandler;
import com.czrbyn.lifestealCore.data.HeartsData;
import com.czrbyn.lifestealCore.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

public class PlayerKillDeathListener implements Listener {

    LifestealCore lCore = LifestealCore.getInstance();
    HeartsData hData = lCore.gethData();
    ConfigHandler cH = lCore.getCfgHandler();

    @EventHandler
    public void pKDListener(PlayerDeathEvent e) {
        if (Objects.requireNonNull(e.getEntity().getLastDamageCause()).getEntityType().equals(EntityType.PLAYER)) {
            Player died = e.getEntity();
            Player killer = (Player) died.getLastDamageCause().getEntity();

            int hearts = cH.getHeartsPerKill();

            if (hData.getHearts(died) == hearts) {
                for (String cmd : cH.getFinalDeathCommands()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ColorUtils.colorize(cmd.replace("%player%", died.getName())));
                }
            } else {
                hData.removeHearts(died, hearts);
                if (lCore.getConfig().get("deathMessage") != null) {
                    e.setDeathMessage("");

                    String ms = lCore.getConfig().getString("deathMessage");
                    String msg = ms.replace("%killer%", killer.getName());
                    Bukkit.broadcastMessage(ColorUtils.colorize(msg.replace("%victim%", died.getName())));
                } else {
                    lCore.getLogger().warning("[LifestealCore] Config value 'deathmessage' was not found, resorting to regular death message.");
                    lCore.getConfig().set("deathMessage", "%victim% has been killed by %killer%");
                }

            }

            hData.addHearts(killer, hearts);
        }
    }

}
