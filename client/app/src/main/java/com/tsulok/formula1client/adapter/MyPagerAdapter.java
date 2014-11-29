package com.tsulok.formula1client.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.tsulok.formula1client.NamedFragment;
import com.tsulok.formula1client.R;
import com.tsulok.formula1client.helper.Helper;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentStatePagerAdapter{

    protected List<NamedFragment> fragments;
    private ViewPager pager;

    public MyPagerAdapter(FragmentManager manager, ViewPager pager){
        super(manager);
        fragments = new ArrayList<NamedFragment>();
        this.pager = pager;
    }

    @Override
    public int getItemPosition(final Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override
    public NamedFragment getItem(final int position) {
        return this.fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        int id = 0;
        int curr = getCurrentPosition();
        if (position == curr)
            return (position + 1) + " / " + getCount();

        if (position > curr)
            id = R.string.lbl_next;
        else
            id = R.string.lbl_previous;

        return Helper.getStringRes(id);
    }

    public int getCurrentPosition() {
        return pager.getCurrentItem();
    }

    public void setFragments(List<NamedFragment> fragments){
        this.fragments = fragments;
    }

    public int getSelectedItemTitle(){
        if(fragments != null) {
            return getItem(getCurrentPosition()).getTitleId();
        }
        return 0;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
