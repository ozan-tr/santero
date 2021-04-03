package com.ozan.plugin.santero;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;


public class EnchCom implements CommandExecutor{
	List<String> tr = Arrays.asList("a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","r","s","t","u","v","y","z","q","w");
	List<String> en = Arrays.asList("ᔑ","ʖ","ᓵ","↸","ᒷ","⎓","⊣","�?�","╎","⋮","ꖌ","ꖎ","ᒲ","リ","J","!¡","∷","ᓭ","ℸ","�?","�?�","||","⨅","ᑑ","∴");
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		String[] S ;
		String cumle ="";
		if(command.getName().equalsIgnoreCase("enchantif")) {
			
				
				
		for(int i = 0; i < args.length; i++){ 
				    String arg = args[i] + " ";
				    cumle = cumle + arg;
				}
			S = cumle.split("(?!^)");
			String F = "";
	    	    for (int i = 0; i < S.length; i++){
	    	        String t = S[i];
	    	        if(S[i] == "ı"){t = "i";};
	    	            int index = tr.indexOf(t);
	    	            if(index != -1){
	    	                F += en.get(index);
	    	            }else{
	    	                F += t;
	    	            }
	    	        

	    	    }
	    	    TextComponent message = new TextComponent(ChatColor.GREEN+ sender.getName()+ChatColor.BLACK+": "+ ChatColor.RED +F);
	    	    message.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/humanize " + F));
	    	    message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.AQUA+"Click here to copy.").create()));
	    	    for(Player pl : Bukkit.getOnlinePlayers()) {
	    	    	pl.spigot().sendMessage(message);
	    	    }
	    	    return true;
		}else {
			p.sendMessage("arg hatası P");
			return false;
		}
	}

}
