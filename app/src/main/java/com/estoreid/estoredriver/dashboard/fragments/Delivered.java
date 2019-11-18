package com.estoreid.estoredriver.dashboard.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.estoreid.estoredriver.R;
import com.estoreid.estoredriver.dashboard.adapter.DeliveredOrderAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Delivered extends Fragment {

    FragmentManager fragmentManager;
    RecyclerView deliverdrecyclerview;
    View view;
    DeliveredOrderAdapter deliveredOrderAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_delivered, container, false);

        init();
        setAdapter();
        return view;
    }

    private void init() {
        fragmentManager = getFragmentManager();
        deliverdrecyclerview = view.findViewById(R.id.deliverdrecyclerview);
    }

    private void setAdapter() {
        deliveredOrderAdapter =new DeliveredOrderAdapter(getContext());
        deliverdrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        deliverdrecyclerview.setAdapter(deliveredOrderAdapter);
    }

}
