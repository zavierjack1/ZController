package android.zavierjack.zcontroller;

import android.support.v4.app.Fragment;

public class ControllerConfigListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new ControllerConfigListFragment();
    }
}

