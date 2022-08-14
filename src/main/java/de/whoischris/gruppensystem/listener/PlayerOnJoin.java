package de.whoischris.gruppensystem.listener;

import de.whoischris.gruppensystem.GruppenSystem;
import de.whoischris.gruppensystem.repos.MySQLPlayerRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class PlayerOnJoin implements Listener {

    private MySQLPlayerRepository repository;

    public PlayerOnJoin(MySQLPlayerRepository repository) {
        this.repository = repository;
        GruppenSystem.getInstance().getServer().getPluginManager().registerEvents(this, GruppenSystem.getInstance());
    }

    @EventHandler
    public void onAsyncPreLogin(AsyncPlayerPreLoginEvent event) {
        UUID uuid = event.getUniqueId();

        if(!repository.isPlayerInGroup(uuid)) {
            repository.setPlayerDefaultGroup(uuid);
        }

    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();



    }

}
