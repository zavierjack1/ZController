package android.zavierjack.zcontroller;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.zavierjack.zcontroller.database.ZContollerDBSchema.ControllerConfigTable;

import java.util.UUID;

public class ControllerConfigCursorWrapper extends CursorWrapper {

    public ControllerConfigCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ControllerConfig getControllerConfig() {
        String uuidString = getString(getColumnIndex(ControllerConfigTable.Cols.UUID));
        String name = getString(getColumnIndex(ControllerConfigTable.Cols.NAME));
        String backgroundColor = getString(getColumnIndex(ControllerConfigTable.Cols.BACKGROUND_COLOR));
        String description = getString(getColumnIndex(ControllerConfigTable.Cols.DESCRIPTION));

        String redButtonURL = getString(getColumnIndex(ControllerConfigTable.Cols.RED_BUTTON_URL));
        String redButtonMethod = getString(getColumnIndex(ControllerConfigTable.Cols.RED_BUTTON_METHOD));
        String redButtonRequestBody = getString(getColumnIndex(ControllerConfigTable.Cols.RED_BUTTON_POST_PARAMS));
        String redButtonContentType = getString(getColumnIndex(ControllerConfigTable.Cols.RED_BUTTON_CONTENT_TYPE));

        String blueButtonURL = getString(getColumnIndex(ControllerConfigTable.Cols.BLUE_BUTTON_URL));
        String blueButtonMethod = getString(getColumnIndex(ControllerConfigTable.Cols.BLUE_BUTTON_METHOD));
        String blueButtonRequestBody = getString(getColumnIndex(ControllerConfigTable.Cols.BLUE_BUTTON_POST_PARAMS));
        String blueButtonContentType = getString(getColumnIndex(ControllerConfigTable.Cols.BLUE_BUTTON_CONTENT_TYPE));

        String greenButtonURL = getString(getColumnIndex(ControllerConfigTable.Cols.GREEN_BUTTON_URL));
        String greenButtonMethod = getString(getColumnIndex(ControllerConfigTable.Cols.GREEN_BUTTON_METHOD));
        String greenButtonRequestBody = getString(getColumnIndex(ControllerConfigTable.Cols.GREEN_BUTTON_POST_PARAMS));
        String greenButtonContentType = getString(getColumnIndex(ControllerConfigTable.Cols.GREEN_BUTTON_CONTENT_TYPE));

        String yellowButtonURL = getString(getColumnIndex(ControllerConfigTable.Cols.YELLOW_BUTTON_URL));
        String yellowButtonMethod = getString(getColumnIndex(ControllerConfigTable.Cols.YELLOW_BUTTON_METHOD));
        String yellowButtonRequestBody = getString(getColumnIndex(ControllerConfigTable.Cols.YELLOW_BUTTON_POST_PARAMS));
        String yellowButtonContentType = getString(getColumnIndex(ControllerConfigTable.Cols.YELLOW_BUTTON_CONTENT_TYPE));

        ControllerConfig controllerConfig = new ControllerConfig(UUID.fromString(uuidString));

        controllerConfig.setName((name));
        controllerConfig.setBackgroundColor(backgroundColor);
        controllerConfig.setDescription(description);
        
        controllerConfig.setRedButtonUrl(redButtonURL);
        controllerConfig.setRedButtonMethod(redButtonMethod);
        controllerConfig.setRedButtonRequestBody(redButtonRequestBody);
        controllerConfig.setRedButtonContentType(redButtonContentType);

        controllerConfig.setBlueButtonUrl(blueButtonURL);
        controllerConfig.setBlueButtonMethod(blueButtonMethod);
        controllerConfig.setBlueButtonRequestBody(blueButtonRequestBody);
        controllerConfig.setBlueButtonContentType(blueButtonContentType);

        controllerConfig.setGreenButtonUrl(greenButtonURL);
        controllerConfig.setGreenButtonMethod(greenButtonMethod);
        controllerConfig.setGreenButtonRequestBody(greenButtonRequestBody);
        controllerConfig.setGreenButtonContentType(greenButtonContentType);

        controllerConfig.setYellowButtonUrl(yellowButtonURL);
        controllerConfig.setYellowButtonMethod(yellowButtonMethod);
        controllerConfig.setYellowButtonRequestBody(yellowButtonRequestBody);
        controllerConfig.setYellowButtonContentType(yellowButtonContentType);

        return controllerConfig;
    }
}
