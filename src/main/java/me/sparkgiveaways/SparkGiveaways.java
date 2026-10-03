package me.sparkgiveaways;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public final class SparkGiveaways extends JavaPlugin implements TabExecutor {

    private static SparkGiveaways instance;

    @Override
    public void onEnable() {
        instance = this;

        getCommand("giveaways").setExecutor(this);
        getCommand("giveaways").setTabCompleter(this);

        getLogger().info("SparkGiveaways has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SparkGiveaways has been disabled!");
    }

    public static SparkGiveaways getInstance() {
        return instance;
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("This command can only be used by a player.");
            return true;
        }

        if (args.length == 0) {
            openGiveawaysMenu(player);
            return true;
        }

        return true;
    }

    private void openGiveawaysMenu(Player player) {
        player.sendMessage(
                ChatColor.GOLD + "Opening SparkGiveaways..."
        );

        // GUI will be added next.
    }
}
