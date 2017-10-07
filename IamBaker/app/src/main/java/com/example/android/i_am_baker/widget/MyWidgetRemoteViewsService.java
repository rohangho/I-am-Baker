package com.example.android.i_am_baker.widget;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.i_am_baker.R;
import com.example.android.i_am_baker.network.Json_Type;

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

        public MyWidgetRemoteViewsFactory(Context context) {
            this.context = context;

            mydata = new ArrayList<>();
            for (int i=0;i<obj.return_widget().length;i++)
            //    Log.i("hiii", (String) obj.return_widget().get(i));
           mydata.add(new CustomDataType(obj.return_widget()[i]));
           // mydata.add(new CustomDataType("hi"));


        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {

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





