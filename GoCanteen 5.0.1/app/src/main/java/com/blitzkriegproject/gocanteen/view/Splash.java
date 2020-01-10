package com.blitzkriegproject.gocanteen.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.blitzkriegproject.gocanteen.R;
import com.blitzkriegproject.gocanteen.model.MainActivity;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, BottomNavbar.class);
                startActivity(intent);
                finish();
            }
        },3000);



    }
}
