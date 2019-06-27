package android.zavierjack.zcontroller;

import org.json.JSONException;
import org.json.JSONObject;

public class ControllerButtonJson extends JSONObject{
    JSONObject config;

    public ControllerButtonJson() {
        this.config = new JSONObject();
    }

    public ControllerButtonJson(JSONObject config) {
        this.config = config;
    }

    public String getName(){
        String name ="";
        try {
            name = config.getString("name");
            return name;
        }
        catch (JSONException e) {
            return name;
        }
    }

    public String getUrl(){
        String url ="";
        try {
            url = config.getString("url");
            return url;
        }
        catch (JSONException e) {
            return url;
        }
    }

    public String getMethod(){
        String method ="";
        try {
            method = config.getString("method");
            return method;
        }
        catch (JSONException e) {
            return method;
        }
    }

    public String getRequestBody(){
        String requestBody ="";
        try {
            requestBody = config.getString("requestBody");
            return requestBody;
        }
        catch (JSONException e) {
            return requestBody;
        }
    }

    public String getContentType(){
        String contentType ="";
        try {
            contentType = config.getString("contentType");
            return contentType;
        }
        catch (JSONException e) {
            return contentType;
        }
    }

    public int getColor(){
        int color = 0;
        try {
            color = config.getInt("color");
            return color;
        }
        catch (JSONException e) {
            return color;
        }
    }
}

