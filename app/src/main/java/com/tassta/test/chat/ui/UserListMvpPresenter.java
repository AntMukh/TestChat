package com.tassta.test.chat.ui;

/**
 * Created by user on 07.09.2017.
 */

public interface UserListMvpPresenter<V extends UserListMvpView> {
    void onAttached(V v);

    void refreshList();
}
