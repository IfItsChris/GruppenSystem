package de.whoischris.gruppensystem.repos;

import de.whoischris.gruppensystem.mysql.Query;
import de.whoischris.gruppensystem.objects.Group;
import de.whoischris.gruppensystem.repos.impl.GroupRepository;

public class MySQLGroupRepository implements GroupRepository {
    @Override
    public void createGroup(String name, String prefix) {
        Group group = new Group(String.valueOf(name), String.valueOf(prefix)).saveToDB().cache();
    }
    @Override
    public Integer getGroupIdByName(String name) {
        return (Integer) Query.select("SELECT id FROM groups WHERE name = ?", new Object[]{name});
    }

    @Override
    public boolean existsGroup(String name) {
        return Query.existsInTable("SELECT * FROM groups WHERE name = ?", new Object[]{name});
    }

    @Override
    public String getGroupPrefix(Integer groupId) {
        return null;
    }
}
