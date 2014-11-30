package com.tsulok.formula1client;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.tsulok.formula1client.adapter.CommentAdapter;
import com.tsulok.formula1client.helper.DataManager;
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if(DataManager.getInstance().getCurrentUser() != null){
            inflater.inflate(R.menu.comment_menu ,menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_comment:
                showAlertdialog();
                return true;
            default:
                return true;
        }
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

    private void showAlertdialog(){
        final EditText input = new EditText(getActivity());
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.dialog_new_comment)
                .setView(input)
                .setPositiveButton(R.string.dialog_send, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        UIHelper.showProgress(true, recyclerView);
                        dialog.dismiss();
                        Api.sendComment(announcementId, input.getText().toString(), DataManager.getInstance().getCurrentUser().getUsername(), new IAction() {
                            @Override
                            public void doAction(Object... args) {
                                Api.getComments(announcementId, new IAction() {
                                    @Override
                                    public void doAction(Object... args) {
                                        if(args != null && args.length == 1){
                                            adapter.updateItems(((CommentContainer)args[0]).getComments());
                                        }
                                        UIHelper.hideProgress();
                                    }
                                });
                            }
                        });
                    }
                })
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
