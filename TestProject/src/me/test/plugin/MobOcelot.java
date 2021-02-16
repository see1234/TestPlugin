package me.test.plugin;

import java.util.Date;
import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftPlayer;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import net.minecraft.server.v1_13_R2.DataWatcher;
import net.minecraft.server.v1_13_R2.EntityHuman;
import net.minecraft.server.v1_13_R2.EntityMonster;
import net.minecraft.server.v1_13_R2.EntityTypes;
import net.minecraft.server.v1_13_R2.PacketPlayOutEntityMetadata;
import net.minecraft.server.v1_13_R2.PathfinderGoalFloat;
import net.minecraft.server.v1_13_R2.PathfinderGoalHurtByTarget;
import net.minecraft.server.v1_13_R2.PathfinderGoalMeleeAttack;
import net.minecraft.server.v1_13_R2.PathfinderGoalMoveTowardsRestriction;
import net.minecraft.server.v1_13_R2.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_13_R2.PathfinderGoalRandomLookaround;
import net.minecraft.server.v1_13_R2.PathfinderGoalRandomStrollLand;
import net.minecraft.server.v1_13_R2.World;

public class MobOcelot extends EntityMonster  {
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public MobOcelot(World world, Location loc) {
        super(EntityTypes.OCELOT, ((CraftWorld)world.getWorld()).getHandle());
        this.goalSelector.a(0, new PathfinderGoalFloat(this));
        this.goalSelector.a(2, new PathfinderGoalMeleeAttack(this, 1.0D, true));
        this.goalSelector.a(5, new PathfinderGoalMoveTowardsRestriction(this, 1.0D));
        this.goalSelector.a(7, new PathfinderGoalRandomStrollLand(this, 1.0D));
        this.goalSelector.a(8, new PathfinderGoalRandomLookaround(this));
        this.targetSelector.a(3, new PathfinderGoalHurtByTarget(this, true));
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityHuman.class, true, true));
        this.setCustomNameVisible(true);
        LivingEntity entity = (LivingEntity) this.getBukkitEntity();
        entity.setRemoveWhenFarAway(false);
        Random r = new Random();
        String text = "אבגדהו¸זחטיךכלםמןנסעףפץצקתûü‎‏ÿ";
        String text1 = "0123456789";
        String sum = text + text.toUpperCase() + text1;
        int i = 0;
        String e = "";
        while(i < 5) {
            char c = sum.charAt(r.nextInt(sum.length()) );
            e=e+c;
       	    i+=1;
        }
        entity.setCustomName("" + e);
    }
    public void die() {
    	try {
        if (this.killer instanceof net.minecraft.server.v1_13_R2.EntityPlayer) {
            final Player p = (Player)this.killer.getBukkitEntity();
            LivingEntity entity = (LivingEntity) this.getBukkitEntity();
            Date date = new Date();
            Main.getInstance().getDatabaseManager().update("INSERT INTO " + DatabaseManager.name + "(Date, name, NameOcelot) VALUES('" + date.toString() + "', '" + p.getName() + "', '" + entity.getCustomName() + "');");
            ItemStack stack = new ItemStack(Material.LEATHER);
            Location loc = new Location(this.getBukkitEntity().getWorld(), this.getBukkitEntity().getLocation().getX(), this.getBukkitEntity().getLocation().getY(), this.getBukkitEntity().getLocation().getZ());
            org.bukkit.entity.Item en = (org.bukkit.entity.Item) loc.getWorld().dropItem(loc, stack);
          en.setCustomNameVisible(true);
          for(Player AllPlayers : Bukkit.getServer().getOnlinePlayers()){
              en.setCustomName(AllPlayers.getName());
              DataWatcher data = ((CraftEntity)en).getHandle().getDataWatcher();
              PacketPlayOutEntityMetadata packetPlayOutEntityMetadata = new PacketPlayOutEntityMetadata(en.getEntityId(),data,true);
              ((CraftPlayer) AllPlayers).getHandle().playerConnection.sendPacket(packetPlayOutEntityMetadata);
          }
        }
        if (this.killer != null) {
							    }
    	}
        catch (Exception e) {
            System.err.println("DIE ERROR!");
            e.printStackTrace();
        }
						        super.die();
    }
}


    