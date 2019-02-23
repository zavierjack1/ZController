package android.zavierjack.zcontroller.database;

public class ZContollerDBSchema {
    public static final class ControllerConfigTable {
        public static final String NAME = "ControllerConfig";

        public static final class Cols {
            //primary key
            public static final String UUID = "uuid";
            public static final String NAME = "name";
            public static final String BACKGROUND_COLOR = "background_color";
            public static final String DESCRIPTION = "description";

            public static final String RED_BUTTON_URL = "red_button_url";
            public static final String RED_BUTTON_METHOD = "red_button_method";
            public static final String RED_BUTTON_POST_PARAMS = "red_button_post_params";
            public static final String RED_BUTTON_CONTENT_TYPE = "red_button_content_type";

            public static final String BLUE_BUTTON_URL = "blue_button_url";
            public static final String BLUE_BUTTON_METHOD = "blue_button_method";
            public static final String BLUE_BUTTON_POST_PARAMS = "blue_button_post_params";
            public static final String BLUE_BUTTON_CONTENT_TYPE = "blue_button_content_type";

            public static final String GREEN_BUTTON_URL = "green_button_url";
            public static final String GREEN_BUTTON_METHOD = "green_button_method";
            public static final String GREEN_BUTTON_POST_PARAMS = "green_button_post_params";
            public static final String GREEN_BUTTON_CONTENT_TYPE = "green_button_content_type";

            public static final String YELLOW_BUTTON_URL = "yellow_button_url";
            public static final String YELLOW_BUTTON_METHOD = "yellow_button_method";
            public static final String YELLOW_BUTTON_POST_PARAMS = "yellow_button_post_params";
            public static final String YELLOW_BUTTON_CONTENT_TYPE = "yellow_button_content_type";
        }
    }

    public static final class ControllerComponentTable {
        public static final String NAME = "components";

        public static final class Cols {
            //foreign key
            public static final String CONTROLLER_CONFIG_UUID = "controller_config_uuid";

            //primary key
            public static final String UUID = "uuid";

            //for now, should only be button
            public static final String TYPE_CD = "type_cd";

            public static final String POSITION = "position";
            public static final String BACKGROUND_COLOR = "background_color";

            //for now, should only be HTTP
            public static final String PROTOCOL = "protocol";
            public static final String HTTP_URL = "http_url";
            public static final String HTTP_PARAMS = "http_params";
            public static final String HTTP_CONTENT_TYPE = "content_type";
        }
    }
}
