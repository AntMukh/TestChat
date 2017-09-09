package com.tassta.test.chat;

import android.app.Application;

import com.tassta.test.chat.data.DataManager;
import com.tassta.test.chat.data.DataManagerImpl;

/**
 * Created by user on 07.09.2017.
 */

public class ChatApp extends Application {

    public static final String LOG_TAG = ChatApp.class.getSimpleName();
    DataManager mDataManager;
        @Override
        public void onCreate() {
            super.onCreate();
            mDataManager = new DataManagerImpl(getApplicationContext());
    }
    public DataManager getDataManager(){
        return mDataManager;
    }
}
