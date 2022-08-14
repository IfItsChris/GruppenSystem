package de.whoischris.gruppensystem.listener;

import de.whoischris.gruppensystem.GruppenSystem;
import de.whoischris.gruppensystem.repos.MySQLPlayerRepository;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatEvent implements Listener {

    private MySQLPlayerRepository repository;


    public PlayerChatEvent(MySQLPlayerRepository repository) {
        this.repository = repository;
        GruppenSystem.getInstance().getServer().getPluginManager().registerEvents(this, GruppenSystem.getInstance());
    }


    @EventHandler
    public void onAsyncChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String prefix = repository.getPlayerGroupPrefix(player.getUniqueId());
        event.setFormat("§7[" + prefix + "§7] §a" + player.getName() + " §8» §7" + event.getMessage());
    }

}
