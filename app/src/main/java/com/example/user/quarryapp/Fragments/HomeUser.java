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
public class HomeUser extends Fragment {


    public HomeUser() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home_user, container, false);

        return  view;
    }

}
