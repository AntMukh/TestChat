package com.tassta.test.chat.data.db;

import com.tassta.test.chat.User;
import com.tassta.test.chat.model.SimpleMessage;

import java.util.List;

/**
 * Created by user on 07.09.2017.
 */

public interface DataBaseManager {

    List<User> getAllUsersFromDb();

    //List<UserBundle> addMessageStatus(List<User> users);

    void addMessage(SimpleMessage message);

    void addUser(User user);

    List<SimpleMessage> getAllMessagesfromDb();

    public void updateMessagesDb(List<SimpleMessage> messages);

}
