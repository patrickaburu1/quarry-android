package com.example.user.quarryapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.quarryapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PostExpenses extends Fragment {


    public PostExpenses() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
      View view= inflater.inflate(R.layout.fragment_post_expenses, container, false);

        return view;
    }

}
