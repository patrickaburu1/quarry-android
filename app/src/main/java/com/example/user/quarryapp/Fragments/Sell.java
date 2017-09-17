package com.example.user.quarryapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.user.quarryapp.Network.AppController;
import com.example.user.quarryapp.Network.CustomRequest;
import com.example.user.quarryapp.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.example.user.quarryapp.Network.URLs.Links;
import static com.example.user.quarryapp.UserMainActivity.sell_p_id;
import static com.example.user.quarryapp.UserMainActivity.sell_p_name;
import static com.example.user.quarryapp.UserMainActivity.sell_p_price;


public class Sell extends Fragment {
        EditText quantity,customerName;
        TextView sellFinalProductName,sellFinalProductPrice;
        Button sellFinaleButton;
    ProgressDialog pDialog;
    Spinner transaction_type;

    String product_id,product_quantity,price,person_id,customer,transaction;

    public Sell() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_sell, container, false);

        quantity= (EditText) view.findViewById(R.id.finalSellQuantity);
        sellFinalProductName= (TextView) view.findViewById(R.id.sellFinalProductName);
        sellFinalProductPrice= (TextView) view.findViewById(R.id.finalSellProductPrice);
        customerName= (EditText) view.findViewById(R.id.finalSellCustomerName);

        sellFinaleButton= (Button) view.findViewById(R.id.finalSellButton);

        transaction_type= (Spinner) view.findViewById(R.id.finalSellTransaction);

        sellFinalProductName.setText(sell_p_name);
        sellFinalProductPrice.setText(sell_p_price);


        sellFinaleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sell();
            }
        });


        return view;
    }
//make sale
    private void sell() {

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading......................Hold On!");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();


        product_quantity=quantity.getText().toString();
        price=sellFinalProductPrice.getText().toString();
        customer=customerName.getText().toString();
        transaction=transaction_type.getSelectedItem().toString();

        //person_id

        //multiply quantity with to post total

       // product_id

        Map<String, String> params = new HashMap<String, String>();
        params.put("product_id", sell_p_id);
       // params.put("person_id", "1");
        params.put("price", price);
        params.put("quantity", product_quantity);
        params.put("customer_name",customer );
        params.put("transaction_type",transaction );


        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Links+"makeSale/1", params,
                new Response.Listener<JSONObject>() {
                    int success;

                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            success = response.getInt("id");
                            if (success >=1) {
                                // Log.d("Amount Successful!", response.toString());
                                String id = response.getString("id");
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("SUCCESSFULLY")
                                        .setContentText("REGISTERED")
                                        .show();
//                                FragmentTransaction f=getActivity().getSupportFragmentManager().beginTransaction();
//                                f.replace(R.id.nurseNavigator, new ParentList(), getString(R.string.app_name));
//                                f.commit();
                            } else {
                                pDialog.dismiss();

                                // Toast.makeText(getActivity(), "error "+response, Toast.LENGTH_SHORT).show();
                                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                                        .setTitleText("OPPS...!!")
                                        .setContentText("PLEASE TRY AGAIN"+response)
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                pDialog.dismiss();
                Log.d("Response: ", response.toString());
                new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("OoPS...!!")
                        .setContentText("PLEASE TRY AGAIN"+response)
                        .show();

            }
        });
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }
}
