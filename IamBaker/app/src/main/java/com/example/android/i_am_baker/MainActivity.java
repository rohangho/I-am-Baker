package com.example.android.i_am_baker;


import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.i_am_baker.Adapter.BasicAdapter;
import com.example.android.i_am_baker.network.Json_Type;

import org.json.JSONException;

import java.net.MalformedURLException;
import java.net.URL;


public class MainActivity extends AppCompatActivity implements BasicAdapter.AdapterOnClickHandler {

    private RecyclerView mrecycle;
    private BasicAdapter madapter;
    //Context context=;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrecycle = (RecyclerView) findViewById(R.id.types);
        //To check weathet it is tab mod or phone mod
        if (getResources().getBoolean(R.bool.is_Tab)) {

            GridLayoutManager layoutmanager = new GridLayoutManager(this, 4);
            mrecycle.setLayoutManager(layoutmanager);
            madapter = new BasicAdapter(this,this);

            mrecycle.setLayoutManager(layoutmanager);
            mrecycle.setAdapter(madapter);

        } else {

            LinearLayoutManager layoutmanager = new LinearLayoutManager(this);
            mrecycle.setLayoutManager(layoutmanager);
            madapter = new BasicAdapter(this,this);

            mrecycle.setLayoutManager(layoutmanager);
            mrecycle.setAdapter(madapter);
        }
        FetchTask fb = new FetchTask();
        fb.execute();

    }



    @Override
    public void onClick(int position) {
        Context context = this;
        Class destinationClass = IngredientStepActivity.class;
        Intent intentToStartDetailActivity = new Intent(context, destinationClass);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, Integer.toString(position));
        startActivity(intentToStartDetailActivity);

    }


    public class FetchTask extends AsyncTask<String, Void, String[]>{




        @Override
        protected String[] doInBackground(String... params) {

            URL RequestUrl = null;
            try {
                RequestUrl = Json_Type.buildurl();
               // Log.i("hiiiiiiiiiiii",RequestUrl.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                String jsonResponse = Json_Type
                        .getResponseFromHttpUrl(RequestUrl);

                String[] names = Json_Type.name
                        (MainActivity.this, jsonResponse);

                return names;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }

        @Override
        protected void onPostExecute(String[] Data) {

            if (Data != null) {

                //Log.i("hiiiiiii",Data[1]);
                madapter.setData(Data);
            }
        }

    }


    public Activity getActivity() {
        Context context = this;
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();

        }

        return this;

    }


}
