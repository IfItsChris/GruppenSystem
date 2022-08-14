package de.whoischris.gruppensystem.manager;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

//Das ist ein Snippet von einem alten Plugin, was ich programmiert habe

public class ScoreboardManager {

    public static void setTab(Player player) {

        Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        Team groupTeam = (scoreboard.getTeam("01inhaber") != null) ? scoreboard.getTeam("01inhaber") : scoreboard.registerNewTeam("01inhaber");
        Team group = null;
        //String tag = ClanTagAddon.getApi().getClanTagFromPlayer(player);
        //group = (scoreboard.getTeam("01" + tag) != null) ? scoreboard.getTeam("01" + tag) : scoreboard.registerNewTeam("01" + tag);
        group.setPrefix("");
        group.setSuffix(" + tag + ");
            group.addPlayer((OfflinePlayer) player);

        for (Player all : Bukkit.getOnlinePlayers()) {
            all.setScoreboard(scoreboard);
        }
    }
}
