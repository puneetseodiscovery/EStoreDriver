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
import com.estoreid.estoredriver.dashboard.adapter.OngoingOrderAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Ongoing extends Fragment {

    FragmentManager fragmentManager;
    RecyclerView ongoingrecyclerview;
    View view;
    OngoingOrderAdapter ongoingOrderAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ongoing, container, false);

        init();
        setData();
        return view;
    }


    private void init() {
        fragmentManager = getFragmentManager();
        ongoingrecyclerview = view.findViewById(R.id.ongoingrecyclerview);
    }

    private void setData() {
        ongoingOrderAdapter =new OngoingOrderAdapter(getContext());
        ongoingrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        ongoingrecyclerview.setAdapter(ongoingOrderAdapter);
    }
}
