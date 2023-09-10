package me.milka.gilles.cloudholder.cloudholder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CreateCommand implements CommandExecutor {

    private final CloudHolder CloudHolder;

    public CreateCommand(me.milka.gilles.cloudholder.cloudholder.CloudHolder cloudHolder) {
        CloudHolder = cloudHolder;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length >= 1) {
                switch (args[0]) {
                    case "create":
                            String DataOpslaan = args[1] + ":" + args[2];
                            sender.sendMessage(args[1] + ":" + args[2]);

                            me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata.add(DataOpslaan);
                            CloudHolder.getCustomConfigDataFile().set("PlaceholdersData", me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata);
                            try {
                                CloudHolder.getCustomConfigDataFile().save(CloudHolder.customConfigDataFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata = CloudHolder.getCustomConfigDataFile().getStringList("PlaceholdersData");
                            break;

                }
            }


        }
        return false;
    }
}
