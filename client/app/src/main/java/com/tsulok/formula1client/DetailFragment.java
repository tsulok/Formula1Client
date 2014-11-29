package com.tsulok.formula1client;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.tsulok.formula1client.model.Announcement;
import com.tsulok.formula1client.model.IdentifiedModel;
import com.tsulok.formula1client.model.Season;
import com.tsulok.formula1client.model.Team;

public class DetailFragment extends NamedFragment {

    private static final String ARG_DATA = "Data";

    private IdentifiedModel data;

    private TextView headerTxt;

    /**
     *
     * @param data
     * @return A new instance of fragment DeatailFragment.
     */
    public static DetailFragment newInstance(IdentifiedModel data) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    protected void inflateObjects(View v) {
        headerTxt = (TextView) v.findViewById(R.id.headerTxt);
    }

    @Override
    protected void initObjects() {
        if (getArguments() != null) {
            data = (IdentifiedModel)getArguments().getSerializable(ARG_DATA);
        }

        if(data instanceof Announcement){
            headerTxt.setText(((Announcement) data).getContent());
        } else if(data instanceof Team){
            headerTxt.setText(((Team) data).getName());
        } else if(data instanceof Season){
            headerTxt.setText(((Season) data).getYear() + "");
        }
    }

    @Override
    protected void initEventHandlers() {

    }

    @Override
    public int getTitleId() {
        return R.string.title_details;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_deatail;
    }
}
