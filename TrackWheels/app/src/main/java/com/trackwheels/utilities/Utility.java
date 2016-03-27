package com.trackwheels.utilities;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.kinvey.android.Client;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

/**
 * Created by shruthi on 26/3/16.
 */
public class Utility {
    public static Client client;

    public static String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

    public static String getFromSharedPreferences(Context ctx, String key){
        SharedPreferences sharedpreferences = ctx.getSharedPreferences(Constants.SharedPref.PREF_NAME, Context.MODE_PRIVATE);
        return sharedpreferences.getString(key, null);
    }

    public static void putToSharedPreference(Context ctx, String key, String value){
        SharedPreferences sharedpreferences = ctx.getSharedPreferences(Constants.SharedPref.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static boolean isUserSignedIn(Context ctx){
        if(getFromSharedPreferences(ctx, Constants.SharedPref.KEY_IS_SIGN_IN)!=null){
            return true;
        }
        return false;
    }
}
