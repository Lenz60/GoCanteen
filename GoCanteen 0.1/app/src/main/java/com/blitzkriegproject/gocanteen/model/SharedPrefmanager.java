package com.blitzkriegproject.gocanteen.model;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.blitzkriegproject.gocanteen.Login;

public class SharedPrefmanager {

    //constants
    private static final String SHARED_PREF_NAME = "gocanteensharedpref";
    private static final String KEY_NIM = "keynim";
    private static final String KEY_NAMA = "keynama";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_GENDER = "keygender";
    private static final String KEY_ID = "keyid";

    private static SharedPrefmanager mInstance;
    private static Context mCtx;

    private  SharedPrefmanager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefmanager getInstance(Context context){
        if (mInstance == null){
            mInstance = new SharedPrefmanager(context);
        }
        return mInstance;
    }

    //let user login
    //store user data in shared preferences
    public void userLogin(User user){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_NIM, user.getNIM());
        editor.putString(KEY_NAMA, user.getNama());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_GENDER, user.getGender());
        editor.apply();
    }

    //check whether user is already logged in or not
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_NIM, null) !=null;
    }

    //will give the logged in user
    public User getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_NIM, null),
                sharedPreferences.getString(KEY_NAMA, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_GENDER, null)


        );
    }

    //Logout
    public void logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, Login.class));
    }

}
