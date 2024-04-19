package dev.toutcourt.reactmenus;

import dev.toutcourt.reactmenus.core.listeners.InventoryEventsListener;
import dev.toutcourt.reactmenus.core.managers.CustomMenuManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class ReactMenus extends JavaPlugin {
    private static ReactMenus instance;
    private CustomMenuManager customMenuManager;

    @Override
    public void onEnable() {
        this.customMenuManager = new CustomMenuManager(instance);
        instance = this;
        this.getServer().getPluginManager().registerEvents(new InventoryEventsListener(), this);
    }

    @Override
    public void onDisable() {
        this.customMenuManager.disable();
    }

    public static ReactMenus getInstance() {
        return instance;
    }

    public CustomMenuManager getCustomMenuManager() {
        return customMenuManager;
    }
}
