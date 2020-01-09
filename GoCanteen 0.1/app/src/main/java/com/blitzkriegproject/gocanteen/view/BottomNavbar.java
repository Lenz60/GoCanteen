package com.blitzkriegproject.gocanteen.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.blitzkriegproject.gocanteen.CartFragment;
import com.blitzkriegproject.gocanteen.HomeFragment;
import com.blitzkriegproject.gocanteen.NotificationFragment;
import com.blitzkriegproject.gocanteen.ProfileFragment;
import com.blitzkriegproject.gocanteen.R;
import com.blitzkriegproject.gocanteen.TokoFragment;
import com.blitzkriegproject.gocanteen.model.Menu.Bakso;
import com.blitzkriegproject.gocanteen.model.Login;
import com.blitzkriegproject.gocanteen.model.Menu.Magelangan;
import com.blitzkriegproject.gocanteen.model.Menu.Mieayam;
import com.blitzkriegproject.gocanteen.model.NotificationOrder.OrderNotif;
import com.blitzkriegproject.gocanteen.model.SharedPrefmanager;
import com.blitzkriegproject.gocanteen.model.URLs;
import com.blitzkriegproject.gocanteen.model.User;
import com.blitzkriegproject.gocanteen.model.VolleySingleton;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BottomNavbar extends AppCompatActivity {

    //declaration
    BottomNavigationView navigation;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navbar);




        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //default fragment
        loadFragment(new HomeFragment());

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        //Check if login
        if(!SharedPrefmanager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, Login.class));
        }



    }


    public void GetMieayamMenu() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_PRODUCT1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            //convert response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJsonM = obj.getJSONObject("mieayam");
//                                JSONObject userJsonB = obj.getJSONObject("bakso");
//                                JSONObject userJsonMG = obj.getJSONObject("magelangan");

                                Mieayam mieayam = new Mieayam(
                                        userJsonM.getInt("id"),
                                        userJsonM.getInt("id_shop"),
                                        userJsonM.getInt("price"),
                                        userJsonM.getInt("stock"),
                                        userJsonM.getInt("total_sold"),
                                        userJsonM.getString("name"),
                                        userJsonM.getString("category")
                                );

//                                Bakso bakso = new Bakso(
//                                        userJsonB.getInt("id"),
//                                        userJsonB.getInt("id_shop"),
//                                        userJsonB.getInt("price"),
//                                        userJsonB.getInt("stock"),
//                                        userJsonB.getInt("total_sold"),
//                                        userJsonB.getString("name"),
//                                        userJsonB.getString("category")
//                                );
//
//                                Magelangan magelangan = new Magelangan(
//                                        userJsonMG.getInt("id"),
//                                        userJsonMG.getInt("id_shop"),
//                                        userJsonMG.getInt("price"),
//                                        userJsonMG.getInt("stock"),
//                                        userJsonMG.getInt("total_sold"),
//                                        userJsonMG.getString("name"),
//                                        userJsonMG.getString("category")
//                                );

                                //storing the user in shared preferences
                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuM(mieayam);
