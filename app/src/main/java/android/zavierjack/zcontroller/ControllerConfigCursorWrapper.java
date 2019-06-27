package android.zavierjack.zcontroller;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.zavierjack.zcontroller.database.ContollerDBSchema.ControllerConfigTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

public class ControllerConfigCursorWrapper extends CursorWrapper {

    public ControllerConfigCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ControllerConfig getControllerConfig() {
        String uuid = getString(getColumnIndex(ControllerConfigTable.Cols.UUID));
        String name = getString(getColumnIndex(ControllerConfigTable.Cols.NAME));
        String controller_config_json_tx = getString(getColumnIndex(ControllerConfigTable.Cols.CONFIG_JSON));
        HashMap<String, ControllerButton> controllerButtonsMap = new HashMap<>();

        ControllerConfigJson controllerConfigJson = new ControllerConfigJson(controller_config_json_tx);
        String background_color = controllerConfigJson.getBackgroundColor();
        String description = controllerConfigJson.getDescription();
        JSONObject buttons_json = controllerConfigJson.getButtons();

        try {
            Iterator<String> butons_json_keys = buttons_json.keys();

            //for each button in the buttons_json add a ControllerButton to the Array
            while(butons_json_keys.hasNext()) {
                String key = butons_json_keys.next();
                if (buttons_json.get(key) instanceof JSONObject) {
                    ControllerButtonJson button_json = new ControllerButtonJson((JSONObject) buttons_json.get(key));

                    controllerButtonsMap.put(key, new ControllerButton(
                            key,
                            button_json.getUrl(),
                            button_json.getMethod(),
                            button_json.getRequestBody(),
                            button_json.getContentType()
                    ));
                }
            }
        }
        catch (JSONException e){
            Util.log(e.toString());
        }

        ControllerConfig controllerConfig = new ControllerConfig(UUID.fromString(uuid));

        controllerConfig.setName((name));
        controllerConfig.setBackgroundColor(background_color);
        controllerConfig.setDescription(description);
        controllerConfig.setButtons(controllerButtonsMap);

        return controllerConfig;
    }
}
