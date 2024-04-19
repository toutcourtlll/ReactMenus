package dev.toutcourt.reactmenus.core.interfaces;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public interface Menu {

    void open();
    void close();
    void setup();
}