//                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuB(bakso);
//                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuMG(magelangan);

                                //starting the profile activity
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }) {
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void GetBaksoMenu() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_PRODUCT2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            //convert response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                //JSONObject userJsonM = obj.getJSONObject("mieayam");
                                JSONObject userJsonB = obj.getJSONObject("bakso");
//                                JSONObject userJsonMG = obj.getJSONObject("magelangan");

//                                Mieayam mieayam = new Mieayam(
//                                        userJsonM.getInt("id"),
//                                        userJsonM.getInt("id_shop"),
//                                        userJsonM.getInt("price"),
//                                        userJsonM.getInt("stock"),
//                                        userJsonM.getInt("total_sold"),
//                                        userJsonM.getString("name"),
//                                        userJsonM.getString("category")
//                                );

                                Bakso bakso = new Bakso(
                                        userJsonB.getInt("id"),
                                        userJsonB.getInt("id_shop"),
                                        userJsonB.getInt("price"),
                                        userJsonB.getInt("stock"),
                                        userJsonB.getInt("total_sold"),
                                        userJsonB.getString("name"),
                                        userJsonB.getString("category")
                                );

//                                Magelangan magelangan = new Magelangan(
//                                        userJsonMG.getInt("id"),
//                                        userJsonMG.getInt("id_shop"),
//                                        userJsonMG.getInt("price"),
//                                        userJsonMG.getInt("stock"),
//                                        userJsonMG.getInt("total_sold"),
//                                        userJsonMG.getString("name"),
//                                        userJsonMG.getString("category")
//                                );

                                //storing the user in shared preferences
//                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuM(mieayam);
                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuB(bakso);
//                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuMG(magelangan);

                                //starting the profile activity
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }) {
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void GetMagelanganMenu() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_PRODUCT3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            //convert response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                //Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
//                                JSONObject userJsonM = obj.getJSONObject("mieayam");
//                                JSONObject userJsonB = obj.getJSONObject("bakso");
                                JSONObject userJsonMG = obj.getJSONObject("magelangan");

//                                Mieayam mieayam = new Mieayam(
//                                        userJsonM.getInt("id"),
//                                        userJsonM.getInt("id_shop"),
//                                        userJsonM.getInt("price"),
//                                        userJsonM.getInt("stock"),
//                                        userJsonM.getInt("total_sold"),
//                                        userJsonM.getString("name"),
//                                        userJsonM.getString("category")
//                                );

//                                Bakso bakso = new Bakso(
//                                        userJsonB.getInt("id"),
//                                        userJsonB.getInt("id_shop"),
//                                        userJsonB.getInt("price"),
//                                        userJsonB.getInt("stock"),
//                                        userJsonB.getInt("total_sold"),
//                                        userJsonB.getString("name"),
//                                        userJsonB.getString("category")
//                                );

                                Magelangan magelangan = new Magelangan(
                                        userJsonMG.getInt("id"),
                                        userJsonMG.getInt("id_shop"),
                                        userJsonMG.getInt("price"),
                                        userJsonMG.getInt("stock"),
                                        userJsonMG.getInt("total_sold"),
                                        userJsonMG.getString("name"),
                                        userJsonMG.getString("category")
                                );

                                //storing the user in shared preferences
//                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuM(mieayam);
//                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuB(bakso);
                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuMG(magelangan);

                                //starting the profile activity
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }) {
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void GetNotification() {
        User user = SharedPrefmanager.getInstance(this).getUser();
        final Integer id = user.getId();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_NOTIF,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try {
                            //convert response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                //getting the user from the response
                                JSONObject userJsonM = obj.getJSONObject("orderNotification");

//                                "id":13,"name_user":"Rafly
//                                Andrian","name_product":"Paket Mieayam & Esteh","place":"Basement
//                                5","seat_number":5,"quantity":2,"total_price":24000,"status":"Menunggu"}

                                OrderNotif notif = new OrderNotif(
                                        userJsonM.getInt("id"),
                                        userJsonM.getInt("quantity"),
                                        userJsonM.getInt("total_price"),
                                        userJsonM.getString("name_user"),
                                        userJsonM.getString("name_product"),
                                        userJsonM.getString("place"),
                                        userJsonM.getString("seat_number"),
                                        userJsonM.getString("status")
                                );

                                //storing the user in shared preferences
                                SharedPrefmanager.getInstance(getApplicationContext()).storeNotif(notif);
//                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuB(bakso);
//                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuMG(magelangan);

                                //starting the profile activity
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                params.put("id", id.toString());
                return params;
            }

        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private  BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;
            switch (menuItem.getItemId()){
                case R.id.navigation_notification:
                    fragment = new NotificationFragment();
                    break;

                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;

                case R.id.navigation_toko :
                    fragment = new TokoFragment();
                    break;


                case R.id.navigation_profile :
                    fragment = new ProfileFragment();
                    break;
                    
            }
            return loadFragment(fragment);
        }
    };

    private boolean loadFragment(Fragment fragment) {
        //load fragment
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();
            return true;
        }
        return false;
    }

}
