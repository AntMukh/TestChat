package com.tassta.test.chat.data;

import com.tassta.test.chat.Message;
import com.tassta.test.chat.MessageHistory;
import com.tassta.test.chat.MessageHistoryInterface;
import com.tassta.test.chat.User;
import com.tassta.test.chat.UserRemovedHandler;
import com.tassta.test.chat.UserStateChangeHandler;
import com.tassta.test.chat.Consumer;
import com.tassta.test.chat.model.SimpleMessage;
import com.tassta.test.chat.model.UserBundle;
import com.tassta.test.chat.noimpl.IoManger;
import com.tassta.test.chat.ui.UserListMvpPresenter;
import com.tassta.test.chat.ui.chat.ChatMvpPresenter;

import java.util.List;

/**
 * Created by user on 07.09.2017.
 */

public interface DataManager  extends UserStateChangeHandler, UserRemovedHandler {
    List<UserBundle> getUserBundles();
    //void addUser(User user);
    User getUser(int iserId);
    //void addMessage(SimpleMessage message);
    void unmarkMessagesFromUser(int senderId);
    Consumer<Message> getMessageConsumer();
    Consumer<User> getUserConsumer();
    IoManger getIoManager();
    MessageHistoryInterface getMessageHistory();
    User getMyUser();
    void registerUserListListener(UserListMvpPresenter v);
    void registerChatListener(ChatMvpPresenter v);
    List<String> getMessagesfromUserAndMe(int senderId);

}
