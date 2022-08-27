package me.stephenminer.lobby.commands;

import me.stephenminer.lobby.Lobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadLobbyCmd implements CommandExecutor {

    private final Lobby plugin;
    public ReloadLobbyCmd(Lobby plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        plugin.reloadConfig();
        sender.sendMessage(ChatColor.GREEN + "Reloaded configuration file");
        return true;
    }
}
