package com.tassta.test.chat.data;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.tassta.test.chat.Consumer;
import com.tassta.test.chat.Message;
import com.tassta.test.chat.MessageHistoryImpl;
import com.tassta.test.chat.R;
import com.tassta.test.chat.User;
import com.tassta.test.chat.UserImpl;
import com.tassta.test.chat.data.db.DataBaseManager;
import com.tassta.test.chat.data.db.DataBaseManagerImpl;
import com.tassta.test.chat.data.prefs.PrefManager;
import com.tassta.test.chat.data.prefs.PrefManagerImpl;
import com.tassta.test.chat.model.SimpleMessage;
import com.tassta.test.chat.model.UserBundle;
import com.tassta.test.chat.model.UserBundleImpl;
import com.tassta.test.chat.noimpl.IoManagerImpl;
import com.tassta.test.chat.noimpl.IoManger;
import com.tassta.test.chat.ui.UserListMvpPresenter;
import com.tassta.test.chat.ui.chat.ChatMvpPresenter;
import com.tassta.test.chat.utils.Constants;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 07.09.2017.
 */

public class DataManagerImpl implements DataManager

{
    public static final String LOG_TAG = DataManagerImpl.class.getSimpleName();
    private final PrefManager mPrefManager;
    private final DataBaseManager mDataBaseManager;
    private Context cntxt;
    private IoManger mIoManager;
    private List<User> users;
    private List<SimpleMessage> simpleMessages;
    private MessageHistoryImpl mMessageHistoryImpl;
    private List<Integer> unreadedHashTable;
    private UserListMvpPresenter mUserListMvpPresenter;
    private ChatMvpPresenter mChatMvpPresenter;

    public Consumer<Message> getMessageConsumer() {
        return new Consumer<Message>() {
            public void accept(Message t) {
                acceptMessage(t);
            }
        };
    }

    public Consumer<User> getUserConsumer() {
        return new Consumer<User>() {
            public void accept(User t) {
                acceptUser(t);
            }
        };
    }

    public User getMyUser() {
        return new UserImpl("MyName", 0, true, null);
    }

    public DataManagerImpl(Context context) {
        cntxt = context;
        mPrefManager = PrefManagerImpl.getInstance(context, Constants.PREF_NAME);
        mDataBaseManager = DataBaseManagerImpl.getInstance(context);
        users = mDataBaseManager.getAllUsersFromDb();
        simpleMessages = mDataBaseManager.getAllMessagesfromDb();
        mMessageHistoryImpl = new MessageHistoryImpl();
        mIoManager = new IoManagerImpl();
        unreadedHashTable = new LinkedList<>();
    }

    public IoManger getIoManager() {
        return mIoManager;
    }

    public MessageHistoryImpl getMessageHistory() {

        return mMessageHistoryImpl;
    }

    public void registerUserListListener(UserListMvpPresenter v) {

        mUserListMvpPresenter = v;
    }

    public void registerChatListener(ChatMvpPresenter v) {

        mChatMvpPresenter = v;
    }

    //handle user State changed
    public void handleremove(int id) {
        Log.d(LOG_TAG, " Handle remove");
        removeUser(id);
        mUserListMvpPresenter.refreshList();
    }

    //handle user State changed
    public void handle(int id, User newValue) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                users.set(i, newValue);
            }
        }
        if (mUserListMvpPresenter != null)
            mUserListMvpPresenter.refreshList();
    }

    //incoming message
    public void acceptMessage(Message value) {
        sendNotification();
        mMessageHistoryImpl.addMessage(value);
        unreadedHashTable.add(value.hashCode());
        if (mChatMvpPresenter != null)
            mChatMvpPresenter.refreshList();
        if (mUserListMvpPresenter != null)
            mUserListMvpPresenter.refreshList();
    }

    private void acceptUser(User value) {
        boolean isExist = false;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == value.getId()) {
                isExist = true;
            }
        }
        if (!isExist) {
            users.add(value);
        }
        mUserListMvpPresenter.refreshList();
    }

    public void removeUser(int userId) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == userId) {
                users.remove(i);
            }
        }
    }

    public List<UserBundle> getUserBundles() {
        List<Message> m = getMessageHistory().getAllMessages();
        List<UserBundle> ubList = new LinkedList<>();
        for (int i = 0; i < users.size(); i++) {
            int foundUnreades = 0;
            for (int j = 0; j < m.size(); j++) {
                if (m.get(j).getSender().getId() == users.get(i).getId()
                        && unreadedHashTable.contains(m.get(j).hashCode())
                        ) {
                    foundUnreades = 1;
                }
            }
            UserBundle ub = new UserBundleImpl(users.get(i), foundUnreades);
            ubList.add(ub);
        }
        return ubList;
    }

    public List<String> getMessagesfromUserAndMe(int senderId) {
        List<Message> allMessages = getMessageHistory().getAllMessages();
        List<String> messages = new LinkedList<>();
        for (Message m : allMessages) {
            if (m.getSender().getId() == senderId
                    || ((m.getSender().getId() == 0)
                    && (m.getReceiver().getId() == senderId))) {
                messages.add(m.getText());
            }
        }
        return messages;
    }

    public User getUser(int userId) {
        for (int i = 0; i < users.size(); i++) {
           if (users.get(i).getId() == userId) {
                return users.get(i);
            }
        }
        return null;
    }


    public void unmarkMessagesFromUser(int senderId) {
        List<Message> m = getMessageHistory().getAllMessages();
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getSender().getId() == senderId) {
                unreadedHashTable.remove((Integer) m.get(i).hashCode());
            }
        }
    }

    private void sendNotification() {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(cntxt);
        mBuilder.setSmallIcon(R.drawable.ic_menu_send);
        NotificationManager mNotificationManager = (NotificationManager)
                cntxt.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(Constants.MESSAGE_NOTIF, mBuilder.build());
        Handler h = new Handler();
        long delayInMilliseconds = 3000;
        h.postDelayed(new Runnable() {
            public void run() {
                ((NotificationManager)
                        cntxt.getSystemService(Context.NOTIFICATION_SERVICE))
                        .cancel(Constants.MESSAGE_NOTIF);
            }
        }, delayInMilliseconds);

    }
}
