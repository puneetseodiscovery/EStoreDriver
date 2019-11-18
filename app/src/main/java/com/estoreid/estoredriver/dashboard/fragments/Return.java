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
import com.estoreid.estoredriver.dashboard.adapter.ReturnOrderAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class Return extends Fragment {


    FragmentManager fragmentManager;
    RecyclerView returnrecyclerview;
    View view;
    ReturnOrderAdapter returnOrderAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_return, container, false);

        init();
        setAdapter();
        return view;
    }

    private void init() {
        fragmentManager = getFragmentManager();
        returnrecyclerview = view.findViewById(R.id.returnrecyclerview);
    }

    private void setAdapter() {
        returnOrderAdapter =new ReturnOrderAdapter(getContext());
        returnrecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        returnrecyclerview.setAdapter(returnOrderAdapter);
    }

}
