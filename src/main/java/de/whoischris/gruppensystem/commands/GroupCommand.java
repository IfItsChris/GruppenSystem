package de.whoischris.gruppensystem.commands;

import de.whoischris.gruppensystem.GruppenSystem;
import de.whoischris.gruppensystem.cache.Cache;
import de.whoischris.gruppensystem.objects.Group;
import de.whoischris.gruppensystem.repos.MySQLGroupRepository;
import de.whoischris.gruppensystem.utils.Data;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GroupCommand implements CommandExecutor {

    private Data data;
    private MySQLGroupRepository repository;

    public GroupCommand(Data data, MySQLGroupRepository repository) {
        this.data = data;
        this.repository = repository;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            switch (args.length) {
                case 0:
                    player.sendMessage(data.getPrefix() + "§c/group create <Name> <Präfix>");
                    player.sendMessage(data.getPrefix() + "§c/group add <Spieler>");
                    player.sendMessage(data.getPrefix() + "§c/group listgroups");
                    break;
                case 1:

                    if(args[0].equalsIgnoreCase("listgroups")) {
                        if(Cache.getGroups().size() < 1) {
                            player.sendMessage(data.getPrefix() + "§cEs existieren keine Gruppen");
                            return false;
                        }

                        for(String key : Cache.getGroups().keySet()) {
                            Group group = Cache.getGroups().get(key);
                            player.sendMessage(data.getPrefix() + "Gruppe §a" + group.getName() + "§7:");
                            player.sendMessage(data.getPrefix() + "    §aPrefix: §7\"" + group.getPrefix() + "\"");
                            player.sendMessage(data.getPrefix());
                        }
                    }

                    break;
                case 2:
                    break;
                case 3:
                    if(repository.existsGroup(args[1])) {
                        player.sendMessage(data.getPrefix() + " §cDiese Gruppe existiert bereits");
                    } else {
                        repository.createGroup(args[1], args[2]);
                        player.sendMessage(data.getPrefix() + "Die Gruppe wurde erstellt");
                    }
                    break;
            }

        } else {

        }
        return false;
    }
}
