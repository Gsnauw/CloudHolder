package me.milka.gilles.cloudholder.cloudholder;

public class CloudCheck {
    private final CloudHolder CloudHolder;

    public CloudCheck(me.milka.gilles.cloudholder.cloudholder.CloudHolder cloudHolder) {
        CloudHolder = cloudHolder;
    }
    public static boolean CheckHolder(String naam, String value) {
        for (String blk : me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata) {
            String[] splitted = blk.split(":");
            String SplitNaam = splitted[0];
            String SplitValue = splitted[1];
            if (naam.equalsIgnoreCase(SplitNaam) && value.equalsIgnoreCase(SplitValue))) {
                return true;
            }
        }
}
