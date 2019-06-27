package android.zavierjack.zcontroller;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ControllerConfig {
    private UUID mID;
    private String mName;
    private String mBackgroundColor;
    private String mDescription;

    private HashMap<String, ControllerButton> mButtons;

    public ControllerConfig(){
        this(UUID.randomUUID());
    }

    public ControllerConfig(UUID id){
        mID = id;
        mButtons = new HashMap<>();
        mButtons.put("A", new ControllerButton("A"));
        mButtons.put("B", new ControllerButton("B"));
        mButtons.put("C", new ControllerButton("C"));
        mButtons.put("D", new ControllerButton("D"));
    }

    public ControllerConfig(ControllerConfigJson controllerConfigJson){
        this(UUID.randomUUID());

        mName = controllerConfigJson.getName();
        mBackgroundColor = controllerConfigJson.getBackgroundColor();
        mDescription = controllerConfigJson.getDescription();

        mButtons = new HashMap<>();
        mButtons.put("A", null);
        mButtons.put("B", null);
        mButtons.put("C", null);
        mButtons.put("D", null);

        //populate values of mButtons with ControllerButton build from JSON
        for (Map.Entry<String, ControllerButton> buttonsEntry: mButtons.entrySet()){
            try{
                buttonsEntry.setValue(new ControllerButton(
                        new ControllerButtonJson((JSONObject)controllerConfigJson.getButtons().get(buttonsEntry.getKey()))
                ));
            }
            catch (JSONException e){
                Util.log(e.toString());
            }
        }
    }

    public UUID getID() {
        return mID;
    }

    public void setID(UUID ID) {
        mID = ID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        mBackgroundColor = backgroundColor;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public HashMap<String, ControllerButton> getButtons() {
        return mButtons;
    }

    public void setButtons(HashMap<String, ControllerButton> buttons) {
        mButtons = buttons;
    }

    public ControllerConfigJson getJson(){
        ControllerConfigJson controllerConfigJson = new ControllerConfigJson();
        JSONObject buttonsConfigJson  = new JSONObject();

        //Controller Config
        try{controllerConfigJson.put("name", this.getName());} catch(JSONException e){Util.log(e.toString());}
        try{controllerConfigJson.put("description", this.getDescription());} catch(JSONException e){Util.log(e.toString());}
        try{controllerConfigJson.put("background_color", this.getBackgroundColor());} catch(JSONException e){Util.log(e.toString());}
        //try{controllerConfigJson.put("template", controllerConfig.getName());} catch(JSONException e){Util.log(e.toString());}

        //Button Config
        for (Map.Entry<String,ControllerButton> controllerButtonEntry: this.getButtons().entrySet()){
            //overwrite buttonConfigJson with next button
            ControllerButtonJson buttonConfigJson = new ControllerButtonJson();
            try{buttonConfigJson.put("name", controllerButtonEntry.getValue().getName());} catch(JSONException e){Util.log(e.toString());}
            try{buttonConfigJson.put("url", controllerButtonEntry.getValue().getUrl());} catch(JSONException e){Util.log(e.toString());}
            try{buttonConfigJson.put("method", controllerButtonEntry.getValue().getMethod());} catch(JSONException e){Util.log(e.toString());}
            try{buttonConfigJson.put("request_body", controllerButtonEntry.getValue().getRequestBody());} catch(JSONException e){Util.log(e.toString());}
            try{buttonConfigJson.put("content_type", controllerButtonEntry.getValue().getContentType());} catch(JSONException e){Util.log(e.toString());}

            //put the button config in the buttons JSON
            try{buttonsConfigJson.put(controllerButtonEntry.getValue().getName(), buttonConfigJson);} catch(JSONException e){Util.log(e.toString());}
        }

        //add buttonsjson to controllerjson
        try{controllerConfigJson.put("buttons", buttonsConfigJson);} catch(JSONException e){Util.log(e.toString());}

        return controllerConfigJson;
    }

    @Override
    public String toString() {
        return "ControllerConfig{" +
                "mID=" + mID +
                ", mName='" + mName + '\'' +
                ", mBackgroundColor='" + mBackgroundColor + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", mButtons=" + mButtons +
                '}';
    }
}
