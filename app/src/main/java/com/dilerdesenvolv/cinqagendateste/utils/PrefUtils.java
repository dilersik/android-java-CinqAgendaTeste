package com.dilerdesenvolv.cinqagendateste.utils;

import android.content.SharedPreferences;
import com.dilerdesenvolv.cinqagendateste.MyApplication;

public class PrefUtils {

    private static SharedPreferences INSTANCE;
    private final static String PREF_ID = "MyPrefCAT";

    private static SharedPreferences getInstance() {
        if (INSTANCE == null) {
            INSTANCE = MyApplication.getInstance().getApplicationContext().getSharedPreferences(PREF_ID, 0);
        }
        return INSTANCE;
    }

    public static Integer getInt(String flag) {
        return getInstance().getInt(flag, 0);
    }

    public static void setInt(String flag, Integer value) {
        getInstance().edit().putInt(flag, value).apply();
    }

    public static String getString(String flag) {
        return getInstance().getString(flag, null);
    }

    public static void setString(String flag, String value) {
        getInstance().edit().putString(flag, value).apply();
    }

    public static void remove(String flag) {
        getInstance().edit().remove(flag).apply();
    }

}
