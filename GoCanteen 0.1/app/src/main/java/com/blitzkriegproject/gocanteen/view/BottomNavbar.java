package com.blitzkriegproject.gocanteen.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.blitzkriegproject.gocanteen.CartFragment;
import com.blitzkriegproject.gocanteen.HomeFragment;
import com.blitzkriegproject.gocanteen.NotificationFragment;
import com.blitzkriegproject.gocanteen.ProfileFragment;
import com.blitzkriegproject.gocanteen.R;
import com.blitzkriegproject.gocanteen.TokoFragment;
import com.blitzkriegproject.gocanteen.model.Profile;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class BottomNavbar extends AppCompatActivity {

    private ActionBar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navbar);

        toolbar = getSupportActionBar();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //default fragment
        loadFragment(new HomeFragment());

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



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
