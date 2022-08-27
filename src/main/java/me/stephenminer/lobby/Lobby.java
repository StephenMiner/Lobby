package me.stephenminer.lobby;

import me.stephenminer.lobby.commands.LobbyCmd;
import me.stephenminer.lobby.commands.ReloadLobbyCmd;
import me.stephenminer.lobby.commands.SetLobbyCmd;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

public final class Lobby extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Location getLobbySpawn(){
        return fromString(getConfig().getString("lobby-spawn"));
    }

    private void registerCommands(){
        getCommand("lobby").setExecutor(new LobbyCmd(this));
        getCommand("setLobby").setExecutor(new SetLobbyCmd(this));
        getCommand("reloadLobby").setExecutor(new ReloadLobbyCmd(this));
    }

    public String fromLoc(Location loc){
        return loc.getWorld().getName() + "," + loc.getX() + "," + loc.getY() + "," + loc.getZ() + "," + loc.getYaw() + "," + loc.getPitch();
    }

    public Location fromString(String str){
        if (str == null) return null;
        String[] contents = str.split(",");
        World world = null;
        try{
            world = Bukkit.getWorld(contents[0]);
        }catch (Exception ignored){}
        if (world == null){
            if (getConfig().getBoolean("load-if-null")){
                world = Bukkit.createWorld(new WorldCreator(contents[0]));
            }else{
                getLogger().warning("Unable to get location because world " + contents[0] + " doesn't exist or isn't loaded");
                return null;
            }
        }
        double x = Double.parseDouble(contents[1]);
        double y = Double.parseDouble(contents[2]);
        double z = Double.parseDouble(contents[3]);
        float yaw = Float.parseFloat(contents[4]);
        float pitch = Float.parseFloat(contents[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }


}
