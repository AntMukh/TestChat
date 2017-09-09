package com.tassta.test.chat.model;

import com.tassta.test.chat.User;
import com.tassta.test.chat.UserImpl;

/**
 * Created by user on 07.09.2017.
 */

public class UserBundleImpl extends UserImpl
        implements UserBundle {
    private User user;
    //if any unread messages from this sender
    private int hasUnreadFromSender;

    public UserBundleImpl(User user, int hasUnread) {
        super(user.getName(), user.getId(), user.isOnline(), user.getIcon());
        hasUnreadFromSender = hasUnread;
    }

    @Override
    public int hasUnread() {
        return hasUnreadFromSender;
    }
}
