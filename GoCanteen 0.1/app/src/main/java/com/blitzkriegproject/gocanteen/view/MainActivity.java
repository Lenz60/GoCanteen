package com.blitzkriegproject.gocanteen.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blitzkriegproject.gocanteen.HomeActivity;
import com.blitzkriegproject.gocanteen.R;
import com.blitzkriegproject.gocanteen.model.AppVar;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText ETUsername;
    private EditText ETPassword;
    private Context context;
    private AppCompatButton ButtonLogin;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;

        pDialog = new ProgressDialog(context);
        ETUsername = (EditText) findViewById(R.id.idInputUsername);
        ETPassword = (EditText) findViewById(R.id.idInputPassword);

        ButtonLogin = (AppCompatButton) findViewById(R.id.idLoginButton);

        ButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                login();
            }
        });
    }
    private void login(){
        //getting value from edittext
        final String username = ETUsername.getText().toString().trim();
        final String password = ETPassword.getText().toString().trim();
        pDialog.setMessage("Login Process...");
        showDialog();

        //string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppVar.LOGIN_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //if success
                if (response.contains(AppVar.LOGIN_SUCCESS)) {
                    hideDialog();
                    gotoCourseActivity();
                } else {
                    hideDialog();
                    //displaying error message on toast
                    Toast.makeText(context, "Invalid username or password", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Handle Error
                hideDialog();
                Toast.makeText(context,"The Server unreachable", Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //adding parameters to request
                params.put(AppVar.KEY_USERNAME, username);
                params.put(AppVar.KEY_PASSWORD, password);

                //returning parameters
                return params;
            }
        };

        //adding the string request to the  queue
        Volley.newRequestQueue(this).add(stringRequest);
    }

    private void gotoCourseActivity(){
        Intent intent = new Intent(context, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void showDialog(){
        if(!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog(){
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
