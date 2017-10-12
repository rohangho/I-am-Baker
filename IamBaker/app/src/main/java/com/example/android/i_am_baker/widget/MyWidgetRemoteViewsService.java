package com.example.android.i_am_baker.widget;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.i_am_baker.IngredientStepActivity;
import com.example.android.i_am_baker.R;
import com.example.android.i_am_baker.network.Json_Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by ROHAN on 07-10-2017.
 */

public class MyWidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new MyWidgetRemoteViewsFactory(this.getApplicationContext());
    }

    class MyWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        private ArrayList<CustomDataType> mydata;
        private Context context;
        Json_Type obj=new Json_Type();
        IngredientStepActivity obj1=new IngredientStepActivity();

        public MyWidgetRemoteViewsFactory(Context context) {
            this.context = context;

            mydata = new ArrayList<>();
           mydata.add(new CustomDataType("Brownies"));
            mydata.add(new CustomDataType("Yellow Cake"));
            mydata.add(new CustomDataType("Nutrella"));
            mydata.add(new CustomDataType("Cheese cake"));}

          //  Log.e("hiii",obj.return_type()[0]);
           //  for (int i=0;i<obj.return_type().length;i++)
            //    Log.i("hiii", (String) obj.return_widget().get(i));
           //mydata.add(new CustomDataType(obj.return_type()[i]));
           // mydata.add(new CustomDataType("hi"));





        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String json = preferences.getString(IngredientStepActivity.SHARED_PREFS_KEY, "");
            if (!json.equals("")) {
                Gson gson = new Gson();
                mydata = gson.fromJson(json, new TypeToken<ArrayList<CustomDataType>>() {
                }.getType());
            }



        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            if(mydata !=null)
                return mydata.size();
            else
                return 0;
        }

        @Override
        public RemoteViews getViewAt(int position) {
            RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.widget_list);
            rv.setTextViewText(R.id.widgetItemTaskNameLabel, mydata.get(position).getresname());

            Bundle extras = new Bundle();
            extras.putString("pos", Integer.toString(position));
            Intent fillInIntent = new Intent();
          //  fillInIntent.putExtra(Intent.EXTRA_TEXT, Integer.toString(position));
            fillInIntent.putExtras(extras);
            // Make it possible to distinguish the individual on-click
            // action of a given item
            rv.setOnClickFillInIntent(R.id.widgetItemTaskNameLabel, fillInIntent);

            return rv;
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }
    }
}





