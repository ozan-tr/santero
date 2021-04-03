package com.ozan.plugin.santero;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class itemBlack implements Listener{
	ItemStack ely = new ItemStack(Material.ELYTRA);
	@EventHandler
	public void pickup(PlayerPickupItemEvent event) {
		ItemStack alin = event.getItem().getItemStack();
		Item it = event.getItem();	
		Player p = event.getPlayer();
		if (alin.equals(ely)) {
			it.remove();
			event.setCancelled(true);
			p.getInventory().removeItem(alin);
			p.sendMessage(ChatColor.RED+"Elytra's aren't allowed on Santero :(");
			
		}
	}
}
