package me.stephenminer.lobby.commands;

import me.stephenminer.lobby.Lobby;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import java.util.ArrayList;
import java.util.List;
public class LobbyCmd implements CommandExecutor{
    private final Lobby plugin;
    public LobbyCmd(Lobby plugin){
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if (sender instanceof Player player){
            Location spawn = plugin.getLobbySpawn();
            if (spawn == null){
                player.sendMessage(ChatColor.RED + "The server does not have a lobby set!");
                return false;
            }
            player.teleport(spawn);
            player.sendMessage(ChatColor.GREEN + "Sending you to the lobby!");
        }else sender.sendMessage(ChatColor.RED + "Only players can use this command");
        return false;
    }
}
