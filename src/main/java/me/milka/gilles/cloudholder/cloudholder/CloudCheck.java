package me.milka.gilles.cloudholder.cloudholder;

public class CloudCheck {
    private final CloudHolder CloudHolder;

    public CloudCheck(me.milka.gilles.cloudholder.cloudholder.CloudHolder cloudHolder) {
        CloudHolder = cloudHolder;
    }

    public static class CheckResult {
        private final String NaamResult;
        boolean result;
        private final String ValueResult;
        public CheckResult(boolean result, String NaamResult, String ValueResult) {
            this.NaamResult = NaamResult;
            this.ValueResult = ValueResult;
            this.result = result;
        }
        public boolean getResult() {
            return result;
        }
        public String getNaamResult() {
            return NaamResult;
        }
        public String getValueResult() {
            return ValueResult;
        }
    }

    public static CheckResult CheckHolder(String naam, String value) {
        for (String blk : me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata) {
            String[] splitted = blk.split(":");
            String SplitNaam = splitted[0];
            String SplitValue = splitted[1];
            if (naam.equalsIgnoreCase(SplitNaam)) {
                return new CheckResult(true, SplitNaam, SplitValue);
            }
        }
        return new CheckResult(false, null, null);
    }
    public static class CheckVerwijder {
        private final String NaamResult;
        boolean result;
        private final String ValueResult;
        public CheckVerwijder(boolean result, String NaamResult, String ValueResult) {
            this.NaamResult = NaamResult;
            this.ValueResult = ValueResult;
            this.result = result;
        }
        public boolean getResult() {
            return result;
        }
        public String getNaamResult() {
            return NaamResult;
        }
        public String getValueResult() {
            return ValueResult;
        }
    }

    public static CheckVerwijder CheckHolder(String naam) {
        for (String blk : me.milka.gilles.cloudholder.cloudholder.CloudHolder.holderdata) {
            String[] splitted = blk.split(":");
            String SplitNaam = splitted[0];
            String SplitValue = splitted[1];
            if (naam.equalsIgnoreCase(SplitNaam)) {
                return new CheckVerwijder(true, SplitNaam, SplitValue);
            }
        }
        return new CheckVerwijder(false, null, null);
    }
}
