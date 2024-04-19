package dev.toutcourt.reactmenus.core.models;

import dev.toutcourt.reactmenus.ReactMenus;
import dev.toutcourt.reactmenus.core.interfaces.Menu;
import dev.toutcourt.reactmenus.core.managers.CustomMenuManager;
import dev.toutcourt.reactmenus.core.utils.ChatColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.checkerframework.checker.nullness.qual.NonNull;

public abstract class InventoryMenu implements InventoryHolder, Menu {
    protected Inventory inventory;
    protected Player player;
    protected boolean isForcingOpening;
    public InventoryMenu(@NonNull Player player, boolean isForcingOpening) {
        this.player = player;
        this.inventory = Bukkit.createInventory(this, this.getInventorySize(), ChatColorUtils.format('&', this.getInventoryTitle()));
        this.isForcingOpening = isForcingOpening;
    }

    public InventoryMenu(@NonNull Player player) {
        this(player, false);
    }
    @Override
    public void open() {
        if (!this.isForcingOpening && ReactMenus.getInstance().getCustomMenuManager().isPlayerInMenu(this.player)) {
            return;
        }
        CustomMenuManager customMenuManager = ReactMenus.getInstance().getCustomMenuManager();
        Menu playerMenu = customMenuManager.getPlayerMenu(player);

        if (playerMenu != null) {
            playerMenu.close();
        }
        this.setup();
        this.player.openInventory(this.inventory);
        ReactMenus.getInstance().getCustomMenuManager().registerPlayerMenu(this.player, this);
    }

    @Override
    public void close() {
        this.closeMenu();
        this.forceClose();
    }

    public void forceClose() {
        ReactMenus.getInstance().getCustomMenuManager().unregisterPlayerMenu(this.player);
        this.player.closeInventory();
    }

    @Override
    @NonNull
    public Inventory getInventory() {
        return this.inventory;
    }

    protected abstract void closeMenu();
    public abstract void handle(@NonNull InventoryClickEvent event);
    public abstract int getInventorySize();
    public abstract String getInventoryTitle();


}
