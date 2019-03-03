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

            public static final String BUTTON_A_URL = "button_a_url";
            public static final String BUTTON_A_METHOD = "button_a_method";
            public static final String BUTTON_A_POST_PARAMS = "button_a_post_params";
            public static final String BUTTON_A_CONTENT_TYPE = "button_a_content_type";

            public static final String BUTTON_B_URL = "button_b_url";
            public static final String BUTTON_B_METHOD = "button_b_method";
            public static final String BUTTON_B_POST_PARAMS = "button_b_post_params";
            public static final String BUTTON_B_CONTENT_TYPE = "button_b_content_type";

            public static final String BUTTON_C_URL = "button_c_url";
            public static final String BUTTON_C_METHOD = "button_c_method";
            public static final String BUTTON_C_POST_PARAMS = "button_c_post_params";
            public static final String BUTTON_C_CONTENT_TYPE = "button_c_content_type";

            public static final String BUTTON_D_URL = "button_d_url";
            public static final String BUTTON_D_METHOD = "button_d_method";
            public static final String BUTTON_D_POST_PARAMS = "button_d_post_params";
            public static final String BUTTON_D_CONTENT_TYPE = "button_d_content_type";
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
