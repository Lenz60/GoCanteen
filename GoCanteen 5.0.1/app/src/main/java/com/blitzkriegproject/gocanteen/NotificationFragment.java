package com.blitzkriegproject.gocanteen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.blitzkriegproject.gocanteen.model.Menu.Mieayam;
import com.blitzkriegproject.gocanteen.model.NotificationOrder.OrderNotif;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NotificationFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NotificationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationFragment extends Fragment {

    TextView TxtvNotifNama, TxtvNotifMakanan,
            TxtvNotifQuantity, TxtvNotifQuantity2,
            TxtvNotifTotal, TxtvNotifTotal2,
            TxtvNotifTempat, TxtvNotifKursi,
            TxtvNotifStatus;
    ImageView ImgNotifProses;
    SwipeRefreshLayout NotifSwipeReload;
    BottomNavbar BottomnavJava ;


    String Makanan, Nama;
    Integer Quantity;
    Integer Total;
    String Tempat;
    String Kursi;
    String Status;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public NotificationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
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
        View view = inflater.inflate(R.layout.fragment_notification, container, false);


        TxtvNotifMakanan = (TextView) view.findViewById(R.id.TxtvNotifMakanan);
        TxtvNotifNama = (TextView) view.findViewById(R.id.TxtvNotifNama);
        TxtvNotifQuantity = (TextView) view.findViewById(R.id.TxtvNotifQuantity);
        TxtvNotifQuantity2 = (TextView) view.findViewById(R.id.TxtvNotifQuantity2);
        TxtvNotifTotal = (TextView) view.findViewById(R.id.TxtvNotifTotal);
        TxtvNotifTotal2 = (TextView) view.findViewById(R.id.TxtvNotifTotal2);
        TxtvNotifStatus = (TextView) view.findViewById(R.id.TxtvNotifStatus);
        TxtvNotifTempat = (TextView) view.findViewById(R.id.TxtvNotifTempat);
        TxtvNotifKursi = (TextView) view.findViewById(R.id.TxtvNotifKursi);
        NotifSwipeReload = (SwipeRefreshLayout) view.findViewById(R.id.NotifSwipeReload);
        ImgNotifProses = (ImageView) view.findViewById(R.id.ImgNotifProses);


        final User user = SharedPrefmanager.getInstance(getActivity()).getUser();


        final OrderNotif notif = SharedPrefmanager.getInstance(getActivity()).getNotif();
        Status = notif.getStatus();
        Nama = notif.getName_user();
        Makanan = notif.getName_product();
        Tempat = notif.getPlace();
        Kursi = notif.getSeat_number();
        Quantity =  Integer.valueOf(notif.getQuantity());
        Total =  Integer.valueOf(notif.getTotal_price());











        NotifSwipeReload.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //BottomnavJava.GetNotification();
                    final Integer id_user = user.getId();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_NOTIF,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {


                                    try {
                                        //convert response to json object
                                        JSONObject obj = new JSONObject(response);

                                        //if no error in response
                                        if (!obj.getBoolean("error")) {
                                            Toast.makeText(getActivity().getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                                            //getting the user from the response
                                            JSONObject userJsonM = obj.getJSONObject("orderNotification");

//                                "id":13,"name_user":"Rafly
//                                Andrian","name_product":"Paket Mieayam & Esteh","place":"Basement
//                                5","seat_number":5,"quantity":2,"total_price":24000,"status":"Menunggu"}

                                            OrderNotif notif = new OrderNotif(
                                                    userJsonM.getInt("id"),
                                                    userJsonM.getInt("quantity"),
                                                    userJsonM.getInt("total_price"),
                                                    userJsonM.getString("name_user"),
                                                    userJsonM.getString("name_product"),
                                                    userJsonM.getString("place"),
                                                    userJsonM.getString("seat_number"),
                                                    userJsonM.getString("status")
                                            );

                                            //storing the user in shared preferences
                                            SharedPrefmanager.getInstance(getActivity().getApplicationContext()).storeNotif(notif);
//                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuB(bakso);
//                                SharedPrefmanager.getInstance(getApplicationContext()).storeMenuMG(magelangan);

                                            //starting the profile activity
                                        } else {
                                            Toast.makeText(getActivity().getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(getActivity().getApplicationContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map <String, String> params = new HashMap<>();
                            params.put("id_user", id_user.toString());
                            return params;
                        }

                    };

                    VolleySingleton.getInstance(getActivity()).addToRequestQueue(stringRequest);

                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setGroupingSeparator('.');
                symbols.setDecimalSeparator(',');

                final DecimalFormat decimalFormat = new DecimalFormat("Rp #,###", symbols);


                TxtvNotifNama.setText(Nama);
                TxtvNotifMakanan.setText(Makanan);
                TxtvNotifTempat.setText(Tempat);
                TxtvNotifKursi.setText(Kursi);
                TxtvNotifTotal2.setText(decimalFormat.format(Total));
//                TxtvNotifTotal2.setText("Rp "+ Total);
                TxtvNotifQuantity2.setText(Quantity.toString());
                TxtvNotifTotal.setText("Total : ");
                TxtvNotifQuantity.setText("Jumlah : ");


                if(Status != null){
                    if(Status.equalsIgnoreCase("Menunggu")){
                        ImgNotifProses.setImageResource(R.drawable.menunggu);
                    }
                    else if (Status.equalsIgnoreCase("Dibuat")){
                        ImgNotifProses.setImageResource(R.drawable.dibuat);
                    }
                    else if (Status.equalsIgnoreCase("Diantar")){
                        ImgNotifProses.setImageResource(R.drawable.diantar);
                    }
                }
                else {

                    TxtvNotifStatus.setText(Status);

                }



                if (NotifSwipeReload.isRefreshing()) {
                    NotifSwipeReload.setRefreshing(false);
                }

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
