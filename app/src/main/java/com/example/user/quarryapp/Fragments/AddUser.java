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
import android.widget.ProgressBar;

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
public class AddUser extends Fragment {
    EditText firstname, lastName, phoneNumber, idNumber;
    Button registerUser;

    String fname,lname,phone,idnumber;

    ProgressDialog pDialog;

    public AddUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        firstname = (EditText) view.findViewById(R.id.firstName);
        lastName = (EditText) view.findViewById(R.id.lastName);
        idNumber = (EditText) view.findViewById(R.id.idNumber);
        phoneNumber = (EditText) view.findViewById(R.id.phoneNumber);

        registerUser = (Button) view.findViewById(R.id.registerUserButton);

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        return view;
    }

    public void register() {

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage("Loading......................Hold On!");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        pDialog.show();

        fname=firstname.getText().toString();
        lname=lastName.getText().toString();
        idnumber=idNumber.getText().toString();
        phone=phoneNumber.getText().toString();

        Map<String, String> params = new HashMap<String, String>();
        params.put("firstname", fname);
        params.put("lastname", lname);
        params.put("idnumber", idnumber);
        params.put("phonenumber", phone);


        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, Links+"registerUser", params,
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
