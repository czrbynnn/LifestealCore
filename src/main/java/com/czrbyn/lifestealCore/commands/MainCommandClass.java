package com.czrbyn.lifestealCore.commands;

import com.czrbyn.lifestealCore.LifestealCore;
import com.czrbyn.lifestealCore.commands.subcommands.ReloadSubCommand;
import com.czrbyn.lifestealCore.utils.ColorUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommandClass implements CommandExecutor {

    private final LifestealCore lCore;
    private final ReloadSubCommand rsc;

    public MainCommandClass() {
        lCore = LifestealCore.getInstance();
        rsc = lCore.getRsc();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            //display info
        } else if (args.length == 1 && args[0].equals("reload")) {
            if (sender.hasPermission("lifestealcore.reload")) {
                rsc.Execute();
            } else {
                sender.sendMessage(ColorUtils.colorize("&cNo permission."));
            }
        }

        return true;
    }
}
