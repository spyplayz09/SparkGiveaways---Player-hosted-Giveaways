package me.sparkgiveaways;

import org.bukkit.plugin.java.JavaPlugin;

public class SparkGiveaways extends JavaPlugin {

    private static SparkGiveaways instance;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("================================");
        getLogger().info("      SparkGiveaways v1.0");
        getLogger().info("      Plugin Enabled!");
        getLogger().info("================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("SparkGiveaways disabled!");
    }

    public static SparkGiveaways getInstance() {
        return instance;
    }
}
