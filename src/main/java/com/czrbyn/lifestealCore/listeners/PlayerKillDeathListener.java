package com.czrbyn.lifestealCore.listeners;

import com.czrbyn.lifestealCore.LifestealCore;
import com.czrbyn.lifestealCore.config.ConfigHandler;
import com.czrbyn.lifestealCore.data.HeartsData;
import com.czrbyn.lifestealCore.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
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
            Player killer = died.getKiller();

            if (killer != null) {


                if (killer.getUniqueId().equals(died.getUniqueId())) {
                    died.sendMessage(ColorUtils.colorize("&fYou cannot gain hearts from killing yourself."));
                } else {
                    int hearts = cH.getHeartsPerKill();

                    int victimHearts = hData.getHearts(died);

                    if (victimHearts == hearts) {
                        for (String cmd : cH.getFinalDeathCommands()) {
                            String parsed = ColorUtils.colorize(cmd.replace("%player%", died.getName()));
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), parsed);
                        }
                    } else {
                        hData.removeHearts(died, hearts);
                        if (lCore.getConfig().get("deathMessage") != null) {
                            e.setDeathMessage("");
                            String ms = lCore.getConfig().getString("deathMessage");
                            String msg = ms.replace("%killer%", killer.getName()).replace("%victim%", died.getName());
                            Bukkit.broadcastMessage(ColorUtils.colorize(msg));
                        } else {
                            lCore.getLogger().warning("[LifestealCore] Config value 'deathMessage' was not found, resorting to regular death message.");
                            lCore.getConfig().set("deathMessage", "%victim% has been killed by %killer%");
                        }
                    }

                    AttributeInstance abi = killer.getAttribute(Attribute.MAX_HEALTH);
                    if (abi != null) {
                        hData.addHearts(killer, hearts);

                        int currentKillerHearts = hData.getHearts(killer);

                        abi.setBaseValue(currentKillerHearts * 2.0);
                    } else {
                        Bukkit.getLogger().warning("[LifestealCore] Could not get AttributeInstance for killer: " + killer.getName());
                    }
                }
            }
        }
    }

}
