package com.tassta.test.chat.noimpl;

import android.util.Log;

import com.tassta.test.chat.Consumer;
import com.tassta.test.chat.Message;
import com.tassta.test.chat.MessageImpl;
import com.tassta.test.chat.User;
import com.tassta.test.chat.UserImpl;
import com.tassta.test.chat.UserRemovedHandler;
import com.tassta.test.chat.UserStateChangeHandler;

import java.util.Date;

/**
 * Created by user on 08.09.2017.
 */

public class IoManagerImpl implements IoManger {

    Consumer<Message> messageHandler;
    Consumer<User> userHandler;
    UserStateChangeHandler changeUserStateHandler;
    UserRemovedHandler removedHandler;

    public void setUserStateChangeHandler(UserStateChangeHandler handler) {
        changeUserStateHandler = handler;
    }

    public void setRecieveMessageHandler(Consumer<Message> handler) {
        messageHandler = handler;
    }

    public void setUserAddedHandler(Consumer<User> handler) {
        userHandler = handler;
    }

    //direction app -> server
    public void sendMessage(User receiver, String text) throws Exception {

    }

    // is it just to remove user ? chnged to handler
    public void setUserRemovedHandler(UserRemovedHandler handler) {
        removedHandler = handler;
    }

    public void sendMessageToApp() {
        User sender = new UserImpl("user1", 1, true, null);
        User receiver = new UserImpl("user2", 2, true, null);
        Date date = new Date();
        date.getDate();
        Message m = new MessageImpl(date, "text1", sender, receiver);
        messageHandler.accept(m);
    }

    public void addUser() {
        User u1 = new UserImpl("user1", 1, true, null);
        User u2 = new UserImpl("user2", 2, true, null);
        userHandler.accept(u1);
        userHandler.accept(u2);
    }

    public void changeState() {
        User u2 = new UserImpl("user2", 2, false, null);
        changeUserStateHandler.handle(2, u2);
    }

    public void removeUser() {
        removedHandler.handleremove(2);
    }


}
