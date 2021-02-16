package me.test.plugin;

import org.bukkit.event.Listener;

import org.bukkit.plugin.java.JavaPlugin;











public class Main extends JavaPlugin {

	private DatabaseManager databaseManager;
    public static Main instance;
	public void onEnable() {
		 this.getServer().getPluginManager().registerEvents((Listener) new me.test.plugin.Listener(), this);
			databaseManager = new DatabaseManager(this);
	        Main.instance = this;

	}
    public void onDisable() {
		databaseManager.closeConnection();
	}
	public DatabaseManager getDatabaseManager() {
		return databaseManager;
	} 
	public static Main getInstance() {
		return instance;
	}
}
