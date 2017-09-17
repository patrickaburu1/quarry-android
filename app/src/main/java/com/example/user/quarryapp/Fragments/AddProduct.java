package com.example.user.quarryapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class AddProduct extends Fragment {

    EditText price,name;
    Button addProduct;

    String productName,productPrice;
    ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

           View view= inflater.inflate(R.layout.fragment_add_product, container, false);
                name= (EditText) view.findViewById(R.id.addProductName);
                price= (EditText) view.findViewById(R.id.addProductPrice);
                addProduct= (Button) view.findViewById(R.id.addProductButton);

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProduct();
            }
        });
        return  view;
    }

    public void addProduct(){

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading......................Hold On!");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        productName=name.getText().toString();
        productPrice=price.getText().toString();

        Map<String, String> params = new HashMap<String, String>();
        params.put("name", productName);
        params.put("price", productPrice);
        //params.put("phone", phone1);


        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Links+"addProduct", params,
                new Response.Listener<JSONObject>() {
                    int success;

                    @Override
                    public void onResponse(JSONObject response) {
                        pDialog.dismiss();
                        try {
                            success = response.getInt("id");
                            if (success >=1) {
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
