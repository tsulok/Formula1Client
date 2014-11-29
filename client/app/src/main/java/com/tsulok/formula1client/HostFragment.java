package com.tsulok.formula1client;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.tsulok.formula1client.adapter.MyPagerAdapter;
import com.tsulok.formula1client.helper.DataManager;
import com.tsulok.formula1client.model.Announcement;
import com.tsulok.formula1client.model.IdentifiedModel;
import com.tsulok.formula1client.model.Season;
import com.tsulok.formula1client.model.Team;

import java.util.ArrayList;

public class HostFragment extends NamedFragment {

    public static String TAG = "HostFagment";
    private static final String ARG_DATA = "data";
    private static final String ARG_POSITION = "position";

    private ViewPager viewPager;
    private MyPagerAdapter pagerAdapter;
    private int position = 0;

    public HostFragment() {
    }

    public static HostFragment newInstance(IdentifiedModel data, int selectedPosition){
        HostFragment hostFragment = new HostFragment();
        Bundle extras = new Bundle();
        extras.putSerializable(ARG_DATA, data);
        extras.putInt(ARG_POSITION, selectedPosition);
        hostFragment.setArguments(extras);
        return hostFragment;
    }

    @Override
    protected void inflateObjects(View v) {
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
    }

    @Override
    protected void initObjects() {
        pagerAdapter = new MyPagerAdapter(((DrawerMainActivity)getActivity()).getSupportFragmentManager(), viewPager);

        IdentifiedModel data = null;
        if(getArguments() != null){
            data = (IdentifiedModel) getArguments().getSerializable(ARG_DATA);
            position = getArguments().getInt(ARG_POSITION);
        }

        ArrayList<NamedFragment> fragments = new ArrayList<NamedFragment>();
        if(data != null){
            if(data instanceof Announcement){
                for (Announcement a : DataManager.getInstance().getAnnouncementsAsList()) {
                    fragments.add(DetailFragment.newInstance(a));
                }
            } else if(data instanceof Team){
                for (Team t : DataManager.getInstance().getTeamsAsList()) {
                    fragments.add(DetailFragment.newInstance(t));
                }
            } else if(data instanceof Season){
                for (Season s : DataManager.getInstance().getSeasons()) {
                    fragments.add(DetailFragment.newInstance(s));
                }
            }
        }

        pagerAdapter.setFragments(fragments);
        viewPager.setAdapter(pagerAdapter);
        viewPager.post(new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(position);
            }
        });
    }

    @Override
    protected void initEventHandlers() {

    }

    @Override
    public int getTitleId() {
        return pagerAdapter.getSelectedItemTitle();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_host;
    }
}
