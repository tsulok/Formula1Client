package com.tsulok.formula1client;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tsulok.formula1client.adapter.MyAdapter;
import com.tsulok.formula1client.model.IdentifiedModel;

import java.util.ArrayList;

public class ListFragment extends NamedFragment {

    public static String TAG = "ListFragment";
    protected static final String ARG_TITLE = "title";
    protected static final String ARG_DATA = "data";

    protected int titleId;
    protected ArrayList<? extends IdentifiedModel> data;

    protected RecyclerView recyclerView;
    protected MyAdapter adapter;
    protected RecyclerView.LayoutManager layoutManager;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param titleId The title of the fragment.
     * @param data The data should be represented.
     * @return A new instance of fragment ListFragment.
     */
    public static ListFragment newInstance(int titleId, ArrayList<? extends IdentifiedModel> data) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_TITLE, titleId);
        args.putSerializable(ARG_DATA, data);
        fragment.setArguments(args);
        return fragment;
    }

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    protected void inflateObjects(View v) {
        recyclerView = (RecyclerView) v.findViewById(R.id.recycleView);
    }

    @Override
    protected void initObjects() {
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        if(getArguments() != null){
            titleId = getArguments().getInt(ARG_TITLE);
            data = (ArrayList<? extends IdentifiedModel>)getArguments().getSerializable(ARG_DATA);
        }

        adapter = new MyAdapter(data, new MyAdapter.ViewHolder.IAdapterClicker() {
            @Override
            public void lisItemClicked(View v) {
                int itemPositon = recyclerView.getChildPosition(v);
                IdentifiedModel data = adapter.getItem(itemPositon);
                ((DrawerMainActivity) getActivity()).pushFragment(
                        HostFragment.newInstance(data, itemPositon),
                        HostFragment.TAG,
                        true);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void initEventHandlers() {
    }

    @Override
    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }
}
