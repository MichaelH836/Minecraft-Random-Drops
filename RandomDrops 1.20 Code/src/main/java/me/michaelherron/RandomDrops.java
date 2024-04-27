package me.michaelherron;

import me.michaelherron.Listeners.RandomDropsListeners;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomDrops extends JavaPlugin {

    @Override
    public void onEnable() {
        new RandomDropsListeners(this);
    }
}
