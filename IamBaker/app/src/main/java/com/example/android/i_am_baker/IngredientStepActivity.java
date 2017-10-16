package com.example.android.i_am_baker;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.i_am_baker.Adapter.Ingredient_Adapter;
import com.example.android.i_am_baker.Adapter.Step_Adapter;
import com.example.android.i_am_baker.network.Json_Type;
import com.example.android.i_am_baker.widget.CustomDataType;
import com.example.android.i_am_baker.widget.new_app_widget;
import com.google.gson.Gson;

import org.json.JSONException;

import java.util.ArrayList;

public class IngredientStepActivity extends AppCompatActivity implements Step_Adapter.AdapterOnClickHandler,Ingredient_Adapter
.AdapterOnClickHandler_for_ingredient{
    public static final String SHARED_PREFS_KEY = "SHARED_PREFS_KEY";

    public static boolean m2plane;
    //public static String position_to_save;
    public static int position_clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if ((findViewById(R.id.linear)) != null)
            m2plane = true;
        else
            m2plane = false;


        setContentView(R.layout.activity_fragment_displayer);
        String position;

        Intent intentThatStartedThisActivity = getIntent();
        Bundle bundle = new Bundle();


        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                position = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);



                makeData(position);



                // position_to_save=position;
                bundle.putString("pos", position);
                //Log.i("i am position",position);
            }
        }

        if (savedInstanceState==null){
        Ingredient_Fragment ifragment = new Ingredient_Fragment();
        ifragment.setArguments(bundle);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.ingre, ifragment).commit();

        //Step_Fragment stfragment = new Step_Fragment();
        Step_Fragment stfragment = Step_Fragment.newInstance(this);
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        fragmentManager1.beginTransaction().add(R.id.ste, stfragment).commit();
    }






        }







    @Override
    public void onClick(int position) {
        position_clicked=position;
       // SharedPreferences sharedpref=getSharedPreferences("clicking pos", Context.MODE_PRIVATE);
        //SharedPreferences.Editor editor = sharedpref.edit();


        if ((getResources().getBoolean(R.bool.is_Tab))) {


            Bundle bundle1=new Bundle();
            bundle1.putString("pos", Integer.toString(position));

            Log.i("hiiiiiiiiiiiiiiiiiii",Integer.toString(position));

            DetailFragment ifragment4=new DetailFragment();
            ifragment4.setArguments(bundle1);
            FragmentManager fragmentManager4=getSupportFragmentManager();
            fragmentManager4.beginTransaction().add(R.id.detail_of_step,ifragment4).commit();

            VideoFragment ifragment5=new VideoFragment();
            ifragment5.setArguments(bundle1);
            FragmentManager fragmentManager5=getSupportFragmentManager();
            fragmentManager5.beginTransaction().add(R.id.Corresponding_video,ifragment5).commit();

        }
        else{
            Class destinationClass = DetailVideoActivity.class;
            Intent intentToStartDetailActivity = new Intent(this, destinationClass);
            intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, Integer.toString(position));
            startActivity(intentToStartDetailActivity);
        }
    }
    Json_Type obj=new Json_Type();
    private void makeData(String buttonName) {
        ArrayList<CustomDataType> widgetObjects = new ArrayList<>();
       if(Integer.parseInt(buttonName)==0)
       {
           try {
               int a=obj.return_Ingredient(buttonName).length;
               for(int i=0;i<a;i++)
               {
                   widgetObjects.add(new CustomDataType(obj.return_Ingredient(buttonName)[i]));

               }
           } catch (JSONException e) {
               e.printStackTrace();
           }

           //sendBroadcast();
       }
        else if(Integer.parseInt(buttonName)==1)
        {
            try {
                int a=obj.return_Ingredient(buttonName).length;
                for(int i=0;i<a;i++)
                {
                    widgetObjects.add(new CustomDataType(obj.return_Ingredient(buttonName)[i]));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            //sendBroadcast();
        }
        else if(Integer.parseInt(buttonName)==2)
        {
            try {
                int a=obj.return_Ingredient(buttonName).length;
                for(int i=0;i<a;i++)
                {
                    widgetObjects.add(new CustomDataType(obj.return_Ingredient(buttonName)[i]));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
           // sendBroadcast();
        }
        else if(Integer.parseInt(buttonName)==3)
        {
            try {
                int a=obj.return_Ingredient(buttonName).length;
                for(int i=0;i<a;i++)
                {
                    widgetObjects.add(new CustomDataType(obj.return_Ingredient(buttonName)[i]));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
           // sendBroadcast();

        }
        // widgetObjects.add(new CustomDataType("Hello"));
        //widgetObjects.add(new CustomDataType("this"));
        //widgetObjects.add(new CustomDataType("position"));
        //widgetObjects.add(new CustomDataType(buttonName));
        Gson gson = new Gson();
        String json = gson.toJson(widgetObjects);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(SHARED_PREFS_KEY, json).commit();
        sendBroadcast();
    }

    private void sendBroadcast() {

        Context context=getApplicationContext();
        Intent intent = new Intent(this, new_app_widget.class);
        intent.setAction("android.appwidget.action.APPWIDGET_UPDATE\"");
        ComponentName name=new ComponentName(context,new_app_widget.class);
        int[] ids = AppWidgetManager.getInstance(context).getAppWidgetIds(name);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS,ids);
        sendBroadcast(intent);
    }

}




