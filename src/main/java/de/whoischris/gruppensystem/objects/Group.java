package de.whoischris.gruppensystem.objects;

import de.whoischris.gruppensystem.cache.Cache;
import de.whoischris.gruppensystem.mysql.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Group {

    private String name;
    private String prefix;
    private Integer id;
    //String1 = UUID, String2 = duration (Milliseconds)
    private HashMap<UUID, String> players;

    public Group(String name, String prefix) {
        this.name = name;
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getPrefix() {
        return prefix;
    }

    public Group setName(String name) {
        this.name = name;
        return this;
    }

    public HashMap<UUID, String> getPlayers() {
        return players;
    }

    public Group addPlayer(UUID uuid, String duration) {
        players.put(uuid, duration);
        return this;
    }

    public Group setId(Integer id) {
        this.id = id;
        return this;
    }

    public Group setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public Group saveToDB() {
        Integer insertId = Query.insert("INSERT INTO groups (name, prefix) VALUES (?, ?)", new Object[]{getName(), getPrefix()});
        return this;
    }
    public Group cache() {
        Cache.groups.put(this.getName(), this);
        return this;
    }

}
