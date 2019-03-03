package android.zavierjack.zcontroller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.UUID;

public class ControllerConfigFragment extends Fragment {
    private static final String ARG_CONFIGURATION_ID_KEY = "ControllerConfigFragment.ControllerConfig_ID";

    private ControllerConfig mControllerConfig;
    private EditText mNameField;
    private EditText mDescriptionField;
    private Button mBackgroundColorButton;
    private EditText mButtonAURLField;
    private EditText mButtonAMethodField;
    private EditText mButtonARequestBodyField;
    private EditText mButtonAContentTypeField;

    private Button mSaveButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        UUID configurationID = (UUID) getArguments().getSerializable(ARG_CONFIGURATION_ID_KEY);

        mControllerConfig = ZControllerDOA.get(getActivity()).getControllerConfig(configurationID);
    }

    @Override
    public void onPause(){
        super.onPause();

        ZControllerDOA.get(getActivity()).updateControllerConfig(mControllerConfig);
    }

    public static ControllerConfigFragment newInstance(UUID configurationID){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CONFIGURATION_ID_KEY, configurationID);

        ControllerConfigFragment fragment = new ControllerConfigFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_controller_config, container, false);

        mNameField = v.findViewById(R.id.controller_config_name);
        mDescriptionField = v.findViewById(R.id.controller_config_description);
        mBackgroundColorButton = v.findViewById(R.id.controller_config_background_color);
        mButtonAURLField = v.findViewById(R.id.controller_config_button_a_url);
        mButtonAMethodField = v.findViewById(R.id.controller_config_button_a_method);
        mButtonARequestBodyField = v.findViewById(R.id.controller_config_button_a_request_body);
        mButtonAContentTypeField = v.findViewById(R.id.controller_config_button_a_content_type);

        mNameField.setText(mControllerConfig.getName());
        mDescriptionField.setText(mControllerConfig.getDescription());
        //mBackgroundColorButton.setText(mControllerConfig.getName());
        mButtonAURLField.setText(mControllerConfig.getButtonA().getUrl());
        mButtonAMethodField.setText(mControllerConfig.getButtonA().getMethod());
        mButtonARequestBodyField.setText(mControllerConfig.getButtonA().getRequestBody());
        mButtonAContentTypeField.setText(mControllerConfig.getButtonA().getContentType());

        mSaveButton = v.findViewById(R.id.controller_config_save);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mControllerConfig.setName(mNameField.getText().toString());
                mControllerConfig.setDescription(mDescriptionField.getText().toString());
                mControllerConfig.getButtonA().setUrl(mButtonAURLField.getText().toString());
                mControllerConfig.getButtonA().setMethod(mButtonAMethodField.getText().toString());
                mControllerConfig.getButtonA().setRequestBody(mButtonARequestBodyField.getText().toString());
                mControllerConfig.getButtonA().setContentType(mButtonAContentTypeField.getText().toString());
                getActivity().finish();
            }
        });

        /*
        mBackgroundColorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = ColorPicker.newInstance(mCrime.getDate());
                dialog.setTargetFragment(CrimeFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });
        */
        return v;
    }
}
