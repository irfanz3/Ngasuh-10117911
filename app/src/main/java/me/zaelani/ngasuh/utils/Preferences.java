package me.zaelani.ngasuh.utils;

import android.content.Context;
import android.content.SharedPreferences;

import me.zaelani.ngasuh.model.UserModel;

public class Preferences {


    private static final String PREF_SESSION = "me.zaelani.ngasuh.pref";

    private final static String REGISTER_USERNAME = "REGISTER_USERNAME";
    private final static String REGISTER_PASSWORD = "REGISTER_PASSWORD";


    public Preferences() {

    }

    public static void setUserPreferences(Context context, UserModel userModel) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(REGISTER_USERNAME, userModel.getUsername());
        editor.putString(REGISTER_PASSWORD, userModel.getPassword());
        editor.apply();
    }

    public static String getRegisteredUser(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(REGISTER_USERNAME, UtilStatic.DEFAULT_STRING);
    }

    public static String getRegisteredPassword(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        return preferences.getString(REGISTER_PASSWORD, UtilStatic.DEFAULT_STRING);
    }

    public static boolean getLoggedInStatus(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        String username = preferences.getString(REGISTER_USERNAME, UtilStatic.DEFAULT_STRING);
        return !username.equals(UtilStatic.DEFAULT_STRING);
    }


    public static void setLogout(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREF_SESSION, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove(REGISTER_USERNAME);
        editor.apply();
    }

}
