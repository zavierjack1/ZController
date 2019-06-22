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
    private EditText mButtonBURLField;
    private EditText mButtonBMethodField;
    private EditText mButtonBRequestBodyField;
    private EditText mButtonBContentTypeField;
    private EditText mButtonCURLField;
    private EditText mButtonCMethodField;
    private EditText mButtonCRequestBodyField;
    private EditText mButtonCContentTypeField;
    private EditText mButtonDURLField;
    private EditText mButtonDMethodField;
    private EditText mButtonDRequestBodyField;
    private EditText mButtonDContentTypeField;
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

    private EditText getUrlField(String buttonName){
        EditText returnEditText;
        switch (buttonName){
            case "A":
                returnEditText = mButtonAURLField;
                break;
            case "B":
                returnEditText = mButtonBURLField;
                break;
            case "C":
                returnEditText = mButtonCURLField;
                break;
            default:
                returnEditText = mButtonDURLField;
                break;
        }

        return returnEditText;
    }

    private EditText getMethodField(String buttonName){
        EditText returnEditText;
        switch (buttonName){
            case "A":
                returnEditText = mButtonAMethodField;
                break;
            case "B":
                returnEditText = mButtonBMethodField;
                break;
            case "C":
                returnEditText = mButtonCMethodField;
                break;
            default:
                returnEditText = mButtonDMethodField;
                break;
        }

        return returnEditText;
    }

    private EditText getRequestBodyField(String buttonName){
        EditText returnEditText;
        switch (buttonName){
            case "A":
                returnEditText = mButtonARequestBodyField;
                break;
            case "B":
                returnEditText = mButtonBRequestBodyField;
                break;
            case "C":
                returnEditText = mButtonCRequestBodyField;
                break;
            default:
                returnEditText = mButtonDRequestBodyField;
                break;
        }

        return returnEditText;
    }

    private EditText getContentTypeField(String buttonName){
        EditText returnEditText;
        switch (buttonName){
            case "A":
                returnEditText = mButtonAContentTypeField;
                break;
            case "B":
                returnEditText = mButtonBContentTypeField;
                break;
            case "C":
                returnEditText = mButtonCContentTypeField;
                break;
            default:
                returnEditText = mButtonDContentTypeField;
                break;
        }

        return returnEditText;
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

        mButtonBURLField = v.findViewById(R.id.controller_config_button_b_url);
        mButtonBMethodField = v.findViewById(R.id.controller_config_button_b_method);
        mButtonBRequestBodyField = v.findViewById(R.id.controller_config_button_b_request_body);
        mButtonBContentTypeField = v.findViewById(R.id.controller_config_button_b_content_type);

        mButtonCURLField = v.findViewById(R.id.controller_config_button_c_url);
        mButtonCMethodField = v.findViewById(R.id.controller_config_button_c_method);
        mButtonCRequestBodyField = v.findViewById(R.id.controller_config_button_c_request_body);
        mButtonCContentTypeField = v.findViewById(R.id.controller_config_button_c_content_type);

        mButtonDURLField = v.findViewById(R.id.controller_config_button_d_url);
        mButtonDMethodField = v.findViewById(R.id.controller_config_button_d_method);
        mButtonDRequestBodyField = v.findViewById(R.id.controller_config_button_d_request_body);
        mButtonDContentTypeField = v.findViewById(R.id.controller_config_button_d_content_type);
        
        mNameField.setText(mControllerConfig.getName());
        mDescriptionField.setText(mControllerConfig.getDescription());
        //mBackgroundColorButton.setText(mControllerConfig.getName());
        //for each button, set the button fields
        for (String buttonName: mControllerConfig.getButtons().keySet()){
            getUrlField(buttonName).setText(mControllerConfig.getButtons().get(buttonName).getUrl());
            getMethodField(buttonName).setText(mControllerConfig.getButtons().get(buttonName).getMethod());
            getRequestBodyField(buttonName).setText(mControllerConfig.getButtons().get(buttonName).getRequestBody());
            getContentTypeField(buttonName).setText(mControllerConfig.getButtons().get(buttonName).getContentType());
        }

        mSaveButton = v.findViewById(R.id.controller_config_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mControllerConfig.setName(mNameField.getText().toString());
                mControllerConfig.setDescription(mDescriptionField.getText().toString());
                for (String buttonName: mControllerConfig.getButtons().keySet()) {
                    mControllerConfig.getButtons().get(buttonName).setUrl(getUrlField(buttonName).getText().toString());
                    mControllerConfig.getButtons().get(buttonName).setMethod(getMethodField(buttonName).getText().toString());
                    mControllerConfig.getButtons().get(buttonName).setRequestBody(getRequestBodyField(buttonName).getText().toString());
                    mControllerConfig.getButtons().get(buttonName).setContentType(getContentTypeField(buttonName).getText().toString());

                }
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
