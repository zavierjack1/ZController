package android.zavierjack.zcontroller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.zavierjack.zcontroller.database.ZContollerDBSchema.ControllerConfigTable;

public class ZControllerDOAHelper extends SQLiteOpenHelper{
    private static final int VERSION = 3;
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
                ControllerConfigTable.Cols.BUTTON_A_URL + ", "+
                ControllerConfigTable.Cols.BUTTON_A_METHOD + ", "+
                ControllerConfigTable.Cols.BUTTON_A_POST_PARAMS + ", "+
                ControllerConfigTable.Cols.BUTTON_A_CONTENT_TYPE + ", "+
                ControllerConfigTable.Cols.BUTTON_B_URL + ", "+
                ControllerConfigTable.Cols.BUTTON_B_METHOD + ", "+
                ControllerConfigTable.Cols.BUTTON_B_POST_PARAMS + ", "+
                ControllerConfigTable.Cols.BUTTON_B_CONTENT_TYPE + ", "+
                ControllerConfigTable.Cols.BUTTON_C_URL + ", "+
                ControllerConfigTable.Cols.BUTTON_C_METHOD + ", "+
                ControllerConfigTable.Cols.BUTTON_C_POST_PARAMS + ", "+
                ControllerConfigTable.Cols.BUTTON_C_CONTENT_TYPE + ", "+
                ControllerConfigTable.Cols.BUTTON_D_URL + ", "+
                ControllerConfigTable.Cols.BUTTON_D_METHOD + ", "+
                ControllerConfigTable.Cols.BUTTON_D_POST_PARAMS + ", "+
                ControllerConfigTable.Cols.BUTTON_D_CONTENT_TYPE +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        if (oldVersion < VERSION) {
            db.execSQL("DROP TABLE IF EXISTS " + ControllerConfigTable.NAME);
            onCreate(db);
        }
    }
}