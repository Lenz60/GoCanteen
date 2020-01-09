package com.blitzkriegproject.gocanteen.model;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.blitzkriegproject.gocanteen.model.Menu.Bakso;
import com.blitzkriegproject.gocanteen.model.Menu.Magelangan;
import com.blitzkriegproject.gocanteen.model.Menu.Mieayam;
import com.blitzkriegproject.gocanteen.model.NotificationOrder.OrderNotif;

public class SharedPrefmanager {

    //constants
    private static final String SHARED_PREF_NAME = "gocanteensharedpref";
    private static final String KEY_NAME = "keyname";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_SALDO = "saldo";
    private static final String KEY_ID = "keyid";
    private static final String KEY_CODE = "code";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_ID_PRODUCT= "idproduct";
    private static final String KEY_ID_SHOP = "idshop";
    private static final String KEY_NAME_MENU = "namemenu";
    private static final String KEY_PRICE = "price";
    private static final String KEY_CATEGORY = "category";
    private static final String KEY_STOCK = "stock";
    private static final String KEY_TOTAL_SOLD = "totalsold";

    private static final String KEY_ID_PRODUCT2= "idproduct2";
    private static final String KEY_ID_SHOP2 = "idshop2";
    private static final String KEY_NAME_MENU2 = "namemenu2";
    private static final String KEY_PRICE2 = "price2";
    private static final String KEY_CATEGORY2= "category2";
    private static final String KEY_STOCK2 = "stock2";
    private static final String KEY_TOTAL_SOLD2 = "totalsold2";

    private static final String KEY_ID_PRODUCT3= "idproduct3";
    private static final String KEY_ID_SHOP3 = "idshop3";
    private static final String KEY_NAME_MENU3 = "namemenu3";
    private static final String KEY_PRICE3 = "price3";
    private static final String KEY_CATEGORY3 = "category3";
    private static final String KEY_STOCK3 = "stock3";
    private static final String KEY_TOTAL_SOLD3 = "totalsold3";

