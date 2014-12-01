package me.yarocks.ai.Commands;

import me.yarocks.ai.Level;
import me.yarocks.ai.Main;
import me.yarocks.ai.YarocksCoreLib.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by User Name on 9/27/2014.
 */
public class InfoCommand implements CommandExecutor {

	public InfoCommand(Main c) {
		this.c = c;
	}

	public Main c;


	@Override
	public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
		if (command.getName().equalsIgnoreCase("spawnmob")){
			if (!(sender instanceof Player)){
//ADD MESSAGE HERE AFTER MAKING OF MESSAGEMANAGER
				return false;
			}
			Player p = (Player) sender;
			Level level = Level.valueOf(args[0]);
			Skeleton skeleton = (Skeleton) p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON);

			if (level == Level.ONE){
			skeleton.setSkeletonType(SkeletonType.WITHER);
				skeleton.getEquipment().setItemInHand(new ItemBuilder(Material.DIAMOND_SWORD).build());
			}
			else if (level == Level.TWO){
			skeleton.setSkeletonType(SkeletonType.WITHER);
				EntityEquipment ee = skeleton.getEquipment();
				ee.setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
				skeleton.getEquipment().setItemInHand(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL).build());
				skeleton.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
			}
		else if (level == Level.THREE){
			skeleton.setSkeletonType(SkeletonType.WITHER);
				skeleton.getEquipment().setItemInHand(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2).build());
				skeleton.setPassenger(p.getWorld().spawnEntity(p.getLocation(), EntityType.SKELETON));
				skeleton.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
				skeleton.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1));
			}
			else if (level == Level.FOUR) {
				for (int i = 0; i < 2; i++) {
					skeleton.setSkeletonType(SkeletonType.WITHER);
					skeleton.getEquipment().setItemInHand(new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL).build());
					skeleton.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 1));
					skeleton.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, Integer.MAX_VALUE, 1));
					skeleton.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
				}
			}
			else if (level == Level.FIVE){
				skeleton.setSkeletonType(SkeletonType.WITHER);
			}
			skeleton.setTarget(p);
		}
		return false;
	}
	//WARNING: ALWAYS RETURN FALSE
}