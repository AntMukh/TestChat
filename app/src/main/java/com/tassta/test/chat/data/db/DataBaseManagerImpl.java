package com.tassta.test.chat.data.db;

import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.tassta.test.chat.R;
import com.tassta.test.chat.User;
import com.tassta.test.chat.UserImpl;
import com.tassta.test.chat.data.DataManagerImpl;
import com.tassta.test.chat.model.SimpleMessage;
import com.tassta.test.chat.model.SimpleMessageImpl;
import com.tassta.test.chat.utils.Constants;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by user on 07.09.2017.
 */

public class DataBaseManagerImpl implements DataBaseManager {

    DataBaseHelper dbHelper;
    private static DataBaseManagerImpl sInstance = null;

    public static final String LOG_TAG = DataManagerImpl.class.getSimpleName();

    private DataBaseManagerImpl(Context context) {

        dbHelper = DataBaseHelper.getInstance(context);
    }

    public List<User> getAllUsersFromDb() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<User> list = new LinkedList<>();
        Cursor c;
        try {
            c = db.query(Constants.DATABASE_TABLE_USERS, null, null, null, null, null, null);
        } catch (Error e) {
            Log.e(LOG_TAG, e.toString());
            return null;
        }
        c.moveToFirst();
        list.clear();
        for (int i = 0; i < c.getCount(); i++) {
            String name = c.getString(c.getColumnIndex(Constants.USER_NAME));
            int id = c.getInt(c.getColumnIndex(Constants.USER_ID));
            int status = c.getInt(c.getColumnIndex(Constants.USER_STATUS));
            boolean isOnline;
            if (status == 0) {
                isOnline = false;
            } else {
                isOnline = true;
            }
            User user = new UserImpl(name, id, isOnline, null);
            list.add(user);
            c.moveToNext();
        }
        Log.e(LOG_TAG, list.toString());
        return list;
    }

    public List<SimpleMessage> getAllMessagesfromDb() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        List<SimpleMessage> simpleList = new LinkedList<>();
        Cursor c;
        try {
            c = db.query(Constants.DATABASE_TABLE_MESSAGES, null, null, null, null, null, null);
        } catch (Error e) {
            Log.e(LOG_TAG, e.toString());
            return null;
        }
        c.moveToFirst();
        simpleList.clear();
        for (int i = 0; i < c.getCount(); i++) {
            String mesageText = c.getString(c.getColumnIndex(Constants.MESSAGE_TEXT));
            int receiverId = c.getInt(c.getColumnIndex(Constants.RECEIVER_ID));
            int senderId = c.getInt(c.getColumnIndex(Constants.SENDER_ID));
            int isNew = c.getInt(c.getColumnIndex(Constants.IS_NEW));
            String date = c.getString(c.getColumnIndex(Constants.DATE));
            SimpleMessage simpleMessage = new SimpleMessageImpl(mesageText, receiverId, senderId, isNew, date);
            simpleList.add(simpleMessage);
            c.moveToNext();
        }
        return simpleList;
    }

    public void addMessage(SimpleMessage message) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(Constants.DATE, message.getDate().toString());
        cv.put(Constants.MESSAGE_TEXT, message.getMesageText());
        cv.put(Constants.RECEIVER_ID, message.getReceiverId());
        cv.put(Constants.SENDER_ID, message.getSenderId());
        cv.put(Constants.IS_NEW, message.getIsNew());
        db.insert(Constants.DATABASE_TABLE_MESSAGES, null, cv);
        db.close();
    }

    public void addUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Constants.USER_NAME, user.getName());
        cv.put(Constants.USER_ID, user.getId());
        cv.put(Constants.USER_STATUS, user.isOnline());
        db.insert(Constants.DATABASE_TABLE_USERS, null, cv);
        db.close();
    }

    public void updateMessagesDb(List<SimpleMessage> messages) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int clearCount = db.delete(Constants.DATABASE_TABLE_MESSAGES, null, null);
        db.close();
        Log.d(LOG_TAG, "deleted rows  " + clearCount);
        for (SimpleMessage sm : messages
                ) {
            addMessage(sm);
        }
    }

    public static synchronized DataBaseManagerImpl getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DataBaseManagerImpl(context.getApplicationContext());
        }
        return sInstance;
    }


}
