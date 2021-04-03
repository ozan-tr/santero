package com.ozan.plugin.santero;

import java.util.Collection;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Yargi implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String msj = "";
		if(args.length==0)return false;
		if(args.length == 1) {msj = "You Have Been JUDGED";} else {msj = args[1];}
		if(command.getName().equalsIgnoreCase("yargi")) {

			final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
			for (Player player : players) {
				if(player.getName().equals(args[0])) {
					player.kickPlayer(msj);
					return true;
				}
			
			}
			
		}
		return false;
	}
	
}
