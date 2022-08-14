package de.whoischris.gruppensystem.repos.impl;

public interface GroupRepository {

    void createGroup(String name, String prefix);
    String getGroupPrefix(Integer groupId);
    Integer getGroupIdByName(String name);
    boolean existsGroup(String name);

}
