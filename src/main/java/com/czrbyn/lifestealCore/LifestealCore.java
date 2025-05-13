package com.czrbyn.lifestealCore;

import com.czrbyn.lifestealCore.commands.MainCommandClass;
import com.czrbyn.lifestealCore.commands.subcommands.ReloadSubCommand;
import com.czrbyn.lifestealCore.config.ConfigHandler;
import com.czrbyn.lifestealCore.data.HeartsData;
import com.czrbyn.lifestealCore.listeners.*;
import com.czrbyn.lifestealCore.utils.HeartTagManager;
import net.luckperms.api.LuckPerms;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class LifestealCore extends JavaPlugin {

    private static LifestealCore instance;
    private ConfigHandler cfgHandler;
    private HeartsData hData;
    private HeartTagManager htm;
    private LuckPerms luckPerms;

    private PlayerJoinListener pjl;
    private PlayerKillDeathListener pkdl;
    private PlayerRespawnListener prl;
    private PlayerDamageListener pdl;
    private PlayerMoveListener pml;

    private MainCommandClass mcc;

    private ReloadSubCommand rsc;

    @Override
    public void onEnable() {

        RegisteredServiceProvider<LuckPerms> provider = Bukkit.getServicesManager().getRegistration(LuckPerms.class);
        if (provider != null) {
            luckPerms = provider.getProvider();
        } else {
            luckPerms = null;
        }

        instance = this;
        getLogger().info("[LifestealCore] Successfully Enabled!");

        cfgHandler = new ConfigHandler();
        hData = new HeartsData();
        htm = new HeartTagManager();

        saveDefaultConfig();

        registerListeners();

        registerCommands();

    }

    @Override
    public void onDisable() {
        getLogger().info("[LifestealCore] Successfully Disabled");
        htm.removeAll();
    }

    public void registerListeners() {
        pjl = new PlayerJoinListener();
        Bukkit.getPluginManager().registerEvents(pjl, this);

        pkdl = new PlayerKillDeathListener();
        Bukkit.getPluginManager().registerEvents(pkdl, this);

        prl = new PlayerRespawnListener();
        Bukkit.getPluginManager().registerEvents(prl, this);

        pdl = new PlayerDamageListener();
        Bukkit.getPluginManager().registerEvents(pdl, this);

        pml = new PlayerMoveListener();
        Bukkit.getPluginManager().registerEvents(pml, this);
    }

    public void registerCommands() {
        rsc = new ReloadSubCommand();

        mcc = new MainCommandClass();
        getCommand("lifestealcore").setExecutor(mcc);


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

    public HeartTagManager getHtm() { return htm; }

    public LuckPerms getLuckPerms() { return luckPerms; }
}
