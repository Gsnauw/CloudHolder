package me.milka.gilles.cloudholder.cloudholder;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CloudCommand implements CommandExecutor, TabCompleter {
    private final CloudHolder CloudHolder;

    public CloudCommand(me.milka.gilles.cloudholder.cloudholder.CloudHolder cloudHolder) {
        CloudHolder = cloudHolder;
    }

    String Prefix = "&7[&cCloud&fHolder&7] &8-> ";
    String ConvertedPrefix = ChatColor.translateAlternateColorCodes('&', Prefix);
    String GeenPermissie = "&cJe hebt hier geen permissie voor!";
    String ConvertedPermissie = ChatColor.translateAlternateColorCodes('&', GeenPermissie);
    String CreatedBericht = "&fDe CloudHolder met naam &a%naam%&f is aangemaakt met waarde &a%value%&f.";
    String ConvertedCreated = ChatColor.translateAlternateColorCodes('&', CreatedBericht);
    String BestaatNietBericht = "&fDe CloudHolder met naam &a%naam%&f bestaat &cniet&f momenteel.";
    String ConvertedBestaatNiet = ChatColor.translateAlternateColorCodes('&', BestaatNietBericht);
    String BestaatAlBericht = "&fDe CloudHolder met naam &a%naam%&c bestaat al&f.";
    String ConvertedBestaatAl = ChatColor.translateAlternateColorCodes('&', BestaatAlBericht);
    String RenamedBericht = "&fDe CloudHolder met naam &a%naam%&f is gerenamed naar waarde &a%value%&f. Oude waarde: &a%oudevalue%&f.";
    String ConvertedRenamed = ChatColor.translateAlternateColorCodes('&', RenamedBericht);
    String VerwijderdBericht = "&fDe CloudHolder met naam &a%naam%&f is &cverwijderd&f.";
    String ConvertedVerwijderd = ChatColor.translateAlternateColorCodes('&', VerwijderdBericht);


    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ConvertedPrefix + ChatColor.RED + "Ongeldig command, gebruik: '/cloudholder <create, delete, rename>'.");
        }

        if (args.length == 1) {
                if (args[0].equals("delete")) {
                    if (sender.hasPermission("cloudholder.delete")) {
                    sender.sendMessage(ConvertedPrefix + ChatColor.RED + "Vul een naam in! (/cloudholder delete <naam>)");
                    return false;
                }
                sender.sendMessage(ConvertedPrefix + ConvertedPermissie);
                return false;
            }
                if (args[0].equals("create")) {
                    if (sender.hasPermission("cloudholder.create")) {
                    sender.sendMessage(ConvertedPrefix + ChatColor.RED + "Vul een naam in! (/cloudholder create <naam> <waarde>)");
                    return false;
                }
                sender.sendMessage(ConvertedPrefix + ConvertedPermissie);
                return false;
            }
                if (args[0].equals("rename")) {
                    if (sender.hasPermission("cloudholder.rename")) {
                    sender.sendMessage(ConvertedPrefix + ChatColor.RED + "Vul een naam in! (/cloudholder rename <naam> <nieuwe waarde>)");
                    return false;
                }
                sender.sendMessage(ConvertedPrefix + ConvertedPermissie);
                return false;
            }
        }

        if (args.length == 2) {
                if (args[0].equals("create")) {
                    if (sender.hasPermission("cloudholder.create")) {
                    sender.sendMessage(ConvertedPrefix + ChatColor.RED + "Vul een waarde in! (/cloudholder create <naam> <waarde>)");
                    return false;
                }
                sender.sendMessage(ConvertedPrefix + ConvertedPermissie);
                return false;
            }
                if (args[0].equals("rename")) {
                    if (sender.hasPermission("cloudholder.rename")) {
                    sender.sendMessage(ConvertedPrefix + ChatColor.RED + "Vul een waarde in! (/cloudholder rename <naam> <nieuwe waarde>)");
                    return false;
                }
                sender.sendMessage(ConvertedPrefix + ConvertedPermissie);
                return false;
            }
        }

        if (args.length > 3) {
            if (args[0].equals("create")) {
                if (sender.hasPermission("cloudholder.create")) {
                    sender.sendMessage(ConvertedPrefix + ChatColor.RED + "Teveel argumenten!");
                    return false;
                }
                sender.sendMessage(ConvertedPrefix + ConvertedPermissie);
                return false;
            }
            if (args[0].equals("rename")) {
                if (sender.hasPermission("cloudholder.rename")) {
                    sender.sendMessage(ConvertedPrefix + ChatColor.RED + "Teveel argumenten!");
                    return false;
                }
                sender.sendMessage(ConvertedPrefix + ConvertedPermissie);
                return false;
            }
        }

        if (args.length > 2) {
            if (args[0].equals("delete")) {
                if (sender.hasPermission("cloudholder.delete")) {
                    sender.sendMessage(ConvertedPrefix + ChatColor.RED + "Teveel argumenten!");
                    return false;
                }
                sender.sendMessage(ConvertedPrefix + ConvertedPermissie);
                return false;
            }
        }

        if (args.length == 3) {
            CloudCheck.CheckResult result = CloudCheck.CheckHolder(args[1], args[2]);
            boolean check = result.getResult();
            // String naamResult = result.getNaamResult();
            String valueResult = result.getValueResult();

            ConvertedBestaatAl = ConvertedBestaatAl.replace("%naam%", args[1]);
            ConvertedBestaatNiet = ConvertedBestaatNiet.replace("%naam%", args[1]);

            switch (args[0]) {
                case "create":
                    if (sender.hasPermission("cloudholder.create")) {
                        if (!check) {
                            String DataOpslaan = args[1] + ":" + args[2];
                            ConvertedCreated = ConvertedCreated.replace("%naam%", args[1]);
                            ConvertedCreated = ConvertedCreated.replace("%value%", args[2]);
                            sender.sendMessage(ConvertedPrefix + ConvertedCreated);
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
                        if (check) {
                            sender.sendMessage(ConvertedPrefix + ConvertedBestaatAl);
                            break;
                        }
                    }
                    sender.sendMessage(ConvertedPrefix + ConvertedPermissie);
                    break;

                case "rename":
                    if (sender.hasPermission("cloudholder.rename")) {
                        if (check) {
                            String DataVerwijder = args[1] + ":" + valueResult;
                            me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata.remove(DataVerwijder);
                            String DataVeranderen = args[1] + ":" + args[2];
                            me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata.add(DataVeranderen);
                            ConvertedRenamed = ConvertedRenamed.replace("%naam%", args[1]);
                            ConvertedRenamed = ConvertedRenamed.replace("%value%", args[2]);
                            ConvertedRenamed = ConvertedRenamed.replace("%oudevalue%", valueResult);
                            sender.sendMessage(ConvertedPrefix + ConvertedRenamed);
                            CloudHolder.getCustomConfigDataFile().set("PlaceholdersData", me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata);
                            try {
                                CloudHolder.getCustomConfigDataFile().save(CloudHolder.customConfigDataFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata = CloudHolder.getCustomConfigDataFile().getStringList("PlaceholdersData");
                            break;
                        }

                        if (!check) {
                            sender.sendMessage(ConvertedPrefix + ConvertedBestaatNiet);
                            break;
                        }
                    }
                    sender.sendMessage(ConvertedPrefix + ConvertedPermissie);
                    break;
            }
        }

        if (args.length == 2) {
            switch (args[0]) {
                case "delete":
                    if (sender.hasPermission("cloudholder.delete")) {
                        CloudCheck.CheckVerwijder result = CloudCheck.CheckHolder(args[1]);
                        boolean check = result.getResult();
                        // String naamResult = result.getNaamResult();
                        String valueResult = result.getValueResult();

                        if (check) {
                            String DataVerwijderen = args[1] + ":" + valueResult;
                            me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata.remove(DataVerwijderen);
                            ConvertedVerwijderd = ConvertedVerwijderd.replace("%naam%", args[1]);
                            sender.sendMessage(ConvertedPrefix + ConvertedVerwijderd);
                            CloudHolder.getCustomConfigDataFile().set("PlaceholdersData", me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata);
                            try {
                                CloudHolder.getCustomConfigDataFile().save(CloudHolder.customConfigDataFile);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata = CloudHolder.getCustomConfigDataFile().getStringList("PlaceholdersData");
                            break;
                        }

                        if (!check) {
                            sender.sendMessage(ConvertedPrefix + ConvertedBestaatNiet);
                            break;
                        }
                    }
                    sender.sendMessage(ConvertedPrefix + ConvertedPermissie);
                    break;
            }
        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete (@NotNull CommandSender commandSender, @NotNull Command
            command, @NotNull String s, @NotNull String[]args){
        List<String> suggestions = new ArrayList<>();
        if (args.length == 1) {
            return Arrays.asList("create", "delete", "rename").stream().filter(p -> p.regionMatches(true, 0, args[0], 0, args[0].length())).collect(Collectors.toList());
        }
        if (args.length == 2) {
            String subcommand = args[0].toLowerCase();
            for (String blk : me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata) {
                String[] splitted = blk.split(":");
                String splitNaam = splitted[0];

                if ((subcommand.equals("rename") || subcommand.equals("delete")) && splitNaam.regionMatches(true, 0, args[1], 0, args[1].length())) {
                    suggestions.add(splitNaam);
                }
            }
        }
        return suggestions;
    }
}