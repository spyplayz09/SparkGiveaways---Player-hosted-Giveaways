package me.sparkgiveaways;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GiveawayGUI {

    private final SparkGiveaways plugin;

    public GiveawayGUI(SparkGiveaways plugin) {
        this.plugin = plugin;
    }

    public void open(Player player) {

        Inventory inventory = Bukkit.createInventory(
                null,
                54,
                ChatColor.DARK_GRAY + "Public Giveaways"
        );

        // Border
        ItemStack border = createItem(
                Material.GRAY_STAINED_GLASS_PANE,
                " "
        );

        int[] borderSlots = {
                0, 1, 2, 3, 4, 5, 6, 7, 8,
                9, 17,
                18, 26,
                27, 35,
                36, 44,
                45, 46, 47, 48, 49, 50, 51, 52, 53
        };

        for (int slot : borderSlots) {
            inventory.setItem(slot, border);
        }

        // Example giveaway
        ItemStack giveaway = createItem(
                Material.BOOK,
                ChatColor.GOLD + "Example Giveaway",
                ChatColor.GRAY + "[GIVEAWAYS]",
                "",
                ChatColor.YELLOW + "Description:",
                ChatColor.WHITE + "| Click on me to enter",
                ChatColor.WHITE + "| Example ongoing giveaway!",
                "",
                ChatColor.YELLOW + "Giveaway List:",
                ChatColor.WHITE + "| 1x Diamond Pickaxe",
                ChatColor.WHITE + "| 1x Diamond Axe",
                ChatColor.WHITE + "| 64x Cooked Beef",
                "",
                ChatColor.GOLD + "Entries: " + ChatColor.WHITE + "0",
                ChatColor.GREEN + "Ends In: " + ChatColor.WHITE + "23h 59m",
                "",
                ChatColor.YELLOW + "➤ LEFT-CLICK to Enter",
                ChatColor.YELLOW + "➤ RIGHT-CLICK to View Items"
        );

        inventory.setItem(10, giveaway);

        // Previous Page
        ItemStack previous = createItem(
                Material.RED_DYE,
                ChatColor.RED + "PREVIOUS PAGE",
                ChatColor.GRAY + "[GIVEAWAYS]",
                "",
                ChatColor.RED + "Description:",
                ChatColor.WHITE + "| Click here to go back",
                ChatColor.WHITE + "| to the Previous page!",
                "",
                ChatColor.YELLOW + "➤ CLICK to Return"
        );

        inventory.setItem(48, previous);

        // Refresh
        ItemStack refresh = createItem(
                Material.CLOCK,
                ChatColor.YELLOW + "REFRESH",
                ChatColor.GRAY + "[GIVEAWAYS]",
                "",
                ChatColor.YELLOW + "Description:",
                ChatColor.WHITE + "| Click to refresh",
                ChatColor.WHITE + "| the giveaway menu!",
                "",
                ChatColor.YELLOW + "➤ CLICK to Refresh"
        );

        inventory.setItem(49, refresh);

        // Next Page
        ItemStack next = createItem(
                Material.LIME_DYE,
                ChatColor.GREEN + "NEXT PAGE",
                ChatColor.GRAY + "[GIVEAWAYS]",
                "",
                ChatColor.GREEN + "Description:",
                ChatColor.WHITE + "| Click here to go",
                ChatColor.WHITE + "| to the Next page!",
                "",
                ChatColor.YELLOW + "➤ CLICK to Proceed"
        );

        inventory.setItem(50, next);

        // Your Items
        ItemStack items = createItem(
                Material.HOPPER,
                ChatColor.LIGHT_PURPLE + "YOUR ITEMS",
                ChatColor.GRAY + "[GIVEAWAYS]",
                "",
                ChatColor.LIGHT_PURPLE + "Description:",
                ChatColor.WHITE + "| Click here to look at",
                ChatColor.WHITE + "| all your won items!",
                "",
                ChatColor.YELLOW + "➤ CLICK to View"
        );

        inventory.setItem(45, items);

        // Create Giveaway
        ItemStack create = createItem(
                Material.TOTEM_OF_UNDYING,
                ChatColor.GOLD + "CREATE GIVEAWAY",
                ChatColor.GRAY + "[GIVEAWAYS]",
                "",
                ChatColor.GOLD + "Description:",
                ChatColor.WHITE + "| Click here to create",
                ChatColor.WHITE + "| your own public giveaway!",
                "",
                ChatColor.YELLOW + "➤ CLICK to Create"
        );

        inventory.setItem(53, create);

        player.openInventory(inventory);
    }

    private ItemStack createItem(
            Material material,
            String name,
            String... lore
    ) {

        ItemStack item = new ItemStack(material);

        ItemMeta meta = item.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(Arrays.asList(lore));
            item.setItemMeta(meta);
        }

        return item;
    }
}
