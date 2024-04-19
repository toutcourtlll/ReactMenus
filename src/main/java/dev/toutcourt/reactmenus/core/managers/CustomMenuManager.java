package dev.toutcourt.reactmenus.core.managers;

import dev.toutcourt.reactmenus.ReactMenus;
import dev.toutcourt.reactmenus.core.interfaces.Manager;
import dev.toutcourt.reactmenus.core.interfaces.Menu;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;


public class CustomMenuManager implements Manager {
    private final ReactMenus plugin;
    private final HashMap<Player, Menu> playerMenuHashMap;

    public CustomMenuManager(@NonNull ReactMenus plugin) {
        this.plugin = plugin;
        this.playerMenuHashMap = new HashMap<>();
    }

    public void registerPlayerMenu(@NonNull Player player, @NonNull Menu menu) {
        this.playerMenuHashMap.put(player, menu);
    }

    public boolean unregisterPlayerMenu(@NonNull Player player) {
        return this.playerMenuHashMap.remove(player) != null;
    }

    public boolean isPlayerInMenu(@NonNull Player player) {
        return this.playerMenuHashMap.containsKey(player);
    }

    public boolean isPlayerInMenu(@NonNull String playerName) {
        Player player = Bukkit.getPlayer(playerName);
        if (player == null) return false;
        return this.isPlayerInMenu(player);
    }

    @Nullable
    public Menu getPlayerMenu(@NonNull Player player) {
        return this.playerMenuHashMap.get(player);
    }

    @Override
    public void disable() {
        for (Menu menu : this.playerMenuHashMap.values()) {
            menu.close();
        }
        this.playerMenuHashMap.clear();
    }
}
