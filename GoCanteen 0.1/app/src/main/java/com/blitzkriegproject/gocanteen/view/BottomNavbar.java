package com.blitzkriegproject.gocanteen.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blitzkriegproject.gocanteen.CartFragment;
import com.blitzkriegproject.gocanteen.HomeFragment;
import com.blitzkriegproject.gocanteen.NotificationFragment;
import com.blitzkriegproject.gocanteen.ProfileFragment;
import com.blitzkriegproject.gocanteen.R;
import com.blitzkriegproject.gocanteen.TokoFragment;
import com.blitzkriegproject.gocanteen.model.Login;
import com.blitzkriegproject.gocanteen.model.Profile;
import com.blitzkriegproject.gocanteen.model.SharedPrefmanager;
import com.blitzkriegproject.gocanteen.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class BottomNavbar extends AppCompatActivity {

    //declaration
    ImageView logoutIcon;
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navbar);




        navigation = (BottomNavigationView) findViewById(R.id.navigation);
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

                case R.id.navigation_cart :
                    fragment = new CartFragment();
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
