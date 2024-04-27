package com.michaelherron.randomdrops.listeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.michaelherron.randomdrops.Main;

import me.idlibrary.main.IDLibrary;

public class RandomDropListener implements Listener{
	public int IDChangeValue = 10;
	private Main plugin;
	
	public RandomDropListener(Main plugin) {
		this.plugin = plugin;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void BlockBreak(BlockBreakEvent breakEvent) {
		Player player = breakEvent.getPlayer();
		Block block = breakEvent.getBlock();
		breakEvent.setDropItems(false);
		Material blockType = block.getType();
		
		int blockID = IDLibrary.getID(blockType);
		int ID;
		int amount = (int)(Math.random() * 100 + 1);
		
		if (blockID >= 963 - IDChangeValue) {
			ID = (int)(Math.random() * 10 + 0);
		}
		else {
			ID = blockID + IDChangeValue;
		}
		Map map = getMaterialDict();
		World world = player.getWorld();
		Location location = block.getLocation();
		Material mat = finalMaterial(map, ID);
		if (IDLibrary.getID(mat) == 17) {
			ID++;
		}
		Material finalMat = finalMaterial(map, ID);
		world.dropItemNaturally(location, new ItemStack(finalMat, amount));
	}
	
	@EventHandler
	public void MobDeath(EntityDeathEvent deathEvent) {
		LivingEntity mob = deathEvent.getEntity();
		List<ItemStack> mobDrops = deathEvent.getDrops();
		World world = mob.getWorld();
		Location location = mob.getLocation();
		for (ItemStack drop : mobDrops) {
			int dropID = IDLibrary.getID(drop);
			int ID;
			int amount = (int)(Math.random() * 100 + 1);
			
			if (dropID >= 963 - IDChangeValue) {
				ID = (int)(Math.random() * 10 + 0);
			}
			else {
				ID = dropID + IDChangeValue;
			}
			Map map = getMaterialDict();
			Material mat = finalMaterial(map, ID);
			if (IDLibrary.getID(mat) == 17) {
				ID++;
			}
			Material finalMat = finalMaterial(map, ID);
			world.dropItemNaturally(location, new ItemStack(finalMat, amount));
		}
	}
	
	public static Map getMaterialDict() {
		int count = 0;
		Map<Integer, Material> materialDictionary = new HashMap<Integer, Material>();
		for (Material material : Material.values()) {
			materialDictionary.put(count, material);
			count++;
		}
		return materialDictionary;
	}
	
	public static Material finalMaterial(Map<Integer, Material> map, int ID) {
		return map.get(ID);
	}
}
