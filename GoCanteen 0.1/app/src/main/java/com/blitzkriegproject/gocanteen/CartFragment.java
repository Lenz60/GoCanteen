package com.blitzkriegproject.gocanteen;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.util.JsonReader;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CartFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    Spinner SpinnerTempat, SpinnerKursi1, SpinnerKursi2;
    TextView TxtvTempat, TxtvKursi, TxtvFoodNameCart1, TxtvPriceCart1;
    ImageView ImgFood1;
    Button BtnAddItemCart, BtnReduceItemCart;
    EditText EdtxItem;
    String strtext1, strtext2;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View view =  inflater.inflate(R.layout.fragment_cart, container, false);

        //Inisialisasi
        TxtvKursi = (TextView) view.findViewById(R.id.TxtvKursi);
        TxtvTempat = (TextView) view.findViewById(R.id.TxtvTempat);
        TxtvFoodNameCart1 = (TextView) view.findViewById(R.id.TxtvFoodNameCart1);
        TxtvPriceCart1 = (TextView) view.findViewById(R.id.TxtvPriceCart1);
        EdtxItem = (EditText) view.findViewById(R.id.EdtxItem);
        SpinnerTempat = (Spinner) view.findViewById(R.id.SpinnerTempat);
        SpinnerKursi1 = (Spinner) view.findViewById(R.id.SpinnerKursi1);
        SpinnerKursi2 = (Spinner) view.findViewById(R.id.SpinnerKursi2);
        BtnAddItemCart = (Button) view.findViewById(R.id.BtnAddItemCart);
        BtnReduceItemCart = (Button) view.findViewById(R.id.BtnReduceItemCart);
        ImgFood1 = (ImageView) view.findViewById(R.id.ImgFood1);

        ArrayAdapter<CharSequence> adapterLokasi = ArrayAdapter.createFromResource(getActivity(),
                R.array.lokasi, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterLokasi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        SpinnerTempat.setAdapter(adapterLokasi);

        ArrayAdapter<CharSequence> adapterKursi1 = ArrayAdapter.createFromResource(getActivity(),
                R.array.nomor_kursi1, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterKursi1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        SpinnerKursi1.setAdapter(adapterKursi1);

        ArrayAdapter<CharSequence> adapterKursi2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.nomor_kursi2, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterKursi2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        SpinnerKursi2.setAdapter(adapterKursi2);





        Bundle bundle = getArguments();
        if(bundle != null){
            strtext1 = bundle.getString("FoodData");
            strtext2 = bundle.getString("PriceData");
            TxtvFoodNameCart1.setText(strtext1);
            TxtvPriceCart1.setText(strtext2);

        }
        else {
            TxtvFoodNameCart1.setText("null");
            TxtvPriceCart1.setText("null");
        }

//        if (bundle != null)
//        {
//
//            TxtvFoodNameCart1.setText(Data1.toString());
//            TxtvPriceCart1.setText(Data2.toString());
//        }
//        else {
//            TxtvFoodNameCart1.setVisibility(View.INVISIBLE);
//            TxtvPriceCart1.setVisibility(View.INVISIBLE);
//            ImgFood1.setVisibility(View.INVISIBLE);
//            BtnReduceItemCart.setVisibility(View.INVISIBLE);
//            BtnAddItemCart.setVisibility(View.INVISIBLE);
//            EdtxItem.setVisibility(View.INVISIBLE);
//        }


        return view;
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof CartFragment.OnFragmentInteractionListener) {
//            mListener = (CartFragment.OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
//    }


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
