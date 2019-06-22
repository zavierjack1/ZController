package android.zavierjack.zcontroller;

import java.util.HashMap;
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
