package de.whoischris.gruppensystem.repos.impl;

import de.whoischris.gruppensystem.objects.Group;

import java.util.UUID;

public interface PlayerRepository {

    boolean isPlayerInGroup(UUID uuid);
    void setPlayerDefaultGroup(UUID uuid);
    String getPlayerGroupPrefix(UUID uuid);
}
