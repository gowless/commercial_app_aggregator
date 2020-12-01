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
import com.orkotkreditru.adapters.AdapterAllMain;
import com.orkotkreditru.models.get.Liste;

import java.util.List;

public class FragmentAllList extends Fragment {

    RecyclerView recyclerView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_all, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        //textView = view.findViewById(R.id.textView3);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        //recyclerView.setHasFixedSize(true);

        //enabling cache for better view experience
       // recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        AdapterAllMain adapterAllMain = new AdapterAllMain(getContext(), MainClass.listDataAll);
        adapterAllMain.setDataList(MainClass.listDataAll);
        recyclerView.setAdapter(adapterAllMain);
       //get main data from web
        return view;


    }




}