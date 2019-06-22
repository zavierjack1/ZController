package android.zavierjack.zcontroller;

import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

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
        View v;
        if (getResources().getConfiguration().orientation ==Configuration.ORIENTATION_PORTRAIT){
             v = inflater.inflate(R.layout.fragment_controller_portrait, container, false);
        }
        else if (getResources().getConfiguration().orientation ==Configuration.ORIENTATION_LANDSCAPE){
            v = inflater.inflate(R.layout.fragment_controller_landscape, container, false);
        }
        else{
            v = inflater.inflate(R.layout.fragment_controller_portrait, container, false);
        }
        final ScrollView mResponseMonitorScrollView = v.findViewById(R.id.response_monitor_scrollview);

        int button_id =0;
        for (Map.Entry<String, ControllerButton> controllerButtonEntry: mControllerConfig.getButtons().entrySet()){
            switch (controllerButtonEntry.getKey()){
                case "A":
                    button_id = R.id.button_a;
                    break;
                case "B":
                    button_id = R.id.button_b;
                    break;
                case "C":
                    button_id = R.id.button_c;
                    break;
                case "D":
                    button_id = R.id.button_d;
                    break;
            }
            Button mFragmentButton = v.findViewById(button_id);
            ControllerButton controllerButton = controllerButtonEntry.getValue();
            controllerButton.setColor(((ColorDrawable) mFragmentButton.getBackground()).getColor());
            mFragmentButton.setOnClickListener(controllerButton.getControllerConfigButtonOnClickListener(getActivity().getApplicationContext(), v, mResponseMonitorScrollView));

        }

        return v;
    }
}
