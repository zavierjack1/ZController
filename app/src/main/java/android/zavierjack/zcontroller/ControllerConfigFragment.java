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
    private EditText mRedButtonURLField;
    private EditText mRedButtonMethodField;
    private EditText mRedButtonRequestBodyField;
    private EditText mRedButtonContentTypeField;

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
        mRedButtonURLField = v.findViewById(R.id.controller_config_red_button_url);
        mRedButtonMethodField = v.findViewById(R.id.controller_config_red_button_method);
        mRedButtonRequestBodyField = v.findViewById(R.id.controller_config_red_button_request_body);
        mRedButtonContentTypeField = v.findViewById(R.id.controller_config_red_button_content_type);

        mNameField.setText(mControllerConfig.getName());
        mDescriptionField.setText(mControllerConfig.getDescription());
        //mBackgroundColorButton.setText(mControllerConfig.getName());
        mRedButtonURLField.setText(mControllerConfig.getRedButtonUrl());
        mRedButtonMethodField.setText(mControllerConfig.getRedButtonMethod());
        mRedButtonRequestBodyField.setText(mControllerConfig.getRedButtonRequestBody());
        mRedButtonContentTypeField.setText(mControllerConfig.getRedButtonContentType());

        mSaveButton = v.findViewById(R.id.controller_config_save);

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mControllerConfig.setName(mNameField.getText().toString());
                mControllerConfig.setDescription(mDescriptionField.getText().toString());
                //backgroundcolor
                mControllerConfig.setRedButtonUrl(mRedButtonURLField.getText().toString());
                mControllerConfig.setRedButtonMethod(mRedButtonMethodField.getText().toString());
                mControllerConfig.setRedButtonRequestBody(mRedButtonRequestBodyField.getText().toString());
                mControllerConfig.setRedButtonContentType(mRedButtonContentTypeField.getText().toString());
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
