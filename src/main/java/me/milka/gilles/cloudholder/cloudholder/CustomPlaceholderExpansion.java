package me.milka.gilles.cloudholder.cloudholder;

import me.clip.placeholderapi.PlaceholderHook;
import org.bukkit.entity.Player;

public class placeholdertest extends PlaceholderHook {

    @Override
    public String onPlaceholderRequest(Player p, String identifier) {
        // %<your plugin name>_yourcustomplaceholder%
        if (identifier.equals("yourcustomplaceholder")) {
            return "works!";
        }
        return null;
    }

}
