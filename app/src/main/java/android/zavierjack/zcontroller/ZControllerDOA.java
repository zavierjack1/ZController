package android.zavierjack.zcontroller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.zavierjack.zcontroller.database.ContollerDBSchema.ControllerConfigTable;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

        ControllerConfigJson controllerConfigJson = new ControllerConfigJson();
        JSONObject buttonsConfigJson  = new JSONObject();

        //Controller Config
        try{controllerConfigJson.put("name", controllerConfig.getName());} catch(JSONException e){Util.log(e.toString());}
        try{controllerConfigJson.put("description", controllerConfig.getDescription());} catch(JSONException e){Util.log(e.toString());}
        try{controllerConfigJson.put("background_color", controllerConfig.getBackgroundColor());} catch(JSONException e){Util.log(e.toString());}
        //try{controllerConfigJson.put("template", controllerConfig.getName());} catch(JSONException e){Util.log(e.toString());}

        //Button Config
        for (Map.Entry<String,ControllerButton> controllerButtonEntry: controllerConfig.getButtons().entrySet()){
            //overwrite buttonConfigJson with next button
            ButtonConfigJson buttonConfigJson = new ButtonConfigJson();
            try{buttonConfigJson.put("url", controllerButtonEntry.getValue().getUrl());} catch(JSONException e){Util.log(e.toString());}
            try{buttonConfigJson.put("method", controllerButtonEntry.getValue().getMethod());} catch(JSONException e){Util.log(e.toString());}
            try{buttonConfigJson.put("request_body", controllerButtonEntry.getValue().getRequestBody());} catch(JSONException e){Util.log(e.toString());}
            try{buttonConfigJson.put("content_type", controllerButtonEntry.getValue().getContentType());} catch(JSONException e){Util.log(e.toString());}

            //put the button config in the buttons JSON
            try{buttonsConfigJson.put(controllerButtonEntry.getValue().getName(), buttonConfigJson);} catch(JSONException e){Util.log(e.toString());}
        }

        //add buttonsjson to controllerjson
        try{controllerConfigJson.put("buttons", buttonsConfigJson);} catch(JSONException e){Util.log(e.toString());}

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
