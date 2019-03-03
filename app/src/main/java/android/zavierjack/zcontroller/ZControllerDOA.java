package android.zavierjack.zcontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.zavierjack.zcontroller.database.ZContollerDBSchema.ControllerConfigTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ZControllerDOA {
    private static ZControllerDOA sZControllerDOA;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    //ControllerDOA is a singleton
    public static ZControllerDOA get(Context context){
        if (sZControllerDOA == null){
            sZControllerDOA = new ZControllerDOA(context);
        }
        return sZControllerDOA;
    }

    //private because we want this to be a singleton class
    private ZControllerDOA(Context context){
        mContext = context.getApplicationContext();
        mDatabase = new ZControllerDOAHelper(mContext).getWritableDatabase();
    }

    public void addControllerConfig(ControllerConfig c){
        ContentValues values = getContentValues(c);
        mDatabase.insert(ControllerConfigTable.NAME, null, values);
    }

    public void deleteControllerConfig(UUID id){
        mDatabase.delete(
                ControllerConfigTable.NAME,
                ControllerConfigTable.Cols.UUID + " = ?",
                new String[] {id.toString()});
    }

    public void deleteControllerConfig(){
        mDatabase.delete(
                ControllerConfigTable.NAME,
                "",
                new String[] {});
    }

    public List<ControllerConfig> getControllerConfigs(){
        List<ControllerConfig> controllerConfigs = new ArrayList<>();

        ControllerConfigCursorWrapper cursor = queryControllerConfigs(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                controllerConfigs.add(cursor.getControllerConfig());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        return controllerConfigs;
    }

    public ControllerConfig getControllerConfig(UUID id){

        ControllerConfigCursorWrapper cursor = queryControllerConfigs(
                ControllerConfigTable.Cols.UUID + " = ?",
                new String[] { id.toString() });

        try {
            if (cursor.getCount() == 0){
                return null;
            }

            cursor.moveToFirst();
            return cursor.getControllerConfig();
        } finally {
            cursor.close();
        }
    }

    public void updateControllerConfig(ControllerConfig controllerConfig) {
        String uuidString = controllerConfig.getID().toString();
        ContentValues values = getContentValues(controllerConfig);

        mDatabase.update(
                ControllerConfigTable.NAME, values,
                ControllerConfigTable.Cols.UUID + " = ?",
                new String[]{uuidString}
        );
    }

    private static ContentValues getContentValues(ControllerConfig controllerConfig){
        ContentValues values = new ContentValues();
        values.put(ControllerConfigTable.Cols.UUID, controllerConfig.getID().toString());
        values.put(ControllerConfigTable.Cols.NAME, controllerConfig.getName());
        values.put(ControllerConfigTable.Cols.DESCRIPTION, controllerConfig.getDescription());
        values.put(ControllerConfigTable.Cols.BACKGROUND_COLOR, controllerConfig.getBackgroundColor());

        values.put(ControllerConfigTable.Cols.BUTTON_A_URL, controllerConfig.getButtonA().getUrl());
        values.put(ControllerConfigTable.Cols.BUTTON_A_METHOD, controllerConfig.getButtonA().getMethod());
        values.put(ControllerConfigTable.Cols.BUTTON_A_POST_PARAMS, controllerConfig.getButtonA().getRequestBody());
        values.put(ControllerConfigTable.Cols.BUTTON_A_CONTENT_TYPE, controllerConfig.getButtonA().getContentType());

        values.put(ControllerConfigTable.Cols.BUTTON_B_URL, controllerConfig.getButtonB().getUrl());
        values.put(ControllerConfigTable.Cols.BUTTON_B_METHOD, controllerConfig.getButtonB().getMethod());
        values.put(ControllerConfigTable.Cols.BUTTON_B_POST_PARAMS, controllerConfig.getButtonB().getRequestBody());
        values.put(ControllerConfigTable.Cols.BUTTON_B_CONTENT_TYPE, controllerConfig.getButtonB().getContentType());

        values.put(ControllerConfigTable.Cols.BUTTON_C_URL, controllerConfig.getButtonC().getUrl());
        values.put(ControllerConfigTable.Cols.BUTTON_C_METHOD, controllerConfig.getButtonC().getMethod());
        values.put(ControllerConfigTable.Cols.BUTTON_C_POST_PARAMS, controllerConfig.getButtonC().getRequestBody());
        values.put(ControllerConfigTable.Cols.BUTTON_C_CONTENT_TYPE, controllerConfig.getButtonC().getContentType());

        values.put(ControllerConfigTable.Cols.BUTTON_D_URL, controllerConfig.getButtonD().getUrl());
        values.put(ControllerConfigTable.Cols.BUTTON_D_METHOD, controllerConfig.getButtonD().getMethod());
        values.put(ControllerConfigTable.Cols.BUTTON_D_POST_PARAMS, controllerConfig.getButtonD().getRequestBody());
        values.put(ControllerConfigTable.Cols.BUTTON_D_CONTENT_TYPE, controllerConfig.getButtonD().getContentType());

        return values;
    }

    private ControllerConfigCursorWrapper queryControllerConfigs(String whereClause, String[] whereArgs){
        Cursor cursor = mDatabase.query(
                ControllerConfigTable.NAME,
                null, //
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new ControllerConfigCursorWrapper(cursor);
    }

}
