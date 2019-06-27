package android.zavierjack.zcontroller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class ControllerConfigListFragment extends Fragment {

    private static final String SAVED_SUBTITLE_VISIBLE_KEY = "subtitle";
    private RecyclerView mControllerConfigRecyclerView;
    private ControllerConfigAdapter mAdapter;
    private boolean mSubtitleVisible;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
/*
        ZControllerDOA zControllerDOA = ZControllerDOA.get(getActivity());

        ControllerConfig controllerConfig = new ControllerConfig(
            new ControllerConfigJson(
                "{\"name\":\"Zavier\",\"description\":\"this is a working example more text\",\"background_color\":\"\",\"buttons\":{\"A\":{\"name\":\"A\",\"url\":\"http://www.google.com\",\"method\":\"GET\",\"request_body\":\"\",\"content_type\":\"\"},\"B\":{\"name\":\"B\",\"url\":\"yellow\",\"method\":\"\",\"request_body\":\"\",\"content_type\":\"\"},\"C\":{\"name\":\"C\",\"url\":\"yellow\",\"method\":\"\",\"request_body\":\"\",\"content_type\":\"\"},\"D\":{\"name\":\"D\",\"url\":\"purple\",\"method\":\"POST\",\"request_body\":\"\",\"content_type\":\"\"}}}"
            )
        );
        ZControllerDOA.get(getActivity()).addControllerConfig(controllerConfig);
        Util.log(controllerConfig.toString());
*/
        View view = inflater.inflate(R.layout.fragment_controller_config_list, container, false);

        mControllerConfigRecyclerView = view.findViewById(R.id.configuration_recycler_view);
        mControllerConfigRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        if(savedInstanceState != null){
            mSubtitleVisible = savedInstanceState.getBoolean(SAVED_SUBTITLE_VISIBLE_KEY);
        }
        updateUI();

        return view;
    }

    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean(SAVED_SUBTITLE_VISIBLE_KEY, mSubtitleVisible);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_controller_config_list, menu);

        MenuItem subtitleItem = menu.findItem(R.id.show_subtitle);
        if(mSubtitleVisible) {
            subtitleItem.setTitle(R.string.hide_subtitle);
        }
        else{
            subtitleItem.setTitle(R.string.show_subtitle);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.new_controller_config:
                ControllerConfig controllerConfig = new ControllerConfig();
                ZControllerDOA.get(getActivity()).addControllerConfig(controllerConfig);
                Intent intent = ControllerConfigPagerActivity.newIntent(getActivity(), controllerConfig.getID());
                startActivity(intent);
                return true;
            case R.id.show_subtitle:
                mSubtitleVisible = !mSubtitleVisible;
                getActivity().invalidateOptionsMenu();
                updateSubtitle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateSubtitle(){
        ZControllerDOA zControllerDOA = ZControllerDOA.get(getActivity());
        int controllerConfigCount = zControllerDOA.getControllerConfigs().size();
        String subtitle = getString(R.string.subtitle_format, controllerConfigCount);

        if(!mSubtitleVisible){
            subtitle = null;
        }

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setSubtitle(subtitle);
    }

    private void updateUI(){
        ZControllerDOA zControllerDOA = ZControllerDOA.get(getActivity());
        List<ControllerConfig> controllerConfigs = zControllerDOA.getControllerConfigs();

        if(mAdapter == null){
            mAdapter = new ControllerConfigAdapter(controllerConfigs);
            mControllerConfigRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.setControllerConfigs(controllerConfigs);
            mAdapter.notifyDataSetChanged();
        }

        updateSubtitle();
    }

    private class ControllerConfigHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ControllerConfig mControllerConfig;
        private TextView mConfigNameTextView;
        private TextView mConfigDescriptionTextView;
        private ImageButton mConfigSettingsButton;


        public ControllerConfigHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_controller_config, parent, false));

            mConfigNameTextView = itemView.findViewById(R.id.config_name);
            mConfigDescriptionTextView = itemView.findViewById(R.id.config_description);
            mConfigSettingsButton = itemView.findViewById(R.id.config_settings_button);

            mConfigSettingsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = ControllerConfigPagerActivity.newIntent(getActivity(), mControllerConfig.getID());
                    startActivity(intent);
                }
            });

            itemView.setOnClickListener(this);
        }

        public void bind(ControllerConfig controllerConfig){
            mControllerConfig = controllerConfig;
            mConfigNameTextView.setText(mControllerConfig.getName());
            mConfigDescriptionTextView.setText(mControllerConfig.getDescription());
        }

        @Override
        public void onClick(View view){
            Intent intent = ControllerActivity.newIntent(getActivity(), mControllerConfig.getID());
            startActivity(intent);
        }
    }

    private class ControllerConfigAdapter extends RecyclerView.Adapter<ControllerConfigHolder>{

        private List<ControllerConfig> mControllerConfigs;

        public ControllerConfigAdapter(List<ControllerConfig> controllerConfigs){
            mControllerConfigs = controllerConfigs;
        }

        @Override
        public ControllerConfigHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

            return new ControllerConfigHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ControllerConfigHolder holder, int i) {
            ControllerConfig controllerConfig = mControllerConfigs.get(i);
            holder.bind(controllerConfig);
        }

        @Override
        public int getItemCount() {
            return mControllerConfigs.size();
        }

        public void setControllerConfigs(List<ControllerConfig> controllerConfigs){
            mControllerConfigs = controllerConfigs;
        }
    }
}