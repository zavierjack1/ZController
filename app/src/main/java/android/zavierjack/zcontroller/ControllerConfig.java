package android.zavierjack.zcontroller;

import java.util.UUID;

public class ControllerConfig {
    private UUID mID;
    private String mName;
    private String mBackgroundColor;
    private String mDescription;

    private String mRedButtonUrl;
    private String mRedButtonMethod;
    private String mRedButtonRequestBody;
    private String mRedButtonContentType;

    private String mBlueButtonUrl;
    private String mBlueButtonMethod;
    private String mBlueButtonRequestBody;
    private String mBlueButtonContentType;

    private String mGreenButtonUrl;
    private String mGreenButtonMethod;
    private String mGreenButtonRequestBody;
    private String mGreenButtonContentType;

    private String mYellowButtonUrl;
    private String mYellowButtonMethod;
    private String mYellowButtonRequestBody;
    private String mYellowButtonContentType;

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

    public String getRedButtonUrl() {
        return mRedButtonUrl;
    }

    public void setRedButtonUrl(String redButtonUrl) {
        mRedButtonUrl = redButtonUrl;
    }

    public String getRedButtonMethod() {
        return mRedButtonMethod;
    }

    public void setRedButtonMethod(String redButtonMethod) {
        mRedButtonMethod = redButtonMethod;
    }

    public String getRedButtonRequestBody() {
        return mRedButtonRequestBody;
    }

    public void setRedButtonRequestBody(String redButtonRequestBody) {
        mRedButtonRequestBody = redButtonRequestBody;
    }

    public String getRedButtonContentType() {
        return mRedButtonContentType;
    }

    public void setRedButtonContentType(String redButtonContentType) {
        mRedButtonContentType = redButtonContentType;
    }

    public String getBlueButtonUrl() {
        return mBlueButtonUrl;
    }

    public void setBlueButtonUrl(String blueButtonUrl) {
        mBlueButtonUrl = blueButtonUrl;
    }

    public String getBlueButtonMethod() {
        return mBlueButtonMethod;
    }

    public void setBlueButtonMethod(String blueButtonMethod) {
        mBlueButtonMethod = blueButtonMethod;
    }

    public String getBlueButtonRequestBody() {
        return mBlueButtonRequestBody;
    }

    public void setBlueButtonRequestBody(String blueButtonRequestBody) {
        mBlueButtonRequestBody = blueButtonRequestBody;
    }

    public String getBlueButtonContentType() {
        return mBlueButtonContentType;
    }

    public void setBlueButtonContentType(String blueButtonContentType) {
        mBlueButtonContentType = blueButtonContentType;
    }

    public String getGreenButtonUrl() {
        return mGreenButtonUrl;
    }

    public void setGreenButtonUrl(String greenButtonUrl) {
        mGreenButtonUrl = greenButtonUrl;
    }

    public String getGreenButtonMethod() {
        return mGreenButtonMethod;
    }

    public void setGreenButtonMethod(String greenButtonMethod) {
        mGreenButtonMethod = greenButtonMethod;
    }

    public String getGreenButtonRequestBody() {
        return mGreenButtonRequestBody;
    }

    public void setGreenButtonRequestBody(String greenButtonRequestBody) {
        mGreenButtonRequestBody = greenButtonRequestBody;
    }

    public String getGreenButtonContentType() {
        return mGreenButtonContentType;
    }

    public void setGreenButtonContentType(String greenButtonContentType) {
        mGreenButtonContentType = greenButtonContentType;
    }

    public String getYellowButtonUrl() {
        return mYellowButtonUrl;
    }

    public void setYellowButtonUrl(String yellowButtonUrl) {
        mYellowButtonUrl = yellowButtonUrl;
    }

    public String getYellowButtonMethod() {
        return mYellowButtonMethod;
    }

    public void setYellowButtonMethod(String yellowButtonMethod) {
        mYellowButtonMethod = yellowButtonMethod;
    }

    public String getYellowButtonRequestBody() {
        return mYellowButtonRequestBody;
    }

    public void setYellowButtonRequestBody(String yellowButtonRequestBody) {
        mYellowButtonRequestBody = yellowButtonRequestBody;
    }

    public String getYellowButtonContentType() {
        return mYellowButtonContentType;
    }

    public void setYellowButtonContentType(String yellowButtonContentType) {
        mYellowButtonContentType = yellowButtonContentType;
    }
}
