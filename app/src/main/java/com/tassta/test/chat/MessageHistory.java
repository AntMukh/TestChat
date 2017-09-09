package com.tassta.test.chat;

import android.util.Log;

import java.util.List;

/**
 * Message history is basically a list of messages.
 */
public abstract class MessageHistory
{
    public static final String LOG_TAG =MessageHistory.class.getSimpleName();

    protected List<Message> messages;

    protected List<Message> getMessages(){
        return messages;
    }
}
