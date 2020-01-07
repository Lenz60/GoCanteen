package com.blitzkriegproject.gocanteen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.blitzkriegproject.gocanteen.model.SharedPrefmanager;
import com.blitzkriegproject.gocanteen.model.TopUpVerif;
import com.blitzkriegproject.gocanteen.view.BottomNavbar;
import com.blitzkriegproject.gocanteen.view.Splash;

public class blank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank);

        //SharedPrefmanager.getInstance(getApplicationContext()).clear(user);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(blank.this, TopUpVerif.class);
                startActivity(intent);
                finish();
            }
        },1000);
    }
}
