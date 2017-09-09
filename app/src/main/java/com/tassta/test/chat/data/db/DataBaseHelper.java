package com.tassta.test.chat.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tassta.test.chat.utils.Constants;

/**
 * Created by user on 07.09.2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DataBaseHelper.class.getSimpleName();
    private static DataBaseHelper sInstance;

    public static synchronized DataBaseHelper getInstance(Context context) {
        Log.e(LOG_TAG, "getInstance");
        if (sInstance == null) {
            sInstance = new DataBaseHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    public void onCreate(SQLiteDatabase db) {
        //create db
        Log.e(LOG_TAG, "--- onCreate database ---");
        // create table with a fields

        db.execSQL("create table " + Constants.DATABASE_TABLE_USERS + " ("
                // + Constants.ID + " " + "integer primary key autoincrement,"
                + Constants.USER_NAME + ", "
                + Constants.USER_ID + " integer primary key, "
                + Constants.USER_STATUS + " integer "
                + ");");

        db.execSQL("create table " + Constants.DATABASE_TABLE_MESSAGES + " ("
                + Constants.ID + " " + "integer primary key autoincrement,"
                + Constants.MESSAGE_TEXT + " , "
                + Constants.RECEIVER_ID + " integer , "
                + Constants.SENDER_ID + " integer  ,"
                + Constants.IS_NEW + " integer ,"
                + Constants.DATE + "  "
                + ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    private DataBaseHelper(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
    }
}
