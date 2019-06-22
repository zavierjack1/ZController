package android.zavierjack.zcontroller.database;

public class ContollerDBSchema {
    public static final class ControllerConfigTable {
        public static final String NAME = "ControllerConfig";

        public static final class Cols {
            //primary key
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String CONFIG_JSON = "config_json";
        }
    }
}
