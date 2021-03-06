package android.zavierjack.zcontroller;

import org.json.JSONException;
import org.json.JSONObject;

public class ControllerConfigJson extends JSONObject{
    JSONObject config;

    public ControllerConfigJson() {
        this.config = new JSONObject();
    }

    public ControllerConfigJson(JSONObject config) {
        this.config = config;
    }

    public ControllerConfigJson(String config) {
        try{
            this.config = new JSONObject(config);
        }
        catch (JSONException e){
            Util.log(e.toString());
        }
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

    public String getBackgroundColor(){
        String background_color ="";
        try {
            background_color = config.getString("background_color");
            return background_color;
        }
        catch (JSONException e) {
            return background_color;
        }
    }

    public String getDescription(){
        String description ="";
        try {
            description = config.getString("description");
            return description;
        }
        catch (JSONException e){
            return description;
        }
    }

    public JSONObject getButtons(){
        JSONObject buttons = new JSONObject();
        try{
            buttons = config.getJSONObject("buttons");
            return buttons;
        }
        catch (JSONException e){
            return buttons;
        }
    }
}


