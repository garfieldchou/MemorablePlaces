package com.garfieldchou.memorableplaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView placeListView;
    static ArrayList<String> placeList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("Info", "onCreate() again");

        placeListView = (ListView) findViewById(R.id.placeListView);

        if (placeList.size() == 0) {

            placeList.add("Add a new place...");

        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, placeList);

        placeListView.setAdapter(arrayAdapter);

        placeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);

                intent.putExtra("Item Click", i);

                startActivity(intent);
                
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.i("back", "back key pressed?");

        try {

            Intent intent = getIntent();
            placeList.addAll(intent.getStringArrayListExtra("savedPlaces"));

        } catch (Exception e){

            e.printStackTrace();

            Log.i("info", "Failed to get savedPlaces");

        }

    }
}
