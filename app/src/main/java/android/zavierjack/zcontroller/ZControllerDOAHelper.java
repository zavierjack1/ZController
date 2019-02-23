package android.zavierjack.zcontroller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.zavierjack.zcontroller.database.ZContollerDBSchema.ControllerConfigTable;

public class ZControllerDOAHelper extends SQLiteOpenHelper{
    private static final int VERSION = 2;
    private static String DATABASE_NAME = "zControllerDatabase.db";

    public ZControllerDOAHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+ControllerConfigTable.NAME+"("+
                " _id integer primary key autoincrement, " +
                ControllerConfigTable.Cols.UUID + ", " +
                ControllerConfigTable.Cols.NAME + ", " +
                ControllerConfigTable.Cols.BACKGROUND_COLOR + ", "+
                ControllerConfigTable.Cols.DESCRIPTION + ", "+
                ControllerConfigTable.Cols.RED_BUTTON_URL + ", "+
                ControllerConfigTable.Cols.RED_BUTTON_METHOD + ", "+
                ControllerConfigTable.Cols.RED_BUTTON_POST_PARAMS + ", "+
                ControllerConfigTable.Cols.RED_BUTTON_CONTENT_TYPE + ", "+
                ControllerConfigTable.Cols.BLUE_BUTTON_URL + ", "+
                ControllerConfigTable.Cols.BLUE_BUTTON_METHOD + ", "+
                ControllerConfigTable.Cols.BLUE_BUTTON_POST_PARAMS + ", "+
                ControllerConfigTable.Cols.BLUE_BUTTON_CONTENT_TYPE + ", "+
                ControllerConfigTable.Cols.GREEN_BUTTON_URL + ", "+
                ControllerConfigTable.Cols.GREEN_BUTTON_METHOD + ", "+
                ControllerConfigTable.Cols.GREEN_BUTTON_POST_PARAMS + ", "+
                ControllerConfigTable.Cols.GREEN_BUTTON_CONTENT_TYPE + ", "+
                ControllerConfigTable.Cols.YELLOW_BUTTON_URL + ", "+
                ControllerConfigTable.Cols.YELLOW_BUTTON_METHOD + ", "+
                ControllerConfigTable.Cols.YELLOW_BUTTON_POST_PARAMS + ", "+
                ControllerConfigTable.Cols.YELLOW_BUTTON_CONTENT_TYPE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion < 2) {
            db.execSQL("DROP TABLE IF EXISTS " + ControllerConfigTable.NAME);
            onCreate(db);
        }
    }
}