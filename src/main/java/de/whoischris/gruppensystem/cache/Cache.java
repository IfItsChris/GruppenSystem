package de.whoischris.gruppensystem.cache;

import de.whoischris.gruppensystem.mysql.Query;
import de.whoischris.gruppensystem.objects.Group;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Cache {

    public Cache() {
        cacheGroups();
    }

    public static HashMap<String, Group> groups = new HashMap<>();

    public static HashMap<String, Group> getGroups() {
        return groups;
    }

    public static void getPlayerGroup(String uuid) {
        for(String key : groups.keySet()) {

        }
    }

    private void cacheGroups() {
        List<HashMap<String, Object>> data = Query.selectAll("SELECT * FROM groups", (short) 3, new Object[]{});
        for (HashMap<String, Object> set : data) {
            Group group = new Group(set.get("name").toString(), set.get("prefix").toString()).setId((Integer) set.get("id")).cache();
        }
    }

}
