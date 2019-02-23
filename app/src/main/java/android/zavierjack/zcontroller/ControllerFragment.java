package android.zavierjack.zcontroller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ControllerFragment extends Fragment {

    public static final String EXTRA_CONTROLLER_CONFIG_ID_KEY = "com.android.controllerActivity.ControllerConfigID";

    private ControllerConfig mControllerConfig;

    public static ControllerFragment newInstance(UUID controllerConfigID){
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_CONTROLLER_CONFIG_ID_KEY, controllerConfigID);

        ControllerFragment fragment = new ControllerFragment();
        fragment.setArguments(args);
        
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID mControllerConfigID = (UUID) getArguments().getSerializable(EXTRA_CONTROLLER_CONFIG_ID_KEY);
        mControllerConfig = ZControllerDOA.get(getActivity()).getControllerConfig(mControllerConfigID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_controller, container, false);
        final TextView mFeedBackMonitor = v.findViewById(R.id.feedback_monitor);

        mFeedBackMonitor.setMovementMethod(new ScrollingMovementMethod());

        final Button mRedButton = v.findViewById(R.id.red_button);
        mRedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String url = mControllerConfig.getRedButtonUrl();
                JSONObject myParams = new JSONObject();

                
                Log.d(Util.LOG_TAG, "RedButtonURL: "+mControllerConfig.getRedButtonUrl());
                Log.d(Util.LOG_TAG, "RedButtonPostParams: "+mControllerConfig.getRedButtonPostParams());
                Log.d(Util.LOG_TAG, "RedButtonContentType: "+mControllerConfig.getRedButtonContentType());
                Log.d(Util.LOG_TAG, "RedButtonMethod: "+mControllerConfig.getRedButtonMethod());

                Log.d(Util.LOG_TAG, "Log for gitlab2"); 
 

                final String requestBody = mControllerConfig.getRedButtonPostParams();
                Log.d(Util.LOG_TAG, requestBody);

                //Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
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
                        headers.put("Content-Type", mControllerConfig.getRedButtonContentType());
                        return headers;
                    }
                };

                queue.add(stringRequest);
            }
        });

        Button mBlueButton = v.findViewById(R.id.blue_button);

        mBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Instantiate the RequestQueue.
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String url = "http://www.google.com";

                //Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Display the first 500 characters of the repsonse string.
                                mFeedBackMonitor.append(System.getProperty("line.separator")+
                                        "Response is: " + response.substring(0, 500));
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mFeedBackMonitor.append(System.getProperty("line.separator")+
                                        error.toString());
                            }
                        });
                queue.add(stringRequest);
            }
        });

        return v;
    }
}
