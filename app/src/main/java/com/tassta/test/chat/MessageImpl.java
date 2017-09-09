package com.tassta.test.chat;

import java.util.Date;

/**
 * Created by user on 07.09.2017.
 */

public class MessageImpl implements  Message {

    private Date date;
    private String text;
    private com.tassta.test.chat.User sender;
    private com.tassta.test.chat.User receiver;
    //false by default.
    private boolean isNew;

    public MessageImpl(Date date,
                       String text,
                       com.tassta.test.chat.User sender,
                       com.tassta.test.chat.User receiver
                       ){
        this.date = date;
        this.text = text;
        this.sender = sender;
        this.receiver = receiver;

    }

    public Date getDate(){
        return date;
    };
    public String getText(){
        return text;
    }
    public com.tassta.test.chat.User getSender(){
        return sender;
    }
    public com.tassta.test.chat.User getReceiver(){
        return receiver;
    }
}
