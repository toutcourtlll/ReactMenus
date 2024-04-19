package dev.toutcourt.reactmenus.core.impl.inventory_menu;

import dev.toutcourt.reactmenus.core.models.InventoryMenu;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.checkerframework.checker.nullness.qual.NonNull;

public class ExampleMenu extends InventoryMenu {
    private ItemStack voidItem;
    private ItemStack yesItem;
    private ItemStack noItem;
    public ExampleMenu(@NonNull Player player) {
        super(player);

        this.initVoidItem();
        this.initYesItem();
        this.initNoItem();
    }
    private void initVoidItem() {
        this.voidItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta voidItemMeta = this.voidItem.getItemMeta();
        assert voidItemMeta != null;
        voidItemMeta.setDisplayName("");
        this.voidItem.setItemMeta(voidItemMeta);
    }
    private void initYesItem() {
        this.yesItem = new ItemStack(Material.GREEN_WOOL);
        ItemMeta itemMeta = this.yesItem.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.GREEN+"YES");
        this.yesItem.setItemMeta(itemMeta);
    }
    private void initNoItem() {
        this.noItem = new ItemStack(Material.RED_WOOL);
        ItemMeta itemMeta = this.noItem.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(ChatColor.RED+"NO");
        this.noItem.setItemMeta(itemMeta);
    }
    @Override
    public void setup() {
        for (int i = 0; i < 27; i++) {
            this.inventory.setItem(i, this.voidItem);
        }
        this.inventory.setItem(10, yesItem);
        this.inventory.setItem(16, noItem);
    }


    @Override
    protected void closeMenu() {
        this.player.sendMessage(ChatColor.RED+"closing menu");
    }

    @Override
    public void handle(@NonNull InventoryClickEvent event) {
        event.setCancelled(true);
        if (event.getClickedInventory() != this.inventory) {
            return;
        }
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack == null) {
            return;
        }

        if (itemStack.equals(yesItem)) {
            this.player.sendMessage(ChatColor.GREEN+"YEEEES !");
            this.forceClose();
        }
        if (itemStack.equals(noItem)) {
            this.player.sendMessage(ChatColor.RED+"NOOO !");
            this.close();
        }

    }

    @Override
    public int getInventorySize() {
        return 27;
    }

    @Override
    public String getInventoryTitle() {
        return "&4&lSuper Menu rouge foncé";
    }
}
