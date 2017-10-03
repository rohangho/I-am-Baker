package com.example.android.i_am_baker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class FragmentDisplayer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_displayer);
        String position;

        Intent intentThatStartedThisActivity = getIntent();
        Bundle bundle=new Bundle();

        // COMPLETED (2) Display the weather forecast that was passed from MainActivity
        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                position = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                bundle.putString("pos",position);
                //Log.i("i am position",position);
            }}

        Ingredient_Fragment ifragment=new Ingredient_Fragment();
        ifragment.setArguments(bundle);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.ingre,ifragment).commit();

        Step_Fragment stfragment=new Step_Fragment();
        FragmentManager fragmentManager1=getSupportFragmentManager();
        fragmentManager1.beginTransaction().add(R.id.ste,stfragment).commit();

    }
}
