package me.scidev.crazyScenarios.oreMobs;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin implements Listener {
	
	public static Plugin instance;
	
	@Override
	public void onEnable() {
		instance = this;
		this.getServer().getPluginManager().registerEvents(new EntityDeathListener(), this);
	}
}
