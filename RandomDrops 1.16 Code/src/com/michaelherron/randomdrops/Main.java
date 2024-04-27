package com.michaelherron.randomdrops;

import org.bukkit.plugin.java.JavaPlugin;

import com.michaelherron.randomdrops.listeners.RandomDropListener;

public class Main extends JavaPlugin {

	public void onEnable() {
		
		new RandomDropListener(this);
	}

}
