package com.example.android.i_am_baker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

public class DetailVideoActivity extends AppCompatActivity {

    public VideoFragment ifragment1;
    FragmentManager fragmentManager1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_step);
        String position;

        Intent intentThatStartedThisActivity = getIntent();
        Bundle bundle=new Bundle();


        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                position = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
                bundle.putString("pos",position);
                //Log.i("i am position",position);
            }}

        DetailFragment ifragment=new DetailFragment();
        ifragment.setArguments(bundle);
        FragmentManager fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.detail_of_step,ifragment).commit();

        if(savedInstanceState!=null)
        {
            ifragment1=(VideoFragment) getSupportFragmentManager().getFragment(savedInstanceState, "saved_fragment");


        }
        else {
            ifragment1 = new VideoFragment();
            ifragment1.setArguments(bundle);
            fragmentManager1 = getSupportFragmentManager();
            fragmentManager1.beginTransaction().add(R.id.Corresponding_video, ifragment1).commit();
        }






    }

    @Override
    protected void onSaveInstanceState (Bundle outState){
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, "saved_fragment", ifragment1);
    }

}
