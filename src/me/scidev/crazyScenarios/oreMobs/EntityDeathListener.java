package me.scidev.crazyScenarios.oreMobs;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

public class EntityDeathListener implements Listener {

	private final List<PossibleLoot> loot = new ArrayList<>();
	private final PossibleLoot pityLoot = new PossibleLoot(Material.COBBLESTONE, 1, 8, 0f);
	private final Random random = new Random();
	private final float minRarity = 10;
	private final float maxRarity = 200;
	private final int maxStacks = 5;
	
	public EntityDeathListener() {
		loot.add(new PossibleLoot(Material.DIAMOND, 1, 4, 100f));
		loot.add(new PossibleLoot(Material.IRON_INGOT, 2, 16, 30f));
		loot.add(new PossibleLoot(Material.REDSTONE, 2, 16, 45f));
		loot.add(new PossibleLoot(Material.GOLD_INGOT, 1, 8, 60f));
		loot.add(new PossibleLoot(Material.COAL, 4, 8, 5f));
	}
	
	@EventHandler
	public void on(EntityDeathEvent e) {
		List<ItemStack> drops = e.getDrops();
		drops.clear();
		
		// Random drops
		
		float rarityRemaining = this.random.nextFloat() * (this.maxRarity - this.minRarity) + this.minRarity;
		
		int stackTries = this.random.nextInt(this.maxStacks);
		for (int i = 0; i < stackTries; i++)
			rarityRemaining = this.loot.get(this.random.nextInt(this.loot.size())).addStack(rarityRemaining, drops);
		
		if (drops.isEmpty())
			this.pityLoot.addStack(0f, drops);
		
		/*
		LivingEntity entity = e.getEntity();
		Player killer = entity.getKiller();
		if (killer != null) {
			killer.sendMessage("!! KILLED ENTITY !!");
			for (ItemStack stack : drops)
				killer.sendMessage(":: "+stack.getI18NDisplayName()+" x"+stack.getAmount());
		}*/
	}
	
	private class PossibleLoot {
		private final Material material;
		private final int min;
		private final int max;
		private final float rarity;
		public PossibleLoot(Material material, int min, int max, float rarity) {
			this.material = material;
			this.max = max;
			this.min = min;
			this.rarity = rarity;
		}
		
		public float addStack(float rarityRemaining, List<ItemStack> drops) {
			int amount = EntityDeathListener.this.random.nextInt(this.max-this.min+1)+this.min;
			
			float raritySpent = this.rarity * (amount/(2f * this.max) + 0.5f);
			
			if (raritySpent > rarityRemaining) 
				return rarityRemaining;
			
			drops.add(new ItemStack(this.material, amount));
			return rarityRemaining - raritySpent;
		}
		
	}
}
