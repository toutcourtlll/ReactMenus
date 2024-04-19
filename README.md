
# ReactMenus

## Description
ReactMenus is an API for Spigot that simplifies the creation of custom inventory menus in Minecraft. It automatically handles event listening, inventory matching, and other essential functions, facilitating rapid and efficient interactive menu development.

## Features
- **Menu Creation Simplification**: Developers can create custom menus without worrying about event listeners or inventory state management.
- **`InventoryMenu` Class**: Streamlines menu creation by encapsulating necessary functionalities within an abstract class.
- **HexaDecimal colors**: This plugin support the usage of Hexadecimal colors. You can use the ChatColorUtils class to convert your raw messages to colored messages

## Example Usage
Below is a simplified example of how to create a custom menu using ReactMenus. This example demonstrates how to set up a menu with options "YES" and "NO".

```java
import dev.toutcourt.reactmenus.core.models.InventoryMenu;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class SimpleMenu extends InventoryMenu {
    private ItemStack yesItem, noItem;

    public SimpleMenu(Player player) {
        super(player);
        // Define YES and NO items directly
        yesItem = new ItemStack(Material.GREEN_WOOL);
        noItem = new ItemStack(Material.RED_WOOL);
        
        // Set display names for items
        ItemMeta yesMeta = yesItem.getItemMeta();
        yesMeta.setDisplayName(ChatColor.GREEN+"YES");
        yesItem.setItemMeta(yesMeta);
        ItemMeta noMeta = noItem.getItemMeta();
        noMeta.setDisplayName(ChatColor.RED+"NO");
        noItem.setItemMeta(noMeta);
    }

    @Override
    public void setup() {
        // Initialize inventory with empty items and the YES/NO options
        for (int i = 0; i < getInventorySize(); i++) {
            inventory.setItem(i, new ItemStack(Material.GRAY_STAINED_GLASS_PANE));
        }
        inventory.setItem(11, yesItem);
        inventory.setItem(15, noItem);
    }

    @Override
    public void handle(InventoryClickEvent event) {
        event.setCancelled(true);
        ItemStack clicked = event.getCurrentItem();
        if (clicked == null || !event.getClickedInventory().equals(inventory)) return;

        if (clicked.equals(yesItem)) {
            player.sendMessage("You clicked YES!");
            close();
        } else if (clicked.equals(noItem)) {
            player.sendMessage("You clicked NO!");
            close();
        }
    }

    @Override
    protected void closeMenu() {
        player.sendMessage("Menu is closing");
    }

    @Override
    public int getInventorySize() { return 27; }
    @Override
    public String getInventoryTitle() { return "Simple Menu"; }
}
```

## Installation
To install ReactMenus, follow these steps:
1. Download the ReactMenus JAR file.
2. Place it in the `plugins` folder of your Spigot server.
3. Restart your server so that the plugin is loaded.

## Maven and Gradle Setup
### Maven
Add the following repository and dependency to your `pom.xml`:
```xml
<repository>
  <id>github</id>
  <url>https://maven.pkg.github.com/toutcourtlll/ReactMenus</url>
</repository>
```
```xml
<dependencies>
  <dependency>
    <groupId>dev.toutcourt</groupId>
    <artifactId>reactmenus</artifactId>
    <version>1.0</version>
  </dependency>
</dependencies>
```

### Gradle
Add the following repository and dependency to your `build.gradle`:
```groovy
repositories {
    maven {
        url 'https://maven.pkg.github.com/toutcourtlll/ReactMenus'
    }
}

dependencies {
    compileOnly 'dev.toutcourt:reactmenus:1.0'
}
```

## Contribution
Currently, ReactMenus is maintained only by me ! (Toutcourt)

## License
ReactMenus is distributed under the MIT License, which allows great flexibility in usage, including modification, private and commercial distribution. This license imposes very few restrictions, essentially allowing you to do whatever you want with the code, provided the copyright notice and license itself are included in all copies or substantial portions of the software.
