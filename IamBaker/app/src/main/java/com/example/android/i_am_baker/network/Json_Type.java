package com.example.android.i_am_baker.network;

import android.content.Context;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by ROHAN on 23-09-2017.
 */

public class Json_Type {

   // public String key(int count)
    //{
        //return key[count];
    //}
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


    public static int noofid;
    public static int number_Of_Ingridient;
    public static String[] full_Sentence;
    public static String[] type;
    public static String[] quantity;
    public static String Url_copy;
    public static String[] measure;
    public static String[] ingredient;
    public static String[] steps;
    public static String[] step_detail;
    public static String[] step_video_url;

    public static URL buildurl() throws JSONException, MalformedURLException {
        String url ="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        Uri builtUri = Uri.parse(url).buildUpon().build();
        URL url1 = null;
        url1 = new URL(builtUri.toString());

        return url1;
    }
    public static String[] name(Context context, String url) throws JSONException {
        Url_copy=url;
        JSONArray forJson = new JSONArray(url);

        noofid=forJson.length();

        type=new String[forJson.length()];
        for (int i=0;i<noofid;i++) {
            JSONObject current = forJson.getJSONObject(i);
            type[i] = current.getString("name");




        }
        return type;
    }

    public static String[] return_Ingredient (String position) throws JSONException {
        JSONArray forJsonfunction = new JSONArray(Url_copy);
        JSONObject current = forJsonfunction.getJSONObject(Integer.parseInt(position));
        JSONArray number = current.getJSONArray("ingredients");
        JSONArray stepnumber = current.getJSONArray("steps");
        number_Of_Ingridient = number.length();
        quantity=new String[number.length()];
        measure=new String[number.length()];
        ingredient=new String[number.length()];
        full_Sentence=new String[number.length()];
        steps=new String[stepnumber.length()];
        step_detail=new String[stepnumber.length()];
        step_video_url=new String[stepnumber.length()];
        // Log.i("hiiiii",Integer.toString(number_Of_Ingridient));
        for(int j=0;j<number_Of_Ingridient;j++)
        {
            JSONObject currentIngredient = number.getJSONObject(j);
            quantity[j] = currentIngredient.getString("quantity");
            measure[j] = currentIngredient.getString("measure");
            ingredient[j] = currentIngredient.getString("ingredient");
            full_Sentence[j]= quantity[j]+" "+measure[j]+" "+"of"+" "+ingredient[j];


        }
        for(int k=0;k<stepnumber.length();k++)
        {
            JSONObject currentstep =  stepnumber.getJSONObject(k);

            steps[k]= currentstep.getString("shortDescription");
            step_detail[k]= currentstep.getString("description");
            step_video_url[k]=currentstep.getString("videoURL");
        }
       // Log.i("The full sentence is ",full_Sentence[1]);
        return full_Sentence;

    }

        public static String[] return_Step()
        {
            return steps;
        }

        public static String return_detail(int pos)
        {
            return step_detail[pos];
        }

    }





