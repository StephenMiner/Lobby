package me.stephenminer.lobby.commands;

import me.stephenminer.lobby.Lobby;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobbyCmd implements CommandExecutor {
    private final Lobby plugin;
    public SetLobbyCmd(Lobby plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender instanceof Player player){
            plugin.getConfig().set("lobby-spawn", plugin.fromLoc(player.getLocation()));
            player.sendMessage(ChatColor.GREEN + "Set the new server lobby!");
            return true;
        }else sender.sendMessage(ChatColor.RED + "Only players can use this command!");
        return false;
    }
}
