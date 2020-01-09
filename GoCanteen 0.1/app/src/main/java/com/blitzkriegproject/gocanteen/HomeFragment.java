package com.blitzkriegproject.gocanteen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blitzkriegproject.gocanteen.model.Menu.Bakso;
import com.blitzkriegproject.gocanteen.model.Menu.Magelangan;
import com.blitzkriegproject.gocanteen.model.Menu.Mieayam;
import com.blitzkriegproject.gocanteen.model.SharedPrefmanager;
import com.blitzkriegproject.gocanteen.model.User;
import com.blitzkriegproject.gocanteen.view.BottomNavbar;
import com.blitzkriegproject.gocanteen.view.Splash;

import java.io.ByteArrayOutputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    //deklarasi
    TextView TxtvNameH, TxtvMenu1, TxtvMenu2, TxtvMenu3, TxtvPrice1, TxtvPrice2, TxtvPrice3;
    SwipeRefreshLayout SwipeReload;
    BottomNavbar BottomnavJava ;
    ImageView ImgFoodHome1, ImgFoodHome2, ImgFoodHome3;
    String Menu1,Menu2,Menu3,Price1,Price2,Price3;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_home, container, false);

        //inisialisasi
        BottomnavJava = (BottomNavbar)getActivity();
        TxtvNameH = (TextView) view.findViewById(R.id.TxtvNameH);
        TxtvMenu1 = (TextView) view.findViewById(R.id.TxtvMenu1);
        TxtvMenu2 = (TextView) view.findViewById(R.id.TxtvMenu2);
        TxtvMenu3 = (TextView) view.findViewById(R.id.TxtvMenu3);
        TxtvPrice1 = (TextView) view.findViewById(R.id.TxtvPrice1);
        TxtvPrice2 = (TextView) view.findViewById(R.id.TxtvPrice2);
        TxtvPrice3 = (TextView) view.findViewById(R.id.TxtvPrice3);
        ImgFoodHome1 = (ImageView) view.findViewById(R.id.ImgFoodHome1);
        ImgFoodHome2 = (ImageView) view.findViewById(R.id.ImgFoodHome2);
        ImgFoodHome3 = (ImageView) view.findViewById(R.id.ImgFoodHome3);
        SwipeReload = (SwipeRefreshLayout) view.findViewById(R.id.SwipeReload);




        //set value
        User user = SharedPrefmanager.getInstance(getActivity()).getUser();
        final Mieayam mieayam = SharedPrefmanager.getInstance(getActivity()).getMieayam();
        final Bakso bakso = SharedPrefmanager.getInstance(getActivity()).getBakso();
        final Magelangan magelangan = SharedPrefmanager.getInstance(getActivity()).getMagelangan();

        Menu1 = mieayam.getName();
        Menu2 = bakso.getName();
        Menu3 = magelangan.getName();

        Price1 = String.valueOf(mieayam.getPrice());
        Price2 = String.valueOf(bakso.getPrice());
        Price3 = String.valueOf(magelangan.getPrice());

        TxtvNameH.setText("Hello " +user.getName());
        TxtvMenu1.setText(Menu1);
        TxtvMenu2.setText(Menu2);
        TxtvMenu3.setText(Menu3);
        TxtvPrice1.setText("Rp "+Price1);
        TxtvPrice2.setText("Rp "+Price2);
        TxtvPrice3.setText("Rp "+Price3);


        SwipeReload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                SwipeReload.setRefreshing(true);
                BottomnavJava.GetMieayamMenu();
                BottomnavJava.GetBaksoMenu();
                BottomnavJava.GetMagelanganMenu();

                if (SwipeReload.isRefreshing()) {
                    SwipeReload.setRefreshing(false);
                }

            }
        });
        SwipeReload.setRefreshing(false);

        TxtvMenu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer setID = mieayam.getId();

                Intent intent = new Intent(getActivity().getBaseContext(),
                        CartActivity.class);
                intent.putExtra("setIDMenu",setID);
                intent.putExtra("FoodName", Menu1);
                intent.putExtra("Price", Price1);
                getActivity().startActivity(intent);


            }
        });

        TxtvMenu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer setID = bakso.getId();

                Intent intent = new Intent(getActivity().getBaseContext(),
                        CartActivity.class);
                intent.putExtra("setIDMenu",setID);
                intent.putExtra("FoodName", Menu2);
                intent.putExtra("Price", Price2);
                getActivity().startActivity(intent);
            }
        });

        TxtvMenu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer setID = magelangan.getId();

                Intent intent = new Intent(getActivity().getBaseContext(),
                        CartActivity.class);
                intent.putExtra("setIDMenu",setID);
                intent.putExtra("FoodName", Menu3);
                intent.putExtra("Price", Price3);
                getActivity().startActivity(intent);

            }
        });

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }




    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
