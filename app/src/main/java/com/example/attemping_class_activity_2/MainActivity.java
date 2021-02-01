package com.example.attemping_class_activity_2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity {

    private Button button_search;
    private SearchView searchView_search;


    private static AsyncHttpClient client = new AsyncHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_search = findViewById(R.id.button_search);
        searchView_search = findViewById(R.id.searchView_search);

        button_search.setOnClickListener(v ->
                launchNextActivity(v));
    }


    public void launchNextActivity(View view) {
        CharSequence query = searchView_search.getQuery().toString();
        String api_url = "https://api.openweathermap.org/data/2.5/weather?q=" + query + "&appid=997475d54886c4fb4db2803703fb4007&units=imperial";

       client.addHeader("Accept", "application/json");
        // send a get request to the api url
        client.get(api_url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // when you get a 200 status code
                Log.d("api response", new String(responseBody));
                try {
                    JSONObject json = new JSONObject(new String(responseBody));
                   // Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                   // intent.putExtra("id", json.getString("id"));

                    // For temp min
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);

                    JSONObject temp_min = json.getJSONObject("main");
                    String temp_min_1 = temp_min.getString("temp_min");
                    intent.putExtra("temp_min", temp_min_1);

                    // For temp max
                    JSONObject temp_max = json.getJSONObject("main");
                    String temp_max_1 = temp_max.getString("temp_max");
                    intent.putExtra("temp_max", temp_max_1);

                    // For feels like
                    JSONObject feels_like = json.getJSONObject("main");
                    String feels_like_1 = feels_like.getString("feels_like");
                    intent.putExtra("feels_like", feels_like_1);

                    // For description
                    JSONArray description = json.getJSONArray("weather");
                    JSONObject object1 = description.getJSONObject(0);
                    String description_1 = object1.getString("description");
                    intent.putExtra("description", description_1);

                    // For country
                    JSONObject location = json.getJSONObject("sys");
                    String location_1 = location.getString("country");
                    CharSequence query = searchView_search.getQuery().toString();
                    String query_1 = query.toString();
                    String sending = query_1 +", " + location_1;
                    intent.putExtra("name", sending);

                    startActivity(intent);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // when you get a 400-499 status code
                Log.e("api error", new String(responseBody));
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String message = "No city found";
                intent.putExtra("weather", message);
                startActivityForResult(intent, 1);
            }
        });

    }

}


