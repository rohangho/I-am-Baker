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
    public static String[] type;
    public static URL buildurl() throws JSONException, MalformedURLException {
        String url ="https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        Uri builtUri = Uri.parse(url).buildUpon().build();
        URL url1 = null;
        url1 = new URL(builtUri.toString());

        return url1;
    }
    public static String[] name(Context context, String url) throws JSONException {

        JSONArray forJson = new JSONArray(url);

        noofid=forJson.length();

        type=new String[forJson.length()];
        for (int i=0;i<noofid;i++) {
            JSONObject current = forJson.getJSONObject(i);
            type[i] = current.getString("name");



        }
        return type;
    }

    }





