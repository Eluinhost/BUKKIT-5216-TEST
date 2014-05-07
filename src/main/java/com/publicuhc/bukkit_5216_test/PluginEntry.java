package com.publicuhc.bukkit_5216_test;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginEntry extends JavaPlugin implements Listener {

    private boolean m_cancelling;

    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("cancelsatiated")) {
            m_cancelling = !m_cancelling;
            sender.sendMessage(ChatColor.GOLD + (m_cancelling ? "Canelling events" : "No longer cancelling events"));
            return true;
        } else if(command.getName().equalsIgnoreCase("setparams")) {
            if(!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Can only be ran as a player!");
                return true;
            }
            Player player = (Player) sender;
            player.setHealth(15);
            player.setFoodLevel(19);

            player.sendMessage(ChatColor.GOLD + "Set health/food level");
            return true;
        }

        return false;
    }

    @EventHandler
    public void onEntityRegainHealthEvent(EntityRegainHealthEvent erhe) {
        if(!(erhe.getEntity() instanceof Player)) {
            return;
        }
        if(m_cancelling) {
            erhe.setCancelled(true);
        }
        getServer().getScheduler().scheduleSyncDelayedTask(
                this,
                new PlayerReporter((Player) erhe.getEntity(), m_cancelling),
                1);
    }
}
