package me.milka.gilles.cloudholder.cloudholder;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class CloudCommand implements CommandExecutor {

    private final CloudHolder CloudHolder;

    public CloudCommand(me.milka.gilles.cloudholder.cloudholder.CloudHolder cloudHolder) {
        CloudHolder = cloudHolder;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (args.length >= 1) {
                switch (args[0]) {
                    case "create":
                        String naam = args[1];
                        String value = args[2];
                        String DataOpslaan = naam + ":" + value;

                        if (CloudCheck.CheckHolder(naam, value)) {
                            sender.sendMessage(naam + ":" + value);
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
                        if (!CloudCheck.CheckHolder(naam, value)) {
                            sender.sendMessage(ChatColor.RED + "false");
                            break;
                        }
                case "rename":
                    String naam = args[1];
                    String value = args[2];
                    String DataOpslaan = naam + ":" + value;

                    if (CloudCheck.CheckHolder(naam, value)) {
                        sender.sendMessage(naam + ":" + value);
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
                    if (!CloudCheck.CheckHolder(naam, value)) {
                        sender.sendMessage(ChatColor.RED + "false");
                        break;
                    }
            }
            }
        return false;
    }
}
