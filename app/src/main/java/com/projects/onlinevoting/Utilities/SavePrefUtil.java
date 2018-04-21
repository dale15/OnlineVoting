package com.projects.onlinevoting.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SavePrefUtil {

    public static void saveBool(String tag, Boolean value, Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putBoolean(tag, value);
        prefsEditor.commit();
    }

    public static Boolean getBool(String tag, Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return appSharedPrefs.getBoolean(tag,false);
    }

    public static void saveString(String tag, String value, Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putString(tag, value);
        prefsEditor.commit();
    }

    public static String getString(String tag, String defValue, Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return appSharedPrefs.getString(tag,defValue);
    }

    public static void saveInt(String tag, int value, Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putInt(tag,value);
        prefsEditor.commit();
    }

    public static void saveLong(String tag, Long value, Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor prefsEditor = appSharedPrefs.edit();
        prefsEditor.putLong(tag,value);
        prefsEditor.commit();
    }

    public static Boolean prefsContains(String tag, Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return appSharedPrefs.contains(tag);
    }

    public static int getInt(String tag, int defValue, Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return appSharedPrefs.getInt(tag,defValue);
    }

    public static Long getLong(String tag, Long defValue, Context context){
        SharedPreferences appSharedPrefs = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        return appSharedPrefs.getLong(tag,defValue);
    }

}
