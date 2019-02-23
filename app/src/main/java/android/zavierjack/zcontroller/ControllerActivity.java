package android.zavierjack.zcontroller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class ControllerActivity extends SingleFragmentActivity {
    public static final String EXTRA_CONTROLLER_CONFIG_ID_KEY = "com.android.controllerActivity.ControllerConfigID";

    ControllerConfig mControllerConfig;

    @Override
    protected Fragment createFragment(){
        return ControllerFragment.newInstance((UUID) getIntent().getSerializableExtra(EXTRA_CONTROLLER_CONFIG_ID_KEY));
    }


    public static Intent newIntent(Context packageContext, UUID controllerConfigID){
        Intent intent = new Intent(packageContext, ControllerActivity.class);
        intent.putExtra(EXTRA_CONTROLLER_CONFIG_ID_KEY, controllerConfigID);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
