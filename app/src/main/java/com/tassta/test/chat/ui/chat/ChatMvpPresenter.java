package com.tassta.test.chat.ui.chat;

/**
 * Created by user on 08.09.2017.
 */

public interface ChatMvpPresenter<V extends ChatMvpView> {
    void onAttached(V v);
    void sendMessage(int receiverId, String text);
    void refreshList();
}
