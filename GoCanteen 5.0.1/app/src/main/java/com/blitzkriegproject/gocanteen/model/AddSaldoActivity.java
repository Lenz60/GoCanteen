package com.blitzkriegproject.gocanteen.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.blitzkriegproject.gocanteen.R;
import com.blitzkriegproject.gocanteen.TopupVeriftoBottomNav;
import com.blitzkriegproject.gocanteen.view.BottomNavbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AddSaldoActivity extends AppCompatActivity {

    ImageView ImgBack;
    Button BtnAddSaldo;
    ProgressBar progressBar;
    EditText EditTextCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_saldo);

        ImgBack = (ImageView) findViewById(R.id.ImgBack);
        EditTextCode = (EditText) findViewById(R.id.EdtxAddSaldo);
        BtnAddSaldo = (Button) findViewById(R.id.BtnAddSaldo);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        ImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddSaldoActivity.this, BottomNavbar.class);
                startActivity(intent);
                finish();
            }
        });

        BtnAddSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topup();
            }
        });


    }

    private void topup() {
        progressBar.setVisibility(View.VISIBLE);

        User user = SharedPrefmanager.getInstance(this).getUser();
        final String code = EditTextCode.getText().toString();
        final Integer id = user.getId();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_TOPUP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);

                        try {
                            //convert response to json object
                            JSONObject obj = new JSONObject(response);

                            //if no error in response
                            if (!obj.getBoolean("error")) {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

//                                //getting the user from the response
//                                JSONObject userJson = obj.getJSONObject("user");
//
//                                User user = new User(
//                                        userJson.getInt("id"),
//                                        userJson.getString("name"),
//                                        userJson.getString("email"),
//                                        userJson.getString("saldo")
//                                );
//
//                                //storing the user in shared preferences
//                                SharedPrefmanager.getInstance(getApplicationContext()).userLogin(user);

                                //starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), TopupVeriftoBottomNav.class));
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
                params.put("code", code);
                params.put("id", id.toString());
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

    }
}
