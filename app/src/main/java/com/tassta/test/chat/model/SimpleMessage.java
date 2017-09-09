package com.tassta.test.chat.model;

/**
 * Created by user on 08.09.2017.
 */

public interface SimpleMessage {
    String getMesageText();

    int getReceiverId();

    int getIsNew();

    String getDate();

    int getSenderId();

    void setIsNew(int newStatus);

}
