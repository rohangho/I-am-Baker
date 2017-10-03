package com.example.android.i_am_baker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.i_am_baker.network.Json_Type;

/**
 * Created by ROHAN on 03-10-2017.
 */

public class DetailFragment extends Fragment {


    public DetailFragment(){
        //Mandantory constructor for instantiating
    }
    public TextView mtext;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.step_detail, container, false);
       mtext=(TextView) rootView.findViewById(R.id.detail);


        String position=this.getArguments().getString("pos").toString();
        Json_Type obj=new Json_Type();

        mtext.setText(obj.return_detail(Integer.parseInt(position)));


        return rootView;

    }

}
