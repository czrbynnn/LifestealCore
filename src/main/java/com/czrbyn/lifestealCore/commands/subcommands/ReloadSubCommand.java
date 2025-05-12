package com.czrbyn.lifestealCore.commands.subcommands;

import com.czrbyn.lifestealCore.LifestealCore;
import com.czrbyn.lifestealCore.config.ConfigHandler;
import com.czrbyn.lifestealCore.data.HeartsData;

public class ReloadSubCommand {

    private final LifestealCore lCore;
    private final HeartsData hData;
    private final ConfigHandler cH;

    public ReloadSubCommand() {
        lCore = LifestealCore.getInstance();
        hData = lCore.gethData();
        cH = lCore.getCfgHandler();
    }

    public void Execute() {
        hData.reload();
        cH.reload();
    }

}
