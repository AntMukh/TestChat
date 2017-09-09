package com.tassta.test.chat;

import android.util.Log;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 07.09.2017.
 */

public class MessageHistoryImpl extends MessageHistory implements MessageHistoryInterface{
   public MessageHistoryImpl(){
        super();
        messages = new LinkedList<Message>();
    }
    public Message getMessage(int position){
        Message message = null;
        try{
            message =  getMessages().get(position);}
        catch (Exception e){
            //handle exception
            Log.e(LOG_TAG, e.toString());
        }
        return message;
    }

    public void addMessage(Message message){
        try{
            getMessages().add(message);
        }catch (Exception e){
            //handle exception
            Log.e(LOG_TAG, e.toString());
        }
        }

    public void removeMessage(int position){
       // Message message = null;
        try{
            getMessages().remove(position);
        }catch (Exception e){
            //handle exception
            Log.e(LOG_TAG, e.toString());
        }
    }

    public List<Message> getAllMessages(){
        Log.e(LOG_TAG, " messages= "+ getMessages());
        return getMessages();
    }

    public int getSize(){
        return getMessages().size();
    }
}
