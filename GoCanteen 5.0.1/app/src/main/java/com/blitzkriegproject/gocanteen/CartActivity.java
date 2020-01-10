package com.blitzkriegproject.gocanteen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.blitzkriegproject.gocanteen.model.SharedPrefmanager;
import com.blitzkriegproject.gocanteen.model.URLs;
import com.blitzkriegproject.gocanteen.model.User;
import com.blitzkriegproject.gocanteen.model.VolleySingleton;
import com.blitzkriegproject.gocanteen.view.BottomNavbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;

public class CartActivity extends AppCompatActivity {

    Spinner SpinnerTempat, SpinnerKursi1, SpinnerKursi2;
    TextView TxtvTempat, TxtvKursi;
    TextView TxtvFoodNameCart1, TxtvPriceCart1;
    TextView TxtvFoodNameCart2, TxtvPriceCart2;
    TextView TxtvFoodNameCart3, TxtvPriceCart3;
    ImageView ImgFood1, ImgFood2, ImgFood3;
    Button BtnAddItemCart1, BtnReduceItemCart1;
    Button BtnAddItemCart2, BtnReduceItemCart2;
    Button BtnAddItemCart3, BtnReduceItemCart3;
    Button BtnOrder;
    EditText EdtxItem1, EdtxItem2, EdtxItem3;

    //fetched value
    Integer setIDFood;
    String FoodName;
    String Price;

    //spinner value
    String Tempat;
    String Kursi1;
    String Kursi2;

    //Price Total
    Integer Total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        TxtvKursi = (TextView) findViewById(R.id.TxtvKursi);
        TxtvTempat = (TextView) findViewById(R.id.TxtvTempat);
        TxtvFoodNameCart1 = (TextView) findViewById(R.id.TxtvFoodNameCart1);
        TxtvPriceCart1 = (TextView) findViewById(R.id.TxtvPriceCart1);
        TxtvFoodNameCart2 = (TextView) findViewById(R.id.TxtvFoodNameCart2);
        TxtvPriceCart2 = (TextView) findViewById(R.id.TxtvPriceCart2);
        TxtvFoodNameCart3 = (TextView) findViewById(R.id.TxtvFoodNameCart3);
        TxtvPriceCart3 = (TextView) findViewById(R.id.TxtvPriceCart3);
        EdtxItem1 = (EditText) findViewById(R.id.EdtxItem1);
        EdtxItem2 = (EditText) findViewById(R.id.EdtxItem2);
        EdtxItem3 = (EditText) findViewById(R.id.EdtxItem3);
        SpinnerTempat = (Spinner) findViewById(R.id.SpinnerTempat);
        SpinnerKursi1 = (Spinner) findViewById(R.id.SpinnerKursi1);
        SpinnerKursi2 = (Spinner) findViewById(R.id.SpinnerKursi2);
        BtnAddItemCart1 = (Button) findViewById(R.id.BtnAddItemCart1);
        BtnReduceItemCart1 = (Button) findViewById(R.id.BtnReduceItemCart1);
        BtnAddItemCart2 = (Button) findViewById(R.id.BtnAddItemCart2);
        BtnReduceItemCart2 = (Button) findViewById(R.id.BtnReduceItemCart2);
        BtnAddItemCart3 = (Button) findViewById(R.id.BtnAddItemCart3);
        BtnReduceItemCart3 = (Button) findViewById(R.id.BtnReduceItemCart3);
        BtnOrder = (Button) findViewById(R.id.BtnOrder);
        ImgFood1 = (ImageView) findViewById(R.id.ImgFood1);
        ImgFood2 = (ImageView) findViewById(R.id.ImgFood2);
        ImgFood3 = (ImageView) findViewById(R.id.ImgFood3);

        //AdapterSpinner();



        final Intent intent = getIntent();
        setIDFood = intent.getIntExtra("setIDMenu",0);
        FoodName = intent.getStringExtra("FoodName");
        Price = intent.getStringExtra("Price");

        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        symbols.setDecimalSeparator(',');

        final DecimalFormat decimalFormat = new DecimalFormat("Rp #,###", symbols);



