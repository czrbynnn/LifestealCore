package com.czrbyn.lifestealCore;

import com.czrbyn.lifestealCore.commands.MainCommandClass;
import com.czrbyn.lifestealCore.commands.subcommands.ReloadSubCommand;
import com.czrbyn.lifestealCore.config.ConfigHandler;
import com.czrbyn.lifestealCore.data.HeartsData;
import com.czrbyn.lifestealCore.listeners.PlayerJoinListener;
import com.czrbyn.lifestealCore.listeners.PlayerKillDeathListener;
import com.czrbyn.lifestealCore.listeners.PlayerRespawnListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifestealCore extends JavaPlugin {

    private static LifestealCore instance;
    private ConfigHandler cfgHandler;
    private HeartsData hData;

    private PlayerJoinListener pjl;
    private PlayerKillDeathListener pkdl;
    private PlayerRespawnListener prl;

    private MainCommandClass mcc;

    private ReloadSubCommand rsc;

    @Override
    public void onEnable() {
        instance = this;
        getLogger().info("[LifestealCore] Successfully Enabled!");

        cfgHandler = new ConfigHandler();
        hData = new HeartsData();

        saveDefaultConfig();

        registerListeners();

        registerCommands();

    }

    @Override
    public void onDisable() {
        getLogger().info("[LifestealCore] Successfully Disabled");
    }

    public void registerListeners() {
        pjl = new PlayerJoinListener();
        Bukkit.getPluginManager().registerEvents(pjl, this);

        pkdl = new PlayerKillDeathListener();
        Bukkit.getPluginManager().registerEvents(pkdl, this);

        prl = new PlayerRespawnListener();
        Bukkit.getPluginManager().registerEvents(prl, this);
    }

    public void registerCommands() {
        mcc = new MainCommandClass();
        getCommand("lifestealcore").setExecutor(mcc);

        rsc = new ReloadSubCommand();
    }

    public static LifestealCore getInstance() {
        return instance;
    }

    public ConfigHandler getCfgHandler() {
        return cfgHandler;
    }

    public HeartsData gethData() {
        return hData;
    }

    public ReloadSubCommand getRsc() { return rsc; }
}
