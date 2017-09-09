package com.tassta.test.chat.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.tassta.test.chat.ChatApp;
import com.tassta.test.chat.R;
import com.tassta.test.chat.model.UserBundle;
import com.tassta.test.chat.noimpl.IoManagerImpl;

import java.util.List;

public class UserListActivity extends AppCompatActivity
        implements UserListMvpView,
        NavigationView.OnNavigationItemSelectedListener {
    public static final String LOG_TAG = UserListActivity.class.getSimpleName();

    UserListMvpPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        mPresenter = new UserListPresenterImpl(((ChatApp) getApplication()).getDataManager());
        mPresenter.onAttached(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public void setAdapter(List<UserBundle> userBundles) {
        UserListAdapter adapter = new UserListAdapter(getApplicationContext(), userBundles);
        ListView list = (ListView) findViewById(R.id.userlist);
        list.setAdapter(adapter);
        list.invalidate();
    }

    public UserListMvpPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // register useradded handler and add 2 users
        if (id == R.id.nav_camera) {
            ((IoManagerImpl) ((UserListPresenterImpl) mPresenter).mDataManager.getIoManager()).setUserAddedHandler(
                    ((UserListPresenterImpl) mPresenter).mDataManager.getUserConsumer());

            ((IoManagerImpl) ((UserListPresenterImpl) mPresenter).mDataManager.getIoManager()).addUser();

            //regidter message handler and send message

        } else if (id == R.id.nav_gallery) {
            ((IoManagerImpl) ((UserListPresenterImpl) mPresenter).mDataManager.getIoManager()).setRecieveMessageHandler(
                    ((UserListPresenterImpl) mPresenter).mDataManager.getMessageConsumer());

            ((IoManagerImpl) ((UserListPresenterImpl) mPresenter).mDataManager.getIoManager()).sendMessageToApp();
        } else if (id == R.id.nav_slideshow) {
        // register change state handler and change state
            ((IoManagerImpl) ((UserListPresenterImpl) mPresenter).mDataManager.getIoManager()).setUserStateChangeHandler(
                    ((UserListPresenterImpl) mPresenter).mDataManager);
            ((IoManagerImpl) ((UserListPresenterImpl) mPresenter).mDataManager.getIoManager()).changeState();
        // set delete user handler and delete user
        } else if (id == R.id.nav_manage) {
            ((IoManagerImpl) ((UserListPresenterImpl) mPresenter).mDataManager.getIoManager()).setUserRemovedHandler(
                    ((UserListPresenterImpl) mPresenter).mDataManager);

            ((IoManagerImpl) ((UserListPresenterImpl) mPresenter).mDataManager.getIoManager()).removeUser();

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onAttached(this);
    }

}
