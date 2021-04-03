package com.ozan.plugin.santero;

import java.util.Collection;
import java.util.Date;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Yargi2 implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String msj = "";
		if(args.length==0)return false;
		if(args.length == 1) {msj = "3 days of JUDGEMENT";} else {msj = args[1];}
		if(command.getName().equalsIgnoreCase("sertyargi")) {

			final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
			for (Player player : players) {
				if(player.getName().equals(args[0])) {
					Bukkit.getBanList(BanList.Type.NAME).addBan(player.getName(),msj,new Date(System.currentTimeMillis()+3*24*60*60*1000), null);
					player.kickPlayer(msj);
					return true;
				}
			
			}
			
		}
		return false;
	}
	
}
