package com.blitzkriegproject.gocanteen.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.blitzkriegproject.gocanteen.R;
import com.blitzkriegproject.gocanteen.view.BottomNavbar;

public class AddSaldoActivity extends AppCompatActivity {

    ImageView ImgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_saldo);

        ImgBack = (ImageView) findViewById(R.id.ImgBack);

        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSaldoActivity.this, BottomNavbar.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
