package com.example.user.quarryapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.user.quarryapp.Adapter.RecyclerProductAdapter;
import com.example.user.quarryapp.Adapter.RecyclerUserSalesAdapter;
import com.example.user.quarryapp.Model.GetSales;
import com.example.user.quarryapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.quarryapp.Network.URLs.Links;


public class Sales extends Fragment {

    List<GetSales> getProduct1;
    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    ProgressBar progressBar;

    RequestQueue requestQueue ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_sales, container, false);
        getProduct1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerUserSales);

        progressBar = (ProgressBar) view.findViewById(R.id.userSalesProgress);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        userSales();
        return view;
    }

    private void userSales() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Links+"getSales/1",

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        progressBar.setVisibility(View.GONE);

                        json_parse_data(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        new SweetAlertDialog(getActivity(), SweetAlertDialog.ERROR_TYPE)
//                                .setTitleText("Oops...!")
//                                .setContentText("Something went wrong!PLEASE CHECK YOUR NETWORK CONNECTION")
//                                .show();
                        Toast.makeText(getActivity(), "Something went wrong!PLEASE CHECK YOUR NETWORK CONNECTION", Toast.LENGTH_SHORT).show();


                    }
                });

        requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(jsonArrayRequest);
    }

    public void  json_parse_data(JSONArray array){
        for(int i = 0; i<array.length(); i++) {

            GetSales GetDataAdapter2 = new GetSales();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
                GetDataAdapter2.setProduct(json.getString("name"));
                GetDataAdapter2.setQuantity(json.getString("quantity"));
                GetDataAdapter2.setTotal(json.getString("price"));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            getProduct1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerUserSalesAdapter(getProduct1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);
    }
}