package de.whoischris.gruppensystem.repos;

import de.whoischris.gruppensystem.mysql.Query;
import de.whoischris.gruppensystem.repos.impl.PlayerRepository;

import java.util.UUID;

public class MySQLPlayerRepository implements PlayerRepository {

    @Override
    public boolean isPlayerInGroup(UUID uuid) {
        return Query.existsInTable("SELECT * FROM players WHERE uuid = ?", new Object[]{uuid.toString()});
    }

    @Override
    public void setPlayerDefaultGroup(UUID uuid) {
        Query.execute("INSERT INTO players (uuid, groupid, duration) VALUES (?, ?, ?)", new Object[]{uuid.toString(),
                (Integer) Query.select("SELECT id FROM groups WHERE name = ?", new Object[]{}), "-1"});
    }

    @Override
    public String getPlayerGroupPrefix(UUID uuid) {
        return Query.select("SELECT prefix FROM groups, players WHERE players.groupid = groups.id AND players.uuid = ?", new Object[]{uuid.toString()}).toString();
    }
}
