package me.michaelherron.Listeners;

import me.michaelherron.RandomDrops;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RandomDropsListeners implements Listener {
    private RandomDrops plugin;
    private Map<Material, Integer> map;
    private Material[] matList = Material.values();

    public RandomDropsListeners(RandomDrops plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);

        map = new HashMap<Material, Integer>();
        for (int i = 0; i < matList.length; i++) {
            map.put(matList[i], (int)(i + (Math.random() * 40 + 10)));
        }
    }
    @EventHandler
    public void BlockBreak(BlockBreakEvent breakEvent) {
        Block block = breakEvent.getBlock();
        World world = block.getWorld();
        Collection<ItemStack> blockDrops = block.getDrops();
        int amount = (int) (Math.random() * 100 + 1);
        int count = 0;
        for (ItemStack item : blockDrops) {
            Material dropType = item.getType();
            int dropID = map.get(dropType);
            Material droppedMat = Material.values()[dropID];
            world.dropItemNaturally(block.getLocation(), new ItemStack(droppedMat, amount));
        }
        breakEvent.setDropItems(false);
    }
    @EventHandler
    public void MobDeath(EntityDeathEvent deathEvent) {
        LivingEntity mob = deathEvent.getEntity();
        List<ItemStack> mobDrops = deathEvent.getDrops();
        int amount = (int) (Math.random() * 100 + 1);
        for (int i = 0; i < mobDrops.size(); i++) {
            Material dropType = mobDrops.get(i).getType();
            int dropID = map.get(dropType);
            Material droppedMat = Material.values()[dropID];
            mobDrops.set(i, new ItemStack(droppedMat, amount));
        }
    }
}