        if(FoodName != null && Price != null) {
            TxtvFoodNameCart1.setText(FoodName);
            TxtvPriceCart1.setText("Rp " + Price);

            if(setIDFood == 1){
                ImgFood1.setImageResource(R.drawable.mieayam);
            }
            else if (setIDFood == 2){
                ImgFood1.setImageResource(R.drawable.bakso);
            }
            else if (setIDFood == 3){
                ImgFood1.setImageResource(R.drawable.magelangan);
            }
            else{
            }

//            if(TxtvFoodNameCart2 == null){
//                TxtvFoodNameCart2.setText(FoodName);
//                TxtvPriceCart2.setText("Rp " + Price);
//
////            if(setIDFood == 1){
////                ImgFood2.setImageResource(R.drawable.mieayam);
////            }
////            else if (setIDFood == 2){
////                ImgFood2.setImageResource(R.drawable.bakso);
////            }
////            else if (setIDFood == 3){
////                ImgFood2.setImageResource(R.drawable.magelangan);
////            }
////            else {
////            }
//            }
//            else {}
//
        TxtvFoodNameCart2.setVisibility(View.INVISIBLE);
        TxtvPriceCart2.setVisibility(View.INVISIBLE);
        ImgFood2.setVisibility(View.INVISIBLE);
        BtnReduceItemCart2.setVisibility(View.INVISIBLE);
        BtnAddItemCart2.setVisibility(View.INVISIBLE);
        EdtxItem2.setVisibility(View.INVISIBLE);
        TxtvFoodNameCart3.setVisibility(View.INVISIBLE);
        TxtvPriceCart3.setVisibility(View.INVISIBLE);
        ImgFood3.setVisibility(View.INVISIBLE);
        BtnReduceItemCart3.setVisibility(View.INVISIBLE);
        BtnAddItemCart3.setVisibility(View.INVISIBLE);
        EdtxItem3.setVisibility(View.INVISIBLE);

        }
        else {
            TxtvFoodNameCart1.setVisibility(View.INVISIBLE);
            TxtvPriceCart1.setVisibility(View.INVISIBLE);
            ImgFood1.setVisibility(View.INVISIBLE);
            BtnReduceItemCart1.setVisibility(View.INVISIBLE);
            BtnAddItemCart1.setVisibility(View.INVISIBLE);
            EdtxItem1.setVisibility(View.INVISIBLE);
        }

        EdtxItem1.setEnabled(false);
        EdtxItem1.setText("1");

        BtnAddItemCart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(EdtxItem1.getText().toString());




                int b=a+1;
                if (b <= 0){
                    b = 1;
                }

                EdtxItem1.setText(new Integer(b).toString());
                String ValueEdtx = EdtxItem1.getText().toString();
                Total = Integer.valueOf(Integer.valueOf(ValueEdtx) * Integer.valueOf(Price));
                TxtvPriceCart1.setText(decimalFormat.format(Total));

                if(Total <= 0 ){
                    TxtvPriceCart1.setText("Rp " + Price);
                }
            }
        });

        BtnReduceItemCart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a=Integer.parseInt(EdtxItem1.getText().toString());

                int b=a-1;
                if (b <= 0){
                    b = 1;
                }

                EdtxItem1.setText(new Integer(b).toString());
                String ValueEdtx = EdtxItem1.getText().toString();
                Total = Integer.valueOf(Integer.valueOf(ValueEdtx) * Integer.valueOf(Price));
                TxtvPriceCart1.setText(decimalFormat.format(Total));

                if(Total <= 0 ){
                    TxtvPriceCart1.setText("Rp " + Price);
                }
            }
        });



        BtnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order();
            }
        });


    }

    private void Order() {
        Tempat = SpinnerTempat.getSelectedItem().toString();
        Kursi1 = SpinnerKursi1.getSelectedItem().toString();
        Kursi2 = SpinnerKursi2.getSelectedItem().toString();
//        id_product','id_user',
//        'quantity','place','seat_number','total_price'
        User user = SharedPrefmanager.getInstance(this).getUser();
        final Integer id_product = setIDFood;
        final Integer id_user = user.getId();
        final String quantity = EdtxItem1.getText().toString();
        final String place = Tempat;
        final String seat_number = Kursi1 + Kursi2;
        final Integer total_price = Total;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_ORDER,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
                                startActivity(new Intent(getApplicationContext(), BottomNavbar.class));
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
            //        id_product','id_user',
//        'quantity','place','seat_number','total_price'
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map <String, String> params = new HashMap<>();
                params.put("id_product", id_product.toString());
                params.put("id_user", id_user.toString());
                params.put("quantity", quantity.toString());
                params.put("place", place.toString());
                params.put("seat_number", seat_number.toString());
                params.put("total_price", total_price.toString());
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }


    private void AdapterSpinner() {
//        ArrayAdapter<CharSequence> adapterLokasi = ArrayAdapter.createFromResource(this,
//                R.array.lokasi, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapterLokasi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        SpinnerTempat.setAdapter(adapterLokasi);
//
//        ArrayAdapter<CharSequence> adapterKursi1 = ArrayAdapter.createFromResource(this,
//                R.array.nomor_kursi1, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapterKursi1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        SpinnerKursi1.setAdapter(adapterKursi1);
//
//        ArrayAdapter<CharSequence> adapterKursi2 = ArrayAdapter.createFromResource(this,
//                R.array.nomor_kursi2, android.R.layout.simple_spinner_item);
//        // Specify the layout to use when the list of choices appears
//        adapterKursi2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        // Apply the adapter to the spinner
//        SpinnerKursi2.setAdapter(adapterKursi2);



    }



}
