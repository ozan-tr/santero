package com.ozan.plugin.santero;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Vote implements CommandExecutor{
	
	
    public Vote(JavaPlugin plugin) {
        plugin.getCommand("startpoll").setExecutor(this);
        plugin.getCommand("vote").setExecutor(this);
        plugin.getCommand("currentpoll").setExecutor(this);
        plugin.getCommand("endpoll").setExecutor(this);
    }
    public boolean oyAktif;
    public static HashMap<String, Integer> oylar;
    public static HashMap<Player, Boolean> oycu;
    public String oyAd;
    public Player baslatan;
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		//-----------------------başlat
		if(command.getName().equalsIgnoreCase("startpoll")) {
			if(oyAktif) {  //var olan oy varmı?
				p.sendMessage("There is curently a poll going on, to view it do /currentpoll");
				return false;
			}
			if(args.length != 1) { //oy a isim verilmişmi
				p.sendMessage("Give the poll a name");
				return false;
			}
			oyAd = args[0];//oyun ismi
			oylar = new HashMap<String, Integer>();//vote sayısı
			oycu = new HashMap<Player, Boolean>();
			oylar.put("yes", 0);//votelar işte 
			oylar.put("no", 0);
			baslatan = p;
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "A new poll named "+ChatColor.WHITE + oyAd + ChatColor.GREEN +" has been started by " + ChatColor.WHITE +""+ p.getName() +""+ ChatColor.GREEN + ". To vote either click on one of the options or use the command " + ChatColor.GOLD + "/vote {yes/no}");
			TextComponent yes = new TextComponent("[Yes]");
			yes.setColor(ChatColor.GREEN);
			yes.setBold(true);
			yes.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/vote yes"));
			TextComponent no = new TextComponent("[No]");
			no.setColor(ChatColor.RED);
			no.setBold(true);
			no.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/vote no"));
			Bukkit.getServer().spigot().broadcast(yes);
			Bukkit.getServer().spigot().broadcast(no);
			oyAktif = true;
			return true;
		}
		//----------------------------oy
		else if(command.getName().equalsIgnoreCase("vote")) {
			if(!oyAktif) { 
				p.sendMessage(ChatColor.RED+ "There isn't a poll active rigth now start one with "+ChatColor.GOLD+ "/startpoll");
				return false;
			}
			if(oycu.containsKey(p)){
				p.sendMessage(ChatColor.RED+ "You have already voted for this poll! ");
				return false;
			}
			if(args.length != 1) {
				p.sendMessage("Respond with either a Yes or No");
				return false;
			}
			if(args[0].equalsIgnoreCase("yes")) {
				oylar.put("yes", oylar.get("yes") + 1);
				oycu.put(p, true);
				p.sendMessage("You voted "+ ChatColor.GREEN + "Yes");
				return true;
			}
			else if(args[0].equalsIgnoreCase("no")) {
				p.sendMessage("You voted "+ ChatColor.RED + "No");
				oycu.put(p, false);
				oylar.put("no", oylar.get("no") + 1);
				return true;
			}
			else return false;
		}
		else if(command.getName().equalsIgnoreCase("currentpoll")) {
			if(!oyAktif) { 
				p.sendMessage(ChatColor.RED+ "There isn't a poll active rigth now start one with "+ChatColor.GOLD+ "/startpoll");
				return false;
			}
			Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Yes: "+ oylar.get("yes")+ ChatColor.RED + "" + ChatColor.BOLD +" No: " + oylar.get("no"));
			return true;
		}
		//-------------------------------poll bitirme
		else if(command.getName().equalsIgnoreCase("endpoll")) {
			if(!oyAktif) { 
				p.sendMessage(ChatColor.RED+ "There isn't a poll active rigth now start one with "+ChatColor.GOLD+ "/startpoll");
				return false;
			}
			else if(p != baslatan) {
				p.sendMessage("You havent started this poll, ask " + baslatan.getName() + " to close it");
				return false;
			}
			else{
				Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "The poll "+ ChatColor.WHITE + oyAd +ChatColor.GREEN+" was ended by " + ChatColor.WHITE + p.getName() + ChatColor.GREEN + ".The final results are: ");
				//Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "Yes: "+ oylar.get("yes")+ ChatColor.RED + "" + ChatColor.BOLD +" No: " + oylar.get("no"));
				StringBuilder yesCi = new StringBuilder();
				StringBuilder noCu = new StringBuilder();
				for(Map.Entry<Player, Boolean> entry : oycu.entrySet()) {
				    Player pla = entry.getKey();
				    @SuppressWarnings("unused")
					Boolean oYu = entry.getValue();
				    
				    if(oYu = true) {
				    	yesCi.append(pla.getName()+", ");
				    }
				    else {
				    	noCu.append(pla.getName()+", ");
				    }
				}
				Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "" + ChatColor.BOLD + "YES ["+oylar.get("yes")+"] : " + ChatColor.GREEN + yesCi.toString());
				Bukkit.getServer().broadcastMessage(ChatColor.RED + "" + ChatColor.BOLD + "NO ["+oylar.get("no")+"] : " + ChatColor.RED + noCu.toString());
				oycu.clear();
				oylar.clear();
				oyAktif = false;
				return true;
			}	
		}
		else return false;
	}
}
//ingilizceden beynim yandı aq bidaha herboku ingilizce yaparsam götümden siksinler beni
