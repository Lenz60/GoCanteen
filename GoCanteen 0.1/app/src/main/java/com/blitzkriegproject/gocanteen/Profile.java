package com.blitzkriegproject.gocanteen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blitzkriegproject.gocanteen.model.SharedPrefmanager;
import com.blitzkriegproject.gocanteen.model.User;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    TextView textViewid, textViewNIM, textViewNama, textViewEmail, textViewGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        //if user not logged in
        //starting login activity
        if(!SharedPrefmanager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(this, Login.class));

            textViewid = (TextView) findViewById(R.id.textViewId);
            textViewNIM = (TextView) findViewById(R.id.textViewNIM);
            textViewNama = (TextView) findViewById(R.id.editTextNama);
            textViewEmail = (TextView) findViewById(R.id.textViewEmail);
            textViewGender = (TextView) findViewById(R.id.textViewGender);


            //getting the current user
            User user = SharedPrefmanager.getInstance(this).getUser();

            //setting value to the textview
            textViewid.setText(String.valueOf(user.getId()));
            textViewNIM.setText(user.getNIM());
            textViewNama.setText(user.getNama());
            textViewEmail.setText(user.getEmail());
            textViewGender.setText(user.getGender());

            //when the user presses logout
            //calling logout method

            findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                    SharedPrefmanager.getInstance(getApplicationContext()).logout();
                }
            });
        }
    }
}
