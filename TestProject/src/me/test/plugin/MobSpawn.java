package me.test.plugin;




import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.event.entity.CreatureSpawnEvent;

import net.minecraft.server.v1_13_R2.Entity;

import net.minecraft.server.v1_13_R2.World;



public enum MobSpawn {
    MobSpawn(98, MobOcelot.class);



    private final int id;
    private final Class<? extends Entity> custom;

    MobSpawn(final int id, final Class<? extends Entity> custom) {
        this.id = id;
        this.custom = custom;
    }

    public void spawnEntity(Location loc) {
        World world = ((CraftWorld) loc.getWorld()).getHandle();
        Entity entity = new MobOcelot(world, loc);
        entity.setPositionRotation(loc.getX(), loc.getY(), loc.getZ(), loc.getYaw(), loc.getPitch());
        world.addEntity(entity, CreatureSpawnEvent.SpawnReason.CUSTOM);
    }


    public int getId() {
        return id;
    }

    public Class<? extends Entity> getCustom() {
        return custom;
    }
}