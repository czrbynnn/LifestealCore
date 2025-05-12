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

            died.getAttribute(Attribute.GENERIC_MAX_HEALTH)

            int hearts = cH.getHeartsPerKill();

            if (hData.getHearts(died) == hearts) {
                for (String cmd : cH.getFinalDeathCommands()) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ColorUtils.colorize(cmd.replace("%player%", died.getName())));
                }
            } else {
                hData.removeHearts(died, hearts);
            }

            hData.addHearts(killer, hearts);
        }
    }

}
