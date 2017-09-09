package com.tassta.test.chat.ui.chat;

import android.util.Log;

import com.tassta.test.chat.Message;
import com.tassta.test.chat.MessageImpl;
import com.tassta.test.chat.User;
import com.tassta.test.chat.data.DataManager;

import java.util.Date;

/**
 * Created by user on 08.09.2017.
 */

public class ChatPresenterImpl<V extends ChatMvpView> implements ChatMvpPresenter<V> {
    public final String LOG_TAG = ChatPresenterImpl.class.getSimpleName();
    DataManager mDataManager;
    ChatMvpView mChatMvpView;


    int senderId;

    ChatPresenterImpl(DataManager dataManager, int senderId) {
        mDataManager = dataManager;
        this.senderId = senderId;
    }

    public void sendMessage(int receiverId, String text) {
        User receiver = mDataManager.getUser(this.senderId);
        Log.e(LOG_TAG, "receiver =" + receiver);
        User sender = mDataManager.getMyUser();
        Log.e(LOG_TAG, "sender =" + sender);

        Date date = new Date();
        date.getDate();
        Message newMessage = new MessageImpl(date, text, sender, receiver);
        mDataManager.getMessageHistory().addMessage(newMessage);
        try {
            mDataManager.getIoManager().sendMessage(receiver, text);
        } catch (Exception e) {
            Log.e(LOG_TAG, e.toString());
        }
        refreshList();
    }

    public void onAttached(V v) {
        mChatMvpView = v;
        mDataManager.unmarkMessagesFromUser(senderId);
        mDataManager.registerChatListener(this);
        refreshList();
    }

    public void refreshList() {
        // combine messages from user and messages to that user
        //  List<String> comList = new LinkedList<>();
        // comList.addAll(mDataManager.getMessagesfromUserAndMe(senderId));
        // comList.addAll(mDataManager.getMessagesfromUser(0));
        mChatMvpView.setAdapter(mDataManager.getMessagesfromUserAndMe(senderId));
    }
}
