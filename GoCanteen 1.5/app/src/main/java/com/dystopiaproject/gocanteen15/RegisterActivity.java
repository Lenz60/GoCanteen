package com.dystopiaproject.gocanteen15;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.dystopiaproject.gocanteen15.apihelper.BaseApiService;
import com.dystopiaproject.gocanteen15.apihelper.UtilsApi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    EditText etNim;
    EditText etNama;
    EditText etEmail;
    EditText etPassword;
    RadioGroup rdgroupGender;
    Button btnRegister;
    ProgressBar loading;

    Context mContext;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mContext = this;
            mApiService = UtilsApi.getAPIService();

            initComponents();
    }

    private void initComponents() {
        etNim = (EditText) findViewById(R.id.etNim);
        etNama = (EditText) findViewById(R.id.etNama);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        rdgroupGender = (RadioGroup) findViewById(R.id.rdgroupGender);

        loading = (ProgressBar) findViewById(R.id.ProgressBar);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setVisibility(View.VISIBLE);
                requestRegister();
            }
        });
    }

    private void requestRegister() {
        //declarasi gender radiogroup
        RadioButton gender = ((RadioButton) findViewById(rdgroupGender.getCheckedRadioButtonId()));

        mApiService.registerRequest(etNim.getText().toString(),
                etNama.getText().toString(),
                etEmail.getText().toString(),
                etPassword.getText().toString(),
                gender.getText().toString())
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            Log.i("debug", "onResponse : BERHASIL");
                            loading.setVisibility(View.GONE);

                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("error").equals("false")){
                                    Toast.makeText(mContext, "BERHASIL REGISTRASI", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(mContext, LoginActivity.class));
                                }
                                else {
                                    String error_message = jsonRESULTS.getString("error_msg");
                                    Toast.makeText(mContext, error_message, Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            Log.i("debug", "onResponse : GA BERHASIL");
                            loading.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.e("debug", "onFailure: ERROR > " + t.getMessage());
                        Toast.makeText(mContext, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                    }
                });
    }
}
