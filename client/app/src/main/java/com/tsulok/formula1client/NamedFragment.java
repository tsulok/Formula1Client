package com.tsulok.formula1client;


import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public abstract class NamedFragment extends Fragment {

    private int titleId;
    private int layoutId;
    public static String TAG;

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

    protected abstract int getTitleId();
    protected abstract int getLayoutId();
}