    private static final String KEY_NOTIFID = "notifid";
    private static final String KEY_NOTIFQUANTITY = "notifquantity";
    private static final String KEY_NOTIFTOTAL_PRICE = "notiftotalprice";
    private static final String KEY_NOTIFNAME_USER = "notifnameuser";
    private static final String KEY_NOTIFNAME_PRODUCT = "notifnameproduct";
    private static final String KEY_NOTIFPLACE = "notifplace";
    private static final String KEY_NOTIFSEAT_NUMBER = "notifseatnumber";
    private static final String KEY_NOTIFSTATUS = "notifstatus";

//    Integer id, quantity, total_price;
//    String name_user, name_product, place, seat_number, status;

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
        editor.putString(KEY_NAME, user.getName());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_SALDO, user.getSaldo());
        editor.apply();
    }

    //store notif
    public void storeNotif(OrderNotif notif){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

//        private static final String KEY_NOTIFID = "notifid";
//        private static final String KEY_NOTIFQUANTITY = "notifquantity";
//        private static final String KEY_NOTIFTOTAL_PRICE = "notiftotalsold3";
//        private static final String KEY_NOTIFNAME_USER = "notifnameuser";
//        private static final String KEY_NOTIFNAME_PRODUCT = "notifnameproduct";
//        private static final String KEY_NOTIFPLACE = "notifplace";
//        private static final String KEY_NOTIFSEAT_NUMBER = "notifseatnumber";
//        private static final String KEY_NOTIFSTATUS = "notifstatus";
//
////    Integer id, quantity, total_price;
////    String name_user, name_product, place, seat_number, status;

        editor.putInt(KEY_NOTIFID, notif.getId());
        editor.putInt(KEY_NOTIFQUANTITY, notif.getQuantity());
        editor.putInt(KEY_NOTIFTOTAL_PRICE, notif.getTotal_price());
        editor.putString(KEY_NOTIFNAME_USER, notif.getName_user());
        editor.putString(KEY_NOTIFNAME_PRODUCT, notif.getName_product());
        editor.putString(KEY_NOTIFPLACE, notif.getPlace());
        editor.putString(KEY_NOTIFSEAT_NUMBER, notif.getSeat_number());
        editor.putString(KEY_NOTIFSTATUS, notif.getStatus());
        editor.apply();
    }

    //store menu to Mieayam
    public void storeMenuM(Mieayam mieayam){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID_PRODUCT, mieayam.getId());
        editor.putInt(KEY_ID_SHOP, mieayam.getId_shop());
        editor.putString(KEY_NAME_MENU, mieayam.getName());
        editor.putInt(KEY_PRICE, mieayam.getPrice());
        editor.putString(KEY_CATEGORY, mieayam.getCategory());
        editor.putInt(KEY_STOCK, mieayam.getStock());
        editor.putInt(KEY_TOTAL_SOLD, mieayam.getTotal_sold());
        editor.apply();
    }

    //store menu to Bakso
    public void storeMenuB(Bakso bakso){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID_PRODUCT2, bakso.getId());
        editor.putInt(KEY_ID_SHOP2, bakso.getId_shop());
        editor.putString(KEY_NAME_MENU2, bakso.getName());
        editor.putInt(KEY_PRICE2, bakso.getPrice());
        editor.putString(KEY_CATEGORY2, bakso.getCategory());
        editor.putInt(KEY_STOCK2, bakso.getStock());
        editor.putInt(KEY_TOTAL_SOLD2, bakso.getTotal_sold());
        editor.apply();
    }

    //store menu to mieayam
    public void storeMenuMG(Magelangan magelangan){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID_PRODUCT3, magelangan.getId());
        editor.putInt(KEY_ID_SHOP3, magelangan.getId_shop());
        editor.putString(KEY_NAME_MENU3, magelangan.getName());
        editor.putInt(KEY_PRICE3, magelangan.getPrice());
        editor.putString(KEY_CATEGORY3, magelangan.getCategory());
        editor.putInt(KEY_STOCK3, magelangan.getStock());
        editor.putInt(KEY_TOTAL_SOLD3, magelangan.getTotal_sold());
        editor.apply();
    }

    public void userVoucher(Voucher voucher){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, voucher.getId());
        editor.putString(KEY_AMOUNT, voucher.getAmount());
        editor.putString(KEY_CODE, voucher.getCode());
        editor.apply();
    }

    //check whether user is already logged in or not
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) !=null;
    }

    //will give the logged in user
    public User getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_NAME, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_SALDO, null)


        );
    }

    public Mieayam getMieayam(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  new Mieayam(
                sharedPreferences.getInt(KEY_ID_PRODUCT, -1),
                sharedPreferences.getInt(KEY_ID_SHOP, -1),
                sharedPreferences.getInt(KEY_PRICE, -1),
                sharedPreferences.getInt(KEY_STOCK, -1),
                sharedPreferences.getInt(KEY_TOTAL_SOLD, -1),
                sharedPreferences.getString(KEY_NAME_MENU, null),
                sharedPreferences.getString(KEY_CATEGORY, null)


        );
    }

    public Bakso getBakso(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  new Bakso(
                sharedPreferences.getInt(KEY_ID_PRODUCT2, -1),
                sharedPreferences.getInt(KEY_ID_SHOP2, -1),
                sharedPreferences.getInt(KEY_PRICE2, -1),
                sharedPreferences.getInt(KEY_STOCK2, -1),
                sharedPreferences.getInt(KEY_TOTAL_SOLD2, -1),
                sharedPreferences.getString(KEY_NAME_MENU2, null),
                sharedPreferences.getString(KEY_CATEGORY2, null)


        );
    }

    public Magelangan getMagelangan(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  new Magelangan(
                sharedPreferences.getInt(KEY_ID_PRODUCT3, 1),
                sharedPreferences.getInt(KEY_ID_SHOP3, 1),
                sharedPreferences.getInt(KEY_PRICE3, 1),
                sharedPreferences.getInt(KEY_STOCK3, 1),
                sharedPreferences.getInt(KEY_TOTAL_SOLD3, 1),
                sharedPreferences.getString(KEY_NAME_MENU3, null),
                sharedPreferences.getString(KEY_CATEGORY3, null)


        );
    }

    public OrderNotif getNotif(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return  new OrderNotif(

                //          editor.putInt(KEY_NOTIFID, notif.getId());
                //        editor.putInt(KEY_NOTIFQUANTITY, notif.getQuantity());
                //        editor.putInt(KEY_NOTIFTOTAL_PRICE, notif.getTotal_price());
                //        editor.putString(KEY_NOTIFNAME_USER, notif.getName_user());
                //        editor.putString(KEY_NOTIFNAME_PRODUCT, notif.getName_product());
                //        editor.putString(KEY_NOTIFPLACE, notif.getPlace());
                //        editor.putString(KEY_NOTIFSEAT_NUMBER, notif.getSeat_number());
                //        editor.putString(KEY_NOTIFSTATUS, notif.getStatus());

                sharedPreferences.getInt(KEY_NOTIFID, 1),
                sharedPreferences.getInt(KEY_NOTIFQUANTITY, 1),
                sharedPreferences.getInt(KEY_NOTIFTOTAL_PRICE, 1),
                sharedPreferences.getString(KEY_NOTIFNAME_USER, null),
                sharedPreferences.getString(KEY_NOTIFNAME_PRODUCT, null),
                sharedPreferences.getString(KEY_NOTIFPLACE, null),
                sharedPreferences.getString(KEY_NOTIFSEAT_NUMBER, null),
                sharedPreferences.getString(KEY_NOTIFSTATUS, null)


        );
    }



//    public void clear(){
//        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.remove(KEY_SALDO);
//        editor.apply();
//    }

    //Logout
    public void logout(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, Login.class));
    }

}
