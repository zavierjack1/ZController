package android.zavierjack.zcontroller;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

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
/*
        Display display = ((WindowManager) this.getContext().getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        int rotation = display.getRotation();
        Log.d(Util.LOG_TAG, "rotation: "+rotation);
*/
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
        final TextView mFeedBackMonitor = v.findViewById(R.id.feedback_monitor);

        //mFeedBackMonitor.setMovementMethod(new ScrollingMovementMethod());

        Button mButtonA = v.findViewById(R.id.button_a);
        mButtonA.setOnClickListener(mControllerConfig.getButtonA().getControllerConfigButtonOnClickListener(getActivity().getApplicationContext(), v, mFeedBackMonitor));

        Button mButtonB = v.findViewById(R.id.button_b);
        mButtonB.setOnClickListener(mControllerConfig.getButtonB().getControllerConfigButtonOnClickListener(getActivity().getApplicationContext(), v, mFeedBackMonitor));

        Button mButtonC = v.findViewById(R.id.button_c);
        mButtonC.setOnClickListener(mControllerConfig.getButtonC().getControllerConfigButtonOnClickListener(getActivity().getApplicationContext(), v, mFeedBackMonitor));

        Button mButtonD = v.findViewById(R.id.button_d);
        mButtonD.setOnClickListener(mControllerConfig.getButtonD().getControllerConfigButtonOnClickListener(getActivity().getApplicationContext(), v, mFeedBackMonitor));

        return v;
    }
}
