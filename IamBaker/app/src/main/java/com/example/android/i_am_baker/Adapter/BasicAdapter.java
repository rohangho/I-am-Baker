package com.example.android.i_am_baker.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.i_am_baker.R;
import com.example.android.i_am_baker.network.Json_Type;
import com.squareup.picasso.Picasso;

/**
 * Created by ROHAN on 23-09-2017.
 */

public class BasicAdapter extends RecyclerView.Adapter<BasicAdapter.AdapterViewHolder> {

    Context mcontext;

    Json_Type obj=new Json_Type();



    private String[] mdata;
    private final AdapterOnClickHandler mhandler;
    int i=0;

    public BasicAdapter(AdapterOnClickHandler mhandler,Context context){
        this.mhandler = mhandler;
        this.mcontext=context;
    }


    public interface AdapterOnClickHandler {
        void onClick(int position);
    }




    @Override
    public BasicAdapter.AdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.recipelist;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new AdapterViewHolder(view);
    }

    public void setData(String[] Data) {

        mdata=Data;
       Log.i("hiiii",mdata[1]);
        notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(BasicAdapter.AdapterViewHolder holder, int position) {

        String mov = mdata[position];
       // Log.i("hiiii",Integer.toString(position));
        if(position==0) {
            if (obj.return_image()[position]==null)
                holder.ming.setImageResource(R.drawable.nuetrella);
            else
                Picasso.with(mcontext).load(obj.return_image()[position]).into(holder.ming);
        }
        else if(position==1){
            if(obj.return_image()[position]==null) {
                holder.ming.setImageResource(R.drawable.brownies);
                Log.i("hiiiiii", Integer.toString(position));
            }
            else
                Picasso.with(mcontext).load(obj.return_image()[position]).into(holder.ming);
        }
        else if(position==2){
            if(obj.return_image()[position]==null)
            holder.ming.setImageResource(R.drawable.yellow);
            else
                Picasso.with(mcontext).load(obj.return_image()[position]).into(holder.ming);
        }
        else{
            if(obj.return_image()[position]==null)
            holder.ming.setImageResource(R.drawable.cheese);
            else
                Picasso.with(mcontext).load(obj.return_image()[position]).into(holder.ming);
        }
        holder.mtext.setText(mov);

    }

    @Override
    public int getItemCount() {
        if(mdata==null)
            return 0;
        return mdata.length;
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mtext;
        private ImageView ming;

        public AdapterViewHolder(View itemView) {
            super(itemView);
            mtext = (TextView) itemView.findViewById(R.id.name_of_cake);
            ming = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {

                int adapterPosition = getAdapterPosition();

                mhandler.onClick(adapterPosition);

        }
    }
}
