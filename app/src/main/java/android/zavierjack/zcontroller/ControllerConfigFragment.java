package android.zavierjack.zcontroller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.UUID;

public class ControllerConfigFragment extends Fragment {
    private static final String ARG_CONFIGURATION_ID_KEY = "ControllerConfigFragment.ControllerConfig_ID";

    private ControllerConfig mControllerConfig;
    private TextView mNameFieldLabel;
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_controller_config, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.delete_controller_config:
                ZControllerDOA.get(getActivity()).deleteControllerConfig(mControllerConfig.getID());
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        switch (buttonName) {
            case "A":
                returnEditText = mButtonAURLField;
                break;
            case "B":
                returnEditText = mButtonBURLField;
                break;
            case "C":
                returnEditText = mButtonCURLField;
                break;
            case "D":
                returnEditText = mButtonDURLField;
                break;
            default:
                throw new IllegalArgumentException("We do not have a button named: " + buttonName);
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
            case "D":
                returnEditText = mButtonDMethodField;
                break;
            default:
                throw new IllegalArgumentException("We do not have a button named: " + buttonName);
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
            case "D":
                returnEditText = mButtonDRequestBodyField;
                break;
            default:
                throw new IllegalArgumentException("We do not have a button named: " + buttonName);
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
            case "D":
                returnEditText = mButtonDContentTypeField;
                break;
            default:
                throw new IllegalArgumentException("We do not have a button named: " + buttonName);
        }

        return returnEditText;
    }

    private final void focusOnView(ScrollView scrollView, TextView textView){

        final ScrollView finalScrollView = scrollView;
        final TextView finalTextView = textView;

        scrollView.post(new Runnable() {
            @Override
            public void run() {
                finalScrollView.smoothScrollTo(0, finalTextView.getTop());
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_controller_config, container, false);

        mNameFieldLabel = v.findViewById(R.id.controller_config_name_label);
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

        final ScrollView mScrollView = v.findViewById(R.id.controller_config_scroll_view);

        mSaveButton = v.findViewById(R.id.controller_config_save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //make name field required
                if (mNameField.getText().toString().trim().equals("")) {
                    Util.showShortToast(view.getContext(), "Name is required");
                    mNameField.setSelected(true);
                    focusOnView(mScrollView, mNameFieldLabel);
                } else {
                    mControllerConfig.setName(mNameField.getText().toString());
                    mControllerConfig.setDescription(mDescriptionField.getText().toString());
                    for (String buttonName : mControllerConfig.getButtons().keySet()) {
                        mControllerConfig.getButtons().get(buttonName).setUrl(getUrlField(buttonName).getText().toString());
                        mControllerConfig.getButtons().get(buttonName).setMethod(getMethodField(buttonName).getText().toString());
                        mControllerConfig.getButtons().get(buttonName).setRequestBody(getRequestBodyField(buttonName).getText().toString());
                        mControllerConfig.getButtons().get(buttonName).setContentType(getContentTypeField(buttonName).getText().toString());
                    }
                    getActivity().finish();
                }
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
