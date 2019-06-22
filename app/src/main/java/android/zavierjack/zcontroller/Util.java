package android.zavierjack.zcontroller;

import android.util.Log;

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
}
