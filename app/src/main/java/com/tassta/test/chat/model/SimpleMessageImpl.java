package com.tassta.test.chat.model;

/**
 * Created by user on 08.09.2017.
 */

public class SimpleMessageImpl implements SimpleMessage {

    String mesageText;
    int receiverId;
    int senderId;
    int isNew;
    String date;

    public SimpleMessageImpl(String mesageText, int receiverId, int senderId, int isNew, String date) {
        this.mesageText = mesageText;
        this.receiverId = receiverId;
        this.senderId = senderId;
        this.isNew = isNew;
        this.date = date;
    }

    public String getMesageText() {
        return mesageText;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public int getIsNew() {
        return isNew;
    }

    public String getDate() {
        return date;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setIsNew(int newStatus) {
        isNew = 0;
    }


}

