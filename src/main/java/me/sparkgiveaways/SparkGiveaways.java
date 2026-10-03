package me.sparkgiveaways;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class SparkGiveaways extends JavaPlugin implements Listener {

    private static final String MENU_TITLE =
            ChatColor.DARK_GRAY + "Public Giveaways";

    private static SparkGiveaways instance;

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getPluginManager().registerEvents(this, this);

        if (getCommand("giveaways") != null) {
            getCommand("giveaways").setExecutor((sender, command, label, args) -> {

                if (!(sender instanceof Player player)) {
                    sender.sendMessage("Only players can use this command.");
                    return true;
                }

                openGiveawaysMenu(player);
                return true;
            });
        }

        getLogger().info("SparkGiveaways has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SparkGiveaways has been disabled!");
    }

    public static SparkGiveaways getInstance() {
        return instance;
    }

    /*
     * =========================
     * PUBLIC GIVEAWAYS MENU
     * =========================
     */

    public void openGiveawaysMenu(Player player) {

        Inventory inventory = Bukkit.createInventory(
                null,
                54,
                MENU_TITLE
        );

        /*
         * Background
         */

        ItemStack background = createItem(
                Material.GRAY_STAINED_GLASS_PANE,
                " "
        );

        for (int i = 0; i < 54; i++) {
            inventory.setItem(i, background);
        }

        /*
         * Example giveaway
         */

        ItemStack giveaway = createGiveawayItem();

        inventory.setItem(10, giveaway);

        /*
         * Bottom buttons
         */

        inventory.setItem(
                45,
                createItem(
                        Material.HOPPER,
                        ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "YOUR ITEMS",
                        ChatColor.GRAY + "Click here to look at",
                        ChatColor.GRAY + "all your won items!"
                )
        );

        inventory.setItem(
                48,
                createItem(
                        Material.RED_STAINED_GLASS_PANE,
                        ChatColor.RED + "" + ChatColor.BOLD + "PREVIOUS PAGE",
                        ChatColor.GRAY + "Click here to go back",
                        ChatColor.GRAY + "to the previous page!"
                )
        );

        inventory.setItem(
                49,
                createItem(
                        Material.CLOCK,
                        ChatColor.YELLOW + "" + ChatColor.BOLD + "REFRESH",
                        ChatColor.GRAY + "Click here to refresh",
                        ChatColor.GRAY + "the giveaway menu!"
                )
        );

        inventory.setItem(
                50,
                createItem(
                        Material.LIME_STAINED_GLASS_PANE,
                        ChatColor.GREEN + "" + ChatColor.BOLD + "NEXT PAGE",
                        ChatColor.GRAY + "Click here to go",
                        ChatColor.GRAY + "to the next page!"
                )
        );

        inventory.setItem(
                53,
                createItem(
                        Material.TOTEM_OF_UNDYING,
                        ChatColor.GOLD + "" + ChatColor.BOLD + "CREATE GIVEAWAY",
                        ChatColor.GRAY + "Click here to create",
                        ChatColor.GRAY + "your own public giveaway!"
                )
        );

        player.openInventory(inventory);
    }

    /*
     * =========================
     * GIVEAWAY ITEM
     * =========================
     */

    private ItemStack createGiveawayItem() {

        ItemStack item = new ItemStack(Material.WRITABLE_BOOK);

        ItemMeta meta = item.getItemMeta();

        if (meta != null) {

            meta.setDisplayName(
                    ChatColor.GOLD + "" + ChatColor.BOLD
                            + "Physics_Gamerz's GIVEAWAY"
            );

            List<String> lore = new ArrayList<>();

            lore.add(ChatColor.DARK_GRAY + "[GIVEAWAYS]");
            lore.add("");

            lore.add(ChatColor.YELLOW + "Description:");
            lore.add(ChatColor.GRAY + "| "
                    + ChatColor.WHITE + "Click on me to enter");
            lore.add(ChatColor.GRAY + "| "
                    + ChatColor.WHITE + "Physics_Gamerz's ongoing giveaway!");
            lore.add("");

            lore.add(ChatColor.YELLOW + "Giveaway List:");
            lore.add(ChatColor.GRAY + "| "
                    + ChatColor.WHITE + "1x Netherite Pickaxe");
            lore.add(ChatColor.GRAY + "| "
                    + ChatColor.WHITE + "1x Diamond Axe");
            lore.add(ChatColor.GRAY + "| "
                    + ChatColor.WHITE + "61x Cooked Beef");
            lore.add("");

            lore.add(ChatColor.GOLD + "💰 "
                    + ChatColor.WHITE + "Entries: "
                    + ChatColor.GREEN + "1");

            lore.add(ChatColor.GOLD + "⏱ "
                    + ChatColor.WHITE + "Ends In: "
                    + ChatColor.GREEN + "23h 59m");

            lore.add("");

            lore.add(ChatColor.YELLOW + "➤ "
                    + ChatColor.WHITE + "LEFT-CLICK "
                    + ChatColor.YELLOW + "to Enter");

            lore.add(ChatColor.YELLOW + "➤ "
                    + ChatColor.WHITE + "RIGHT-CLICK "
                    + ChatColor.YELLOW + "to View Items");

            meta.setLore(lore);

            item.setItemMeta(meta);
        }

        return item;
    }

    /*
     * =========================
     * ITEM CREATOR
     * =========================
     */

    private ItemStack createItem(
            Material material,
            String name,
            String... loreLines
    ) {

        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();

        if (meta != null) {

            meta.setDisplayName(name);

            if (loreLines.length > 0) {

                List<String> lore = new ArrayList<>();

                for (String line : loreLines) {
                    lore.add(line);
                }

                meta.setLore(lore);
            }

            item.setItemMeta(meta);
        }

        return item;
    }

    /*
     * =========================
     * GUI CLICK HANDLER
     * =========================
     */

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {

        if (!(event.getWhoClicked() instanceof Player player)) {
            return;
        }

        if (!event.getView().getTitle().equals(MENU_TITLE)) {
            return;
        }

        event.setCancelled(true);

        int slot = event.getRawSlot();

        /*
         * Giveaway
         */

        if (slot == 10) {

            if (event.isLeftClick()) {

                player.sendMessage(
                        ChatColor.GREEN
                                + "You entered the giveaway!"
                );

                return;
            }

            if (event.isRightClick()) {

                player.sendMessage(
                        ChatColor.YELLOW
                                + "Viewing giveaway items..."
                );

                return;
            }
        }

        /*
         * Your Items
         */

        if (slot == 45) {

            player.sendMessage(
                    ChatColor.LIGHT_PURPLE
                            + "Opening your won items..."
            );

            return;
        }

        /*
         * Previous page
         */

        if (slot == 48) {

            player.sendMessage(
                    ChatColor.RED
                            + "You are already on the first page."
            );

            return;
        }

        /*
         * Refresh
         */

        if (slot == 49) {

            openGiveawaysMenu(player);

            player.sendMessage(
                    ChatColor.GREEN
                            + "Giveaways menu refreshed!"
            );

            return;
        }

        /*
         * Next page
         */

        if (slot == 50) {

            player.sendMessage(
                    ChatColor.GREEN
                            + "There are no more pages."
            );

            return;
        }

        /*
         * Create Giveaway
         */

        if (slot == 53) {

            player.closeInventory();

            player.sendMessage("");
            player.sendMessage(
                    ChatColor.GOLD + "" + ChatColor.BOLD
                            + "SparkGiveaways"
            );
            player.sendMessage(
                    ChatColor.YELLOW
                            + "Create Giveaway system coming next!"
            );
            player.sendMessage("");

        }
    }
}
