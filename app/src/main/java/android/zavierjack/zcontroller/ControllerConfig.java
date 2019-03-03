package android.zavierjack.zcontroller;

import java.util.UUID;

public class ControllerConfig {
    private UUID mID;
    private String mName;
    private String mBackgroundColor;
    private String mDescription;

    private ControllerButton mButtonA;
    private ControllerButton mButtonB;
    private ControllerButton mButtonC;
    private ControllerButton mButtonD;

    public ControllerConfig(){
        this(UUID.randomUUID());
    }

    public ControllerConfig(UUID id){
        mID = id;
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
    public ControllerButton getButtonA() {
        return mButtonA;
    }

    public void setButtonA(ControllerButton buttonA) {
        mButtonA = buttonA;
    }

    public ControllerButton getButtonB() {
        return mButtonB;
    }

    public void setButtonB(ControllerButton buttonB) {
        mButtonB = buttonB;
    }

    public ControllerButton getButtonC() {
        return mButtonC;
    }

    public void setButtonC(ControllerButton buttonC) {
        mButtonC = buttonC;
    }

    public ControllerButton getButtonD() {
        return mButtonD;
    }

    public void setButtonD(ControllerButton buttonD) {
        mButtonD = buttonD;
    }
}
