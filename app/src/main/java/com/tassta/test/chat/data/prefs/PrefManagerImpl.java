package com.tassta.test.chat.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.tassta.test.chat.utils.Constants;

/**
 * Created by user on 07.09.2017.
 */

public class PrefManagerImpl implements PrefManager {

    public static final String LOG_TAG = PrefManagerImpl.class.getSimpleName();
    private static PrefManagerImpl sInstance = null;
    private final SharedPreferences mPrefs;

    public PrefManagerImpl(Context context,
                           String prefName
    ) {
        mPrefs = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }

    //@TODO
    // implement code upgrade support
    public boolean isFirstRun() {
        if (mPrefs.getBoolean(Constants.IS_FIRST_LAUNCH, true)) {
            Log.d(LOG_TAG, "first application launch");
            mPrefs.edit().putBoolean(Constants.IS_FIRST_LAUNCH, false).commit();
            return true;
        } else {
            return false;
        }
    }

    public static synchronized PrefManagerImpl getInstance(Context context, String prefName) {
        if (sInstance == null) {
            sInstance = new PrefManagerImpl(context, prefName);
        }
        return sInstance;
    }

}
