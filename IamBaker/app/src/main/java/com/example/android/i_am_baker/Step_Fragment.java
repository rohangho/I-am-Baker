package com.example.android.i_am_baker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.i_am_baker.Adapter.Step_Adapter;
import com.example.android.i_am_baker.network.Json_Type;

/**
 * Created by ROHAN on 03-10-2017.
 */

public class Step_Fragment extends Fragment implements Step_Adapter.AdapterOnClickHandler {

    private RecyclerView mrecycle;
    private Step_Adapter mAdapter;
    public Step_Fragment(){
        //Mandantory constructor for instantiating
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.step_and_ingredient, container, false);
        mrecycle=(RecyclerView)rootView.findViewById(R.id.ingredient);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
        mrecycle.setLayoutManager(layoutmanager);
        mAdapter=new Step_Adapter(this);
        mrecycle.setLayoutManager(layoutmanager);
        mrecycle.setAdapter(mAdapter);
       // String position=this.getArguments().getString("pos").toString();

        Json_Type obj=new Json_Type();
        // Log.i("The full sentence is",obj.return_Ingredient(position)[1]);
        mAdapter.setData(obj.return_Step());


        return rootView;

    }


    @Override
    public void onClick(int position) {

        Class destinationClass = DetailVideoActivity.class;
        Intent intentToStartDetailActivity = new Intent(getActivity(), destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, Integer.toString(position));
        startActivity(intentToStartDetailActivity);
    }
}
