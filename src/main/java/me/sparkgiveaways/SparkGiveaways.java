package me.sparkgiveaways;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SparkGiveaways extends JavaPlugin {

    private static SparkGiveaways instance;
    private GiveawayGUI giveawayGUI;

    @Override
    public void onEnable() {

        instance = this;

        giveawayGUI = new GiveawayGUI(this);

        getLogger().info("================================");
        getLogger().info("      SparkGiveaways v1.0");
        getLogger().info("      Plugin Enabled!");
        getLogger().info("================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("SparkGiveaways disabled!");
    }

    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {

        if (command.getName().equalsIgnoreCase("giveaways")) {

            if (!(sender instanceof Player player)) {
                sender.sendMessage("Only players can use this command.");
                return true;
            }

            giveawayGUI.open(player);
            return true;
        }

        return false;
    }

    public static SparkGiveaways getInstance() {
        return instance;
    }
}
