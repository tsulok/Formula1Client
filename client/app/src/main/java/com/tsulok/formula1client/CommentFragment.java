package com.tsulok.formula1client;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tsulok.formula1client.adapter.CommentAdapter;
import com.tsulok.formula1client.helper.UIHelper;
import com.tsulok.formula1client.rest.container.CommentContainer;

public class CommentFragment extends NamedFragment {
    public static final String TAG = "CommentFragment";
    private static final String ARG_ANNOUNCEMENT_ID = "announcementID";

    private int announcementId;
    private RecyclerView recyclerView;
    private CommentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    /**
     * @return A new instance of fragment CommentFragment.
     */
    public static CommentFragment newInstance(int id) {
        CommentFragment fragment = new CommentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ANNOUNCEMENT_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public CommentFragment() {
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

        if (getArguments() != null) {
            announcementId = getArguments().getInt(ARG_ANNOUNCEMENT_ID);
            UIHelper.showProgress(true, recyclerView);
            Api.getComments(announcementId, new IAction() {
                @Override
                public void doAction(Object... args) {
                    if(args != null && args.length == 1){
                        UIHelper.hideProgress();
                        CommentContainer commentContainer = (CommentContainer) args[0];
                        adapter = new CommentAdapter(commentContainer.getComments());
                        recyclerView.setAdapter(adapter);
                    }
                }
            });
        }
    }

    @Override
    protected void initEventHandlers() {

    }

    @Override
    public int getTitleId() {
        return R.string.title_comment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_comment;
    }
}
