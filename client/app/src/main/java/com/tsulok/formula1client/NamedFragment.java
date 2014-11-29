package com.tsulok.formula1client;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class NamedFragment extends Fragment {

    private int titleId;
    private int layoutId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(getLayoutId(), container, false);

        inflateObjects(v);
        initObjects();
        initEventHandlers();

        return v;
    }

    /**
     *
     * @param v
     */
    protected abstract void inflateObjects(View v);

    /**
     *
     */
    protected abstract void initObjects();

    /**
     *
     */
    protected abstract void initEventHandlers();

    public abstract int getTitleId();
    protected abstract int getLayoutId();
}
