package com.ozan.plugin.santero;

import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Main extends JavaPlugin{
	@Override
	public void onEnable() {
		 @SuppressWarnings("unused")
		final Vote ve = new Vote(this);
		getServer().getPluginManager().registerEvents(new itemBlack(), this);
		getServer().getPluginManager().registerEvents(new hg(), this);
		 this.getCommand("enchantif").setExecutor(new EnchCom());
		 this.getCommand("humanize").setExecutor(new InsanCom());
		 this.getCommand("yargi").setExecutor(new Yargi());
		 this.getCommand("sertyargi").setExecutor(new Yargi2());
		 
		 getServer().broadcastMessage(ChatColor.GREEN + "Santero Başladı");
		 getServer().broadcastMessage(ChatColor.GOLD +  "0zan_     v1.1");
	}

}
