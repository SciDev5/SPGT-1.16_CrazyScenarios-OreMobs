package me.scidev.crazyScenarios.oreMobs;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void on(EntityDeathEvent e) {
		LivingEntity entity = e.getEntity();
		Player killer = entity.getKiller();
		List<ItemStack> drops = e.getDrops();
		drops.clear();
		
		// TODO Random drops
		drops.add(new ItemStack(Material.DIAMOND, 65));
	}
}
