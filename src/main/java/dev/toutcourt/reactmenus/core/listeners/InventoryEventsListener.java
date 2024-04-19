package dev.toutcourt.reactmenus.core.listeners;

import dev.toutcourt.reactmenus.ReactMenus;
import dev.toutcourt.reactmenus.core.models.InventoryMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class InventoryEventsListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();
        InventoryHolder inventoryHolder = inventory.getHolder();

        if (inventoryHolder == null) return;

        if (inventoryHolder instanceof InventoryMenu inventoryMenu) {
            inventoryMenu.handle(event);
        }
    }

    @EventHandler
    public void onInventoryCloseEvent(InventoryCloseEvent event) {
        Inventory inventory = event.getInventory();
        InventoryHolder inventoryHolder = inventory.getHolder();

        if (inventoryHolder == null) return;

        if (inventoryHolder instanceof InventoryMenu) {

            if (ReactMenus.getInstance().getCustomMenuManager().isPlayerInMenu((Player) event.getPlayer())) {
                InventoryMenu inventoryMenu = (InventoryMenu) inventoryHolder;
                inventoryMenu.close();
            }
        }
    }
}
