package com.example.android.i_am_baker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.i_am_baker.Adapter.Ingredient_Adapter;
import com.example.android.i_am_baker.network.Json_Type;

import org.json.JSONException;

/**
 * Created by ROHAN on 02-10-2017.
 */

public class Ingredient_Fragment extends Fragment {

    private RecyclerView mrecycle;
    private Ingredient_Adapter mAdapter;
    public Ingredient_Fragment(){
        //Mandantory constructor for instantiating
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.step_and_ingredient, container, false);
        mrecycle=(RecyclerView)rootView.findViewById(R.id.ingredient);
        LinearLayoutManager layoutmanager = new LinearLayoutManager(getActivity());
        mrecycle.setLayoutManager(layoutmanager);
        mAdapter=new Ingredient_Adapter();
        mrecycle.setLayoutManager(layoutmanager);
        mrecycle.setAdapter(mAdapter);
        String position=this.getArguments().getString("pos").toString();

        Json_Type obj=new Json_Type();
        try {
           // Log.i("The full sentence is",obj.return_Ingredient(position)[1]);
            mAdapter.setData(obj.return_Ingredient(position));


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return rootView;

    }


}
