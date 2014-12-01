package me.yarocks.ai;

import me.yarocks.ai.Commands.InfoCommand;
import me.yarocks.ai.YarocksCoreLib.Cooldowns;
import me.yarocks.ai.YarocksCoreLib.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by User Name on 9/16/2014.
 */
public class Main extends JavaPlugin {

	Cooldowns cooldowns = new Cooldowns();
	ItemBuilder itemBuilder = new ItemBuilder(this);
	private String message;


	@Override
	public void onEnable(){
		InfoCommand infocommand = new InfoCommand(this);
		getCommand("spawnmob").setExecutor(new InfoCommand(this));
        PluginManager pm = Bukkit.getServer().getPluginManager();
	    print(ChatColor.GREEN + getDescription().getFullName() + " Has Been Enabled!");
    }

	@Override
	public void onDisable(){
	print(ChatColor.RED + getDescription().getFullName() + " Has Been Disabled!");
	}


	private void print(String message){
		ConsoleCommandSender ccs = Bukkit.getConsoleSender();
		ccs.sendMessage(message);

	}

}
