package android.zavierjack.zcontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.zavierjack.zcontroller.database.ContollerDBSchema.ControllerConfigTable;

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
        mDatabase = new ControllerDOAHelper(mContext).getWritableDatabase();
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

    public void deleteControllerConfigs(){
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

    public ControllerConfig getControllerConfig(String name){

        ControllerConfigCursorWrapper cursor = queryControllerConfigs(
                ControllerConfigTable.Cols.NAME + " = ?",
                new String[] { name.toString() });

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

        ControllerConfigJson controllerConfigJson = controllerConfig.getJson();

        values.put(ControllerConfigTable.Cols.UUID, controllerConfig.getID().toString());
        values.put(ControllerConfigTable.Cols.NAME, controllerConfig.getName());
        values.put(ControllerConfigTable.Cols.CONFIG_JSON, controllerConfigJson.toString());
        
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
