package com.example.android.i_am_baker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.i_am_baker.Adapter.Step_Adapter;

public class IngredientStepActivity extends AppCompatActivity implements Step_Adapter.AdapterOnClickHandler {

    public static boolean m2plane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if((findViewById(R.id.linear))!=null)
            m2plane=true;
        else
            m2plane=false;


        setContentView(R.layout.activity_fragment_displayer);
        String position;

        Intent intentThatStartedThisActivity = getIntent();
        Bundle bundle=new Bundle();


        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                position = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                bundle.putString("pos",position);
                //Log.i("i am position",position);
            }}


            Ingredient_Fragment ifragment = new Ingredient_Fragment();
            ifragment.setArguments(bundle);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.ingre, ifragment).commit();

            //Step_Fragment stfragment = new Step_Fragment();
             Step_Fragment stfragment= Step_Fragment.newInstance(this);
            FragmentManager fragmentManager1 = getSupportFragmentManager();
            fragmentManager1.beginTransaction().add(R.id.ste, stfragment).commit();







        }







    @Override
    public void onClick(int position) {

        if ((getResources().getBoolean(R.bool.is_Tab))) {

            Bundle bundle1=new Bundle();
            bundle1.putString("pos", Integer.toString(position));

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
}




