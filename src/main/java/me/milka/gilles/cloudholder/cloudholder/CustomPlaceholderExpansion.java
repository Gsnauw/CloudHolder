package me.milka.gilles.cloudholder.cloudholder;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomPlaceholderExpansion extends PlaceholderExpansion {

    private final JavaPlugin plugin;

    public CustomPlaceholderExpansion(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean canRegister() {
        // Return true if the plugin can register the expansion (e.g., if PlaceholderAPI is present)
        return true;
    }

    @Override
    public String getAuthor() {
        return "Gilles";
    }

    @Override
    public String getIdentifier() {
        return "cloudholder"; // Replace with your plugin's identifier
    }

    @Override
    public String getVersion() {
        return plugin.getDescription().getVersion();
    }

    @Override
    public String onPlaceholderRequest(Player player, String identifier) {
        for (String blk : CloudHolder.holderdata) {
            String[] splitted = blk.split(":");
            String naam = splitted[0];
            String value = splitted[1];
            if (identifier.equals(naam)) {
                return String.valueOf(value);
            }
        }
        return null;
    }
}

