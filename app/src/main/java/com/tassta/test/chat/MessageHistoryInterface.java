package com.tassta.test.chat;

/**
 * Created by user on 07.09.2017.
 */

public interface MessageHistoryInterface {

    void addMessage(Message message);
    void removeMessage(int pos);
    int getSize();

}
