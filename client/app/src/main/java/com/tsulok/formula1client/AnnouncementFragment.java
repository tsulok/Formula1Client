package com.tsulok.formula1client;


import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tsulok.formula1client.helper.DataManager;
import com.tsulok.formula1client.helper.UIHelper;
import com.tsulok.formula1client.model.Announcement;
import com.tsulok.formula1client.utils.URLImageParser;

public class AnnouncementFragment extends NamedFragment {
    private static final String ARG_ID = "announcementId";

    private int announcementId;
    private Announcement announcement;

    private Button commentsBtn;
    private TextView titleView;
    private TextView contentView;
    private TextView authorView;
    private TextView leadView;

    /**
     *
     * @param id Announcement id.
     * @return A new instance of fragment AnnouncementFragment.
     */
    public static AnnouncementFragment newInstance(int id) {
        AnnouncementFragment fragment = new AnnouncementFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_ID, id);
        fragment.setArguments(args);
        return fragment;
    }

    public AnnouncementFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.announcement_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                Intent share = new Intent(android.content.Intent.ACTION_SEND);
                share.setType("text/plain");
                share.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.share_subject));
                share.putExtra(Intent.EXTRA_TEXT, getString(R.string.announcement_url) + announcement.getId());

                startActivity(Intent.createChooser(share, "Share link!"));
                return true;
            default:
                return true;
        }
    }

    @Override
    protected void inflateObjects(View v) {
        authorView = (TextView) v.findViewById(R.id.author);
        contentView = (TextView) v.findViewById(R.id.content);
        titleView = (TextView) v.findViewById(R.id.title);
        commentsBtn = (Button) v.findViewById(R.id.commentBtn);
        leadView = (TextView) v.findViewById(R.id.lead);
    }

    @Override
    protected void initObjects() {
        if (getArguments() != null) {
            announcementId = getArguments().getInt(ARG_ID);
            if(DataManager.getInstance().getAnnouncements().containsKey(announcementId)) {
                announcement = DataManager.getInstance().getAnnouncements().get(announcementId);
                authorView.setText(announcement.getAuthor());
                titleView.setText(announcement.getTitle());
                leadView.setText(announcement.getLead());

//                contentView.setText(Html.fromHtml(announcement.getContent()));
                URLImageParser p = new URLImageParser(contentView, getActivity());
                String trickedImg = replaceContentImage(announcement.getContent());
                Spanned htmlSpan = Html.fromHtml(trickedImg, p, null);
                contentView.setText(htmlSpan);
            } else {
                UIHelper.showToast(R.string.alert_developer);
            }
        }
    }

    private String replaceContentImage(String content){
        return content.replace("<img","<p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><img ");
    }

    @Override
    protected void initEventHandlers() {
        commentsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DrawerMainActivity)getActivity()).pushFragment(
                        CommentFragment.newInstance(announcement.getId()),
                        CommentFragment.TAG,
                        true);
            }
        });
    }

    @Override
    public int getTitleId() {
        return R.string.title_announcement;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_announcement;
    }
}
