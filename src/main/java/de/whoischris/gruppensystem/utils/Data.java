package de.whoischris.gruppensystem.utils;

import de.whoischris.gruppensystem.GruppenSystem;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

public class Data {

    private final FileConfiguration config;
    private HashMap<String, String> connectionData;
    private String prefix;
    private String defaultGroupName;
    private String defaultGroupPrefix;

    public Data(FileConfiguration config) {
        this.config = config;
        init();
    }

    public String getPrefix() {
        return prefix;
    }

    public String getDefaultGroupName() {
        return defaultGroupName;
    }

    public String getDefaultGroupPrefix() {
        return defaultGroupPrefix;
    }

    public String getConnectionData(String key) {
        return connectionData.get(key);
    }

    private void init() {
        prefix = config.getString("Prefix") != null ? ChatColor.translateAlternateColorCodes('&', config.getString("Prefix")) : "§aGruppenSystem §7» ";
        defaultGroupName = config.getString("DefaultGroup.Name") != null ? ChatColor.translateAlternateColorCodes('&', config.getString("DefaultGroup.Name")) : "Spieler";
        defaultGroupPrefix = config.getString("DefaultGroup.Prefix") != null ? ChatColor.translateAlternateColorCodes('&', config.getString("DefaultGroup.Prefix")) : "Spieler";
        this.connectionData = new HashMap<String, String>();
        connectionData.put("host", GruppenSystem.getInstance().getConfig().getString("MySQL.Host"));
        connectionData.put("port", GruppenSystem.getInstance().getConfig().getString("MySQL.Port"));
        connectionData.put("database", GruppenSystem.getInstance().getConfig().getString("MySQL.Database"));
        connectionData.put("user", GruppenSystem.getInstance().getConfig().getString("MySQL.User"));
        connectionData.put("password", GruppenSystem.getInstance().getConfig().getString("MySQL.Password"));
    }


}
