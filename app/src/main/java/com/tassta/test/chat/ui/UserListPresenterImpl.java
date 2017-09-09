package com.tassta.test.chat.ui;

import android.util.Log;

import com.tassta.test.chat.UserImpl;
import com.tassta.test.chat.data.DataManager;
import com.tassta.test.chat.model.UserBundle;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 07.09.2017.
 */

public class UserListPresenterImpl<V extends UserListMvpView> implements UserListMvpPresenter<V> {

    public static final String LOG_TAG = UserListPresenterImpl.class.getSimpleName();

    DataManager mDataManager;
    UserListMvpView mUserListMvpView;


    UserListPresenterImpl(DataManager dataManager) {
        mDataManager = dataManager;
    }

    public void onAttached(V v) {
        mUserListMvpView = v;

        mDataManager.registerUserListListener(this);

        Date date = new Date();
        date.getDate();
        try {
            mDataManager.getIoManager().sendMessage(new UserImpl("Andrey", 1, true, null), "hi");
        } catch (Exception e) {
        }
        refreshList();
    }

    public void refreshList() {
        mUserListMvpView.setAdapter(getListfromDataManager());
    }

    public List<UserBundle> getListfromDataManager() {
        return mDataManager.getUserBundles();
    }

}
