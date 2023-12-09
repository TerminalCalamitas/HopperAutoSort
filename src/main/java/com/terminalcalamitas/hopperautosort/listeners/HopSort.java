package com.terminalcalamitas.hopperautosort.listeners;

import org.bukkit.block.Container;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class HopSort implements Listener {

    public HopSort() {}

    private String getItemName (String translationKey) {
        if(translationKey == null) return null;
        String[] names = translationKey.split("\\.");
        return names[names.length-1];
    }

    private boolean filterMatch (String filterString, String fullItemName) {
        String itemName = getItemName(fullItemName);
        String[] filter = filterString.split(",");

        return Arrays.stream(filter).anyMatch((filter_i) -> {

            if(filter_i.endsWith("*")) {
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
        return (filter[1].equals("dougisgoodat2dplatformers|"));
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
                }else if(!filterMatch(customName, itemName)) {
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
}
