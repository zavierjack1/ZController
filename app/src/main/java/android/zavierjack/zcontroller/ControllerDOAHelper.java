package android.zavierjack.zcontroller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.zavierjack.zcontroller.database.ContollerDBSchema.ControllerConfigTable;

public class ControllerDOAHelper extends SQLiteOpenHelper{
    private static final int VERSION = 4;
    private static String DATABASE_NAME = "SQLLite_ControllerDatabase.db";

    public ControllerDOAHelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("create table "+ControllerConfigTable.NAME+"("+
                " _id integer primary key autoincrement, " +
                ControllerConfigTable.Cols.UUID + ", " +
                ControllerConfigTable.Cols.NAME + ", " +
                ControllerConfigTable.Cols.CONFIG_JSON +
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