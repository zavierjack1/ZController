package android.zavierjack.zcontroller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

public class ControllerConfigPagerActivity extends AppCompatActivity {

    public static final String EXTRA_CONTROLLER_CONFIG_ID_KEY = "ControllerConfigPagerActivity.ControllerConfig_ID";

    private ViewPager mViewPager;
    private List<ControllerConfig> mControllerConfigs;

    public static Intent newIntent(Context packageContext, UUID controllerConfigID){

        Intent intent = new Intent(packageContext, ControllerConfigPagerActivity.class);
        intent.putExtra(EXTRA_CONTROLLER_CONFIG_ID_KEY, controllerConfigID);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Util.log("ControllerConfigPagerActivity.onCreate");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_controller_config_pager);
        UUID controllerConfigID = (UUID) getIntent().getSerializableExtra(EXTRA_CONTROLLER_CONFIG_ID_KEY);

        mViewPager = findViewById(R.id.controller_config_view_pager);
        //mViewPager.setOffscreenPageLimit(2);

        mControllerConfigs = ZControllerDOA.get(this).getControllerConfigs();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                ControllerConfig controllerConfig = mControllerConfigs.get(i);
                return ControllerConfigFragment.newInstance(controllerConfig.getID());
            }

            @Override
            public int getCount() {
                return mControllerConfigs.size();
            }
        });

        for (int i = 0; i < mControllerConfigs.size(); i++) {
            if (mControllerConfigs.get(i).getID().equals(controllerConfigID)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
