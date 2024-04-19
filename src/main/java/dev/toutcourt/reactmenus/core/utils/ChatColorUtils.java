package dev.toutcourt.reactmenus.core.utils;

import net.md_5.bungee.api.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatColorUtils {
    public static String format(char s, String message) {
        if (message == null) {
            return null;
        }
        return translateHexColorCodes(s, ChatColor.translateAlternateColorCodes(s, message));
    }

    public static String translateHexColorCodes(char s, String message) {
        if (message == null) {
            return null;
        }
        Pattern pattern = Pattern.compile("" + s + "#([0-9a-fA-F]{6})");
        Matcher matcher = pattern.matcher(message);
        StringBuilder sb = new StringBuilder();

        while(matcher.find()) {
            String hexColor = matcher.group(1);
            ChatColor color = ChatColor.of("#" + hexColor);
            matcher.appendReplacement(sb, color.toString());
        }

        matcher.appendTail(sb);
        return sb.toString();
    }
}
