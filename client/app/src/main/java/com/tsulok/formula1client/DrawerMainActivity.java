package com.tsulok.formula1client;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.tsulok.formula1client.helper.DataManager;
import com.tsulok.formula1client.helper.UIHelper;


public class DrawerMainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, LoginFragment.OnLoginListener{

    private static DrawerMainActivity instance;
    private DataManager dataManager;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private Toolbar mToolbar;
    private ProgressBar progressBar;
    private View containerView;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_main);

        App.initializeDummy();
        dataManager = DataManager.getInstance();
        instance = this;

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getFragmentManager().findFragmentById(R.id.navigation_drawer);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        containerView = findViewById(R.id.mainContainer);
        mTitle = getTitle();

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        setSupportActionBar(mToolbar);

        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                UIHelper.showProgress(true, progressBar, containerView);
                loadInitialData(new IAction() {
                    @Override
                    public void doAction(Object... args) {
                        UIHelper.hideProgress();
                        pushFragment(ListFragment.newInstance(
                                R.string.title_announcements, dataManager.getAnnouncementsAsList()),
                                "Announcements");
                    }
                });
            }
        });
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        NamedFragment fragment = null;
        String tag = null;

        switch (position){
            case 1:
                fragment = ListFragment.newInstance(R.string.title_announcements, dataManager.getAnnouncementsAsList());
                tag = "Announcements";
                break;
            case 2:
                fragment = ListFragment.newInstance(R.string.title_teams, dataManager.getTeamsAsList());
                tag = "Teams";
                break;
            case 3:
                fragment = ListFragment.newInstance(R.string.title_season, dataManager.getSeasons());
                tag = "Seasons";
                break;
            case 4:
                fragment = new LoginFragment();
                tag = LoginFragment.TAG;
                break;
        }
        if(fragment != null && tag != null){
            clearBackStack();
            pushFragment(fragment, tag);
        } else {
            UIHelper.showToast("Developer error happened");
        }

//        FragmentManager fragmentManager = getFragmentManager();
//        fragmentManager.beginTransaction()
//                .replace(R.id.container, PlaceholderFragment.newInstance(a))
//                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_announcements);
                break;
            case 2:
                mTitle = getString(R.string.title_teams);
                break;
            case 3:
                mTitle = getString(R.string.title_season);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setTitle(mTitle);
    }

    public void pushFragment(final NamedFragment fragment, String tag){
        pushFragment(fragment, tag, false);
    }

    public void pushFragment(final NamedFragment fragment, String tag, boolean addToBackStack){
        // update the main content by replacing fragments
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();

        final int entryCount = fragmentManager.getBackStackEntryCount();

        // find on the stack
        boolean found = false;
        if (tag != null) {
            for (int i = 0; i < entryCount; i++) {
                final FragmentManager.BackStackEntry entry = fragmentManager.getBackStackEntryAt(i);
                final String name = entry.getName();
                if (name == tag) {
                    found = true;
                    for (int j = entryCount; j > (i + 1); j--) {
                        fragmentManager.popBackStack();
                    }
                }
            }
        }

        FragmentTransaction transaction = fragmentManager.beginTransaction()
                .replace(R.id.container, fragment);
        if(addToBackStack){
            showBack();
            transaction.addToBackStack(tag);
        }
        transaction.commit();
        mToolbar.post(new Runnable() {
            @Override
            public void run() {
                updateTitle(fragment.getTitleId());
            }
        });
    }

    public void updateTitle(int titleId){
        getSupportActionBar().setTitle(titleId);
    }

    public void updateTitle(){
        getSupportActionBar().setTitle(getActiveNamedFragment().getTitleId());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.drawer_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mNavigationDrawerFragment.getmDrawerToggle().onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()){
            case android.R.id.home:
                if (mNavigationDrawerFragment.getmDrawerToggle().isDrawerIndicatorEnabled()) {
                    return mNavigationDrawerFragment.getmDrawerToggle().onOptionsItemSelected(item);
                } else {
                    this.onBackPressed();
                    return true;
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        updateBack();
        updateTitle();
    }

    /**
     * Gets the active fragment
     * @return The active fragment.
     */
    public NamedFragment getActiveNamedFragment() {
        if (this.getSupportFragmentManager().findFragmentById(R.id.container) instanceof NamedFragment) {
            return (NamedFragment) this.getSupportFragmentManager().findFragmentById(R.id.container);
        } else {
            return null;
        }
    }

    protected void updateBack() {
        final FragmentManager fragmentManager = this.getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() < 1) {
            this.hideBack();
        } else {
            this.showBack();
        }
    }

    protected void showBack() {
        this.mNavigationDrawerFragment.setNavigationDrawerToogleEnable(false);
    }

    protected void hideBack() {
        this.mNavigationDrawerFragment.setNavigationDrawerToogleEnable(true);
    }

    protected void clearBackStack() {
        final FragmentManager fm = this.getSupportFragmentManager();
        while (0 < fm.getBackStackEntryCount()) {
            fm.popBackStackImmediate();
        }
        this.hideBack();
    }

    @Override
    public void onLoginSucceeded(String username) {

    }

    private void loadInitialData(final IAction action){
        Api.getAnnouncements(new IAction() {
            @Override
            public void doAction(Object... args) {
                Api.getDrivers(new IAction() {
                    @Override
                    public void doAction(Object... args) {
                        Api.getTeams(new IAction() {
                            @Override
                            public void doAction(Object... args) {
                                action.doAction();
                            }
                        });
                    }
                });
            }
        });
    }

    public Toolbar getmToolbar() {
        return mToolbar;
    }

    public static DrawerMainActivity getInstance() {
        return instance;
    }

    public void updateUser(){
        mNavigationDrawerFragment.updateUserName();
    }
}
