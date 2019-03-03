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

        String buttonAURL = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_A_URL));
        String buttonAMethod = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_A_METHOD));
        String buttonARequestBody = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_A_POST_PARAMS));
        String buttonAContentType = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_A_CONTENT_TYPE));

        String buttonBURL = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_B_URL));
        String buttonBMethod = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_B_METHOD));
        String buttonBRequestBody = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_B_POST_PARAMS));
        String buttonBContentType = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_B_CONTENT_TYPE));

        String buttonCURL = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_C_URL));
        String buttonCMethod = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_C_METHOD));
        String buttonCRequestBody = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_C_POST_PARAMS));
        String buttonCContentType = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_C_CONTENT_TYPE));

        String buttonDURL = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_D_URL));
        String buttonDMethod = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_D_METHOD));
        String buttonDRequestBody = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_D_POST_PARAMS));
        String buttonDContentType = getString(getColumnIndex(ControllerConfigTable.Cols.BUTTON_D_CONTENT_TYPE));

        ControllerConfig controllerConfig = new ControllerConfig(UUID.fromString(uuidString));

        controllerConfig.setName((name));
        controllerConfig.setBackgroundColor(backgroundColor);
        controllerConfig.setDescription(description);
        
        controllerConfig.setButtonA(new ControllerButton(buttonAURL, buttonAMethod, buttonARequestBody, buttonAContentType));
        controllerConfig.setButtonB(new ControllerButton(buttonBURL, buttonBMethod, buttonBRequestBody, buttonBContentType));
        controllerConfig.setButtonC(new ControllerButton(buttonCURL, buttonCMethod, buttonCRequestBody, buttonCContentType));
        controllerConfig.setButtonD(new ControllerButton(buttonDURL, buttonDMethod, buttonDRequestBody, buttonDContentType));

        return controllerConfig;
    }
}
