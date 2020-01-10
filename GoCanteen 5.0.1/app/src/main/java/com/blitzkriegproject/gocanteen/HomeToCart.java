package com.blitzkriegproject.gocanteen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.blitzkriegproject.gocanteen.view.BottomNavbar;

public class HomeToCart extends AppCompatActivity {

    TextView Txtv3,Txtv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_to_cart);

        Txtv3 = (TextView) findViewById(R.id.textView32);
        Txtv4 = (TextView) findViewById(R.id.textView42);

        Intent intent = getIntent();
        String FoodName = intent.getStringExtra("FoodName");
        String Price = intent.getStringExtra("Price");

        Txtv3.setText(FoodName);
        Txtv4.setText(Price);



        CartFragment CartFragment = new CartFragment();//Get Fragment Instance
        Bundle data = new Bundle();//Use bundle to pass data
        data.putString("FoodData", FoodName);//put string, int, etc in bundle with a key value
        data.putString("PriceData", Price);//put string, int, etc in bundle with a key value
        CartFragment.setArguments(data);//Finally set argument bundle to fragment


        Intent intent_next = new Intent(this, BottomNavbar.class);
        startActivity(intent_next);
        finish();




//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame_container, CartFragment)
//                .commit();

//        Bundle data = new Bundle();
//        data.putString(CartFragment.FoodNameKEY, FoodName);
//        data.putString(CartFragment.FoodPriceKEY, Price);
//        CartFragment cartfragment = new CartFragment();
//        cartfragment.setArguments(data);
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.frame_container, cartfragment)
//                .commit();



//        Bundle bundle=new Bundle();
//
//        bundle.putString("FoodNameData", FoodName.toString() );
//        //bundle.putString("PriceData", "From Activity");
//        //set Fragmentclass Arguments
//        Fragment fragobj=new CartFragment();
//        fragobj.setArguments(bundle);
//
//        Fragment frg = new CartFragment();
////        FragmentManager fragmentManagert = getFragmentManager();
////        FragmentTransaction fragmentTransaction = fragmentManagert.beginTransaction();
////        fragmentTransaction.replace(R.id.frame_container, frg).addToBackStack(null);
////        fragmentTransaction.commit();



    }
}
