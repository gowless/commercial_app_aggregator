package com.orkotkreditru.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.orkotkreditru.MainClass;
import com.orkotkreditru.R;
import com.orkotkreditru.adapters.RecyclerAdapterWithBadList;
import com.orkotkreditru.models.get.Liste;

import java.util.List;

public class FragmentWithBadList extends Fragment {
    RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_with_bad_list, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        //  textView = view.findViewById(R.id.textView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
      //  recyclerView.setHasFixedSize(true);

        //enabling cache for better view experience
       // recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        RecyclerAdapterWithBadList recyclerAdapterWithBadList = new RecyclerAdapterWithBadList(getContext(), MainClass.listDataBad);
        recyclerAdapterWithBadList.setDataList(MainClass.listDataBad);
        recyclerView.setAdapter(recyclerAdapterWithBadList);

        return view;
    }


}