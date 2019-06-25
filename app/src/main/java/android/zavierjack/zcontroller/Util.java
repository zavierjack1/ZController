package android.zavierjack.zcontroller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class Util {
    private static final String LOG_TAG = "ZCONTROLLER_LOG";
    private static final String ASSUMED_CURRENT_DIR = "android.zavierjack.zcontroller.";

    public static String getMethodName(final int depth)
    {
        final StackTraceElement[] steArray = Thread.currentThread().getStackTrace();
        StackTraceElement ste = steArray[depth];
        return "<"+ste.getClassName().replace(ASSUMED_CURRENT_DIR, "")+"."+ste.getMethodName()+"> line "+ste.getLineNumber();
    }
    public static void log(String message){
        Log.d(LOG_TAG, getMethodName(4)+": "+message);
    }

    public static void showShortToast(Context context, CharSequence text){
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


}
