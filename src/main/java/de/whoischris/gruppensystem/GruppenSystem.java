package de.whoischris.gruppensystem;

import de.whoischris.gruppensystem.cache.Cache;
import de.whoischris.gruppensystem.commands.GroupCommand;
import de.whoischris.gruppensystem.listener.PlayerChatEvent;
import de.whoischris.gruppensystem.listener.PlayerOnJoin;
import de.whoischris.gruppensystem.mysql.MySQL;
import de.whoischris.gruppensystem.mysql.Query;
import de.whoischris.gruppensystem.repos.MySQLGroupRepository;
import de.whoischris.gruppensystem.repos.MySQLPlayerRepository;
import de.whoischris.gruppensystem.utils.Data;
import org.bukkit.plugin.java.JavaPlugin;

public final class GruppenSystem extends JavaPlugin {

    public static GruppenSystem instance;
    public static Data data;
    public static MySQL mysql;
    public static Query query;
    public static MySQLGroupRepository mySQLGroupRepository;
    public static MySQLPlayerRepository mySQLPlayerRepository;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;
        data = new Data(getConfig());
        registerConfig();
        mysql = new MySQL(data);
        query = new Query();
        new Cache();
        mySQLGroupRepository = new MySQLGroupRepository();
        mySQLPlayerRepository = new MySQLPlayerRepository();
        registerCommands();
        registerListener();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        instance = null;
    }

    private void registerCommands() {
        getCommand("group").setExecutor(new GroupCommand(getData(), getMySQLGroupRepository()));
    }

    private void registerListener() {
        new PlayerOnJoin(mySQLPlayerRepository);
        new PlayerChatEvent(mySQLPlayerRepository);
    }

    public static Data getData() {
        return data;
    }

    public static MySQLGroupRepository getMySQLGroupRepository() {
        return mySQLGroupRepository;
    }

    public static MySQLPlayerRepository getMySQLPlayerRepository() {
        return mySQLPlayerRepository;
    }

    private void registerConfig() {
        getConfig().options().copyDefaults(true);
        saveConfig();
    }

    public static GruppenSystem getInstance() {
        return instance;
    }
}
