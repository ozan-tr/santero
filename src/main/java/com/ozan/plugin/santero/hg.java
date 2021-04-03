package com.ozan.plugin.santero;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class hg implements Listener{
	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		HumanEntity sender = e.getPlayer();
		//if (sender.hasPermission("girisMesajKapa")) return;

	  StringBuilder online = new StringBuilder();
	  final Collection<? extends Player> players = Bukkit.getOnlinePlayers();
	  for (Player player : players) {

	    if (sender instanceof Player && !((Player) sender).canSee(player))
	      continue;
	    if (online.length() > 0) {
	      online.append(", ");
	    }
	    online.append(player.getDisplayName());
	  }
	  sender.sendMessage(players.size() + "§6/§f" + Bukkit.getMaxPlayers() + " §aPlayers Online :\n" + online.toString());
	}
}
