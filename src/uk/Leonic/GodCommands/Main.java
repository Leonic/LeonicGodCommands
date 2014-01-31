package uk.Leonic.GodCommands;

import java.util.logging.*;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.*;
import org.bukkit.potion.*;

public class Main extends JavaPlugin{
    
	protected Logger log = Logger.getLogger("Minecraft");
	protected Update updaterCheck;
	
	@Override
	public void onEnable(){
		Update update = new Update(this,"http://dev.bukkit.org/bukkit-plugins/leonicgodcommmands/files.rss");
		update.updateNeeded();
		PluginDescriptionFile pdfFile = this.getDescription();
	    this.log.info("[" + pdfFile.getName() + "]" + " Has Been Activated! Woo!");
	    this.log.info("====== NOTE ======");
	    this.log.info("This plugin is work in progress so there's bound to be some bugs.");
      }
	
	@Override
	public void onDisable(){
	    PluginDescriptionFile pdfFile = this.getDescription();
	    this.log.info("[" + pdfFile.getName() +"]" + " Has Been Deactivated.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("gcupdate") || commandLabel.equalsIgnoreCase("gcu")){
			Update object = new Update(this,"http://dev.bukkit.org/bukkit-plugins/leonicgodcommmands/files.rss");
			player.sendMessage(ChatColor.BLUE + "Check In The Console For The Output.");
			object.updateNeeded();
			if(this.updaterCheck.updateNeeded()){
				log.info("A new version is available.");
				log.info("Get It Here At: " + this.updaterCheck.getLink());
			}
		}
		
		if(commandLabel.equalsIgnoreCase("godeffects") || commandLabel.equalsIgnoreCase("ge")){
			if(args.length == 0){
			try{
			   player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999999, 9999));
			   player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999999, 10));
			   player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 9999));
			   player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 10));
			   player.sendMessage(ChatColor.GOLD + "You Have The God Effects! Drink A Bucket Of Milk To Stop The Effects.");
			}catch(Exception ex){
				log.severe("Whoa. Something Went Wrong.");
				log.severe("Printing Stacktrace: \n");
				log.severe(ex.getStackTrace().toString());
			}
			}else if(args.length == 1){
				if(player.getServer().getPlayer(args[0])!=null){
					Player targetPlayer = player.getServer().getPlayer(args[0]);
				try{
					   player.sendMessage(ChatColor.GOLD + "This Person Now Has God Effects");
					   targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 999999999, 9999));
					   targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 999999999, 30));
					   targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 999999999, 9999));
					   targetPlayer.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999999, 10));
					   targetPlayer.sendMessage(ChatColor.GOLD + "You Have The God Effects! Drink A Bucket Of Milk To Stop The Effects.");
					}catch(Exception ex){
						log.severe("Whoa. Something Went Wrong.");
						log.severe("Printing Stacktrace: \n");
						log.severe(ex.getStackTrace().toString());
					}
			
			}else{
				player.sendMessage(ChatColor.RED + "Error: " + ChatColor.BOLD + "That Player Isn't Online");
			}
			}
			}
		
		return false;
	}
}
