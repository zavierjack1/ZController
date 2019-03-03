package android.zavierjack.zcontroller;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class ControllerButton {
    public static final int BUTTON_COLOR_RED = 1;
    public static final int BUTTON_COLOR_BLUE = 2;
    public static final int BUTTON_COLOR_GREEN = 3;
    public static final int BUTTON_COLOR_YELLOW = 4;

    private String mUrl;
    private String mMethod;
    private String mRequestBody;
    private String mContentType;
    private int mColor;

    public ControllerButton(String url, String method, String requestBody, String contentType, int color) {
        mUrl = url;
        mMethod = method;
        mRequestBody = requestBody;
        mContentType = contentType;
        mColor = color;
    }

    public ControllerButton(String method, String requestBody, String contentType) {
        mMethod = method;
        mRequestBody = requestBody;
        mContentType = contentType;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }

    public String getMethod() {
        return mMethod;
    }

    public void setMethod(String method) {
        mMethod = method;
    }

    public int getMethodInt(){
        if (this.getMethod().equals("POST")){
            return Request.Method.POST;
        }
        else if (this.getMethod().equals("GET")){
            return Request.Method.GET;
        }
        else{
            // -1 is used by Request.java
            return -2;
        }
    }

    public String getRequestBody() {
        return mRequestBody;
    }

    public void setRequestBody(String requestBody) {
        mRequestBody = requestBody;
    }

    public String getContentType() {
        return mContentType;
    }

    public void setContentType(String contentType) {
        mContentType = contentType;
    }

    public int getColor() {
        return mColor;
    }

    public void setColor(int color) {
        mColor = color;
    }

    public View.OnClickListener getControllerConfigButtonOnClickListener(final Context context, final View v, final TextView mFeedBackMonitor){
        final String url = this.getUrl();
        final String requestBody = this.getRequestBody();
        final String contentType = this.getContentType();
        final int method = this.getMethodInt();

        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(context);
                //JSONObject myParams = new JSONObject();

                //Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(method, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                mFeedBackMonitor.append(System.getProperty("line.separator")+
                                        "Response is: " + response);
                                Log.d(Util.LOG_TAG, response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mFeedBackMonitor.append(System.getProperty("line.separator")+
                                        "error is: " + error);
                                Log.d(Util.LOG_TAG, error.toString());
                            }
                        }){
                    /*@Override
                    public String getBodyContentType() {
                        return "application/json; charset=utf-8";
                    }*/

                    @Override
                    public byte[] getBody() {
                        try {
                            return requestBody == null ? null : requestBody.getBytes("utf-8");
                        } catch (UnsupportedEncodingException uee) {
                            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                            return null;
                        }
                    }
                    @Override
                    public Map<String, String> getHeaders() {
                        Map<String, String> headers = new HashMap<>();
                        //headers.put("Content-Type", "application/json");
                        headers.put("Content-Type", contentType);
                        return headers;
                    }
                };

                queue.add(stringRequest);
            }
        };
    };
}