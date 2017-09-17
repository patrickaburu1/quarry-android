package com.example.user.quarryapp.Fragments;


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
import com.example.user.quarryapp.Model.GetProduct;
import com.example.user.quarryapp.Model.GetProduct;
import com.example.user.quarryapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.user.quarryapp.Network.URLs.Links;

/**
 * A simple {@link Fragment} subclass.
 */
public class Products extends Fragment {


    List<GetProduct> getProduct1;
    RecyclerView recyclerView;

    RecyclerView.LayoutManager recyclerViewlayoutManager;

    RecyclerView.Adapter recyclerViewadapter;

    ProgressBar progressBar;

//    //testing
//    String JSON_NAME = "train";
//    String JSON_FROM = "from";
//    String JSON_DESTINATION = "to";
//    String JSON_AMOUNT = "amount";

    RequestQueue requestQueue ;
    public Products() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_products_list, container, false);

        getProduct1 = new ArrayList<>();

        recyclerView = (RecyclerView) view.findViewById(R.id.recylerproducts);

        progressBar = (ProgressBar) view.findViewById(R.id.progressbar);

        recyclerView.setHasFixedSize(true);

        recyclerViewlayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(recyclerViewlayoutManager);

        products();

        return view;
    }

    public  void  products(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Links+"getProducts",

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

            GetProduct GetDataAdapter2 = new GetProduct();

            JSONObject json = null;
            try {
                json = array.getJSONObject(i);
               GetDataAdapter2.setId(json.getString("id"));
                GetDataAdapter2.setName(json.getString("name"));
                GetDataAdapter2.setPrice(json.getString("price"));
               // GetDataAdapter2.setQuantity(json.getString("quantity"));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            getProduct1.add(GetDataAdapter2);
        }

        recyclerViewadapter = new RecyclerProductAdapter(getProduct1, getActivity());

        recyclerView.setAdapter(recyclerViewadapter);
    }
}