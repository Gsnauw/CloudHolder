package me.milka.gilles.cloudholder.cloudholder;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class CloudHolder extends JavaPlugin {

    File customConfigDataFile;
    private FileConfiguration customConfigData;
    public FileConfiguration getCustomConfigDataFile() {
        return this.customConfigData;
    }

    static List<String> holderdata = new ArrayList<String>();

    @Override
    public void onEnable() {

        this.getCommand("cloudholder").setExecutor(new CloudCommand(this));

        Bukkit.getServer().getLogger().info("[CloudHolder] Plugin enabled, Hello World");
        ConfigMaken();
        if (setupPlaceholder() ) {
            Bukkit.getServer().getLogger().info("[CloudHolder] Connectie met PlaceholderAPI gemaakt.");
            new CustomPlaceholderExpansion(this).register();
        }
        if (!setupPlaceholder() ) {
            getLogger().severe(String.format("PlaceholderAPI plugin is niet gevonden!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
        }
    }

    public void ConfigMaken() {
        customConfigDataFile = new File(getDataFolder(), "placeholdersdata.yml");
        if (!customConfigDataFile.exists()) {
            customConfigDataFile.getParentFile().mkdirs();
            saveResource("placeholdersdata.yml", false);
        }
        customConfigData = new YamlConfiguration();
        try {
            customConfigData.load(customConfigDataFile);
            holderdata = customConfigData.getStringList("PlaceholdersData");
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info("[CloudHolder] Plugin disabled");
    }

    private boolean setupPlaceholder() {
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
            return false;
        }
        return true;
    }
}
