package com.blitzkriegproject.gocanteen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.blitzkriegproject.gocanteen.view.BottomNavbar;

public class TopupVeriftoBottomNav extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_verifto_bottom_nav);
        Toast.makeText(getApplicationContext(), "Silahkan Login Ulang",Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(TopupVeriftoBottomNav.this, BottomNavbar.class);
        startActivity(intent);
        finish();
    }
}
