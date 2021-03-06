package com.example.android.i_am_baker.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.i_am_baker.R;

/**
 * Created by ROHAN on 03-10-2017.
 */

public class Ingredient_Adapter extends RecyclerView.Adapter<Ingredient_Adapter.AdapterViewHolder> {

    private  String[] mdata;
    public interface AdapterOnClickHandler_for_ingredient {
        void onClick(int position);
    }

    @Override
    public Ingredient_Adapter.AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.ingridientlist;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new AdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Ingredient_Adapter.AdapterViewHolder holder, int position) {
        Log.i("hiiii",mdata[1]);
        String data = mdata[position];
        holder.mtext.setText(data);

    }

    @Override
    public int getItemCount() {
        if(mdata==null)
            return 0;
        return mdata.length;
    }

    public void setData(String[] Data) {

        mdata=Data;

        notifyDataSetChanged();
    }
    public class AdapterViewHolder extends RecyclerView.ViewHolder  {
        public TextView mtext;


        public AdapterViewHolder(View itemView) {
            super(itemView);
            mtext = (TextView) itemView.findViewById(R.id.ingridient);
        }
        }

}
