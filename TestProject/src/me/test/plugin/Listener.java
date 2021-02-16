package me.test.plugin;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDeathEvent;

public class Listener implements org.bukkit.event.Listener {

	@EventHandler
	public void killMob(EntityDeathEvent e) { 
		LivingEntity entity = e.getEntity();
         if(entity instanceof Zombie) {
        	 entity.remove();
             MobSpawn.MobSpawn.spawnEntity(entity.getLocation());
         }
	}




}
