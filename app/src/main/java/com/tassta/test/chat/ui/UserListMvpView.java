package com.tassta.test.chat.ui;

import com.tassta.test.chat.model.UserBundle;

import java.util.List;

/**
 * Created by user on 07.09.2017.
 */

public interface UserListMvpView {
    void setAdapter(List<UserBundle> userBundles);
}